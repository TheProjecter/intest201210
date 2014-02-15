package intest.dao;

import java.io.Serializable;

public interface GenericDAO<T, ID extends Serializable> {

	T create(T t);

	T find(ID id);

	T update(T t);

	void delete(ID id);

}
