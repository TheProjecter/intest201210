package intest.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class HibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	@PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    public HibernateDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }
	
    @Override
    public T create(final T t) {
        this.em.persist(t);
        return t;
    }

    @Override
    public void delete(final ID id) {
        this.em.remove(this.em.getReference(type, id));
    }

    @Override
    public T find(final ID id) {
        return (T) this.em.find(type, id);
    }

    @Override
    public T update(final T t) {
        return this.em.merge(t);    
    }

}
