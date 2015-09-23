package dao;

import java.util.Map;

public interface BaseDAO<T> {
	
	long countAll(Map<String, Object> params);

    T insert(T t);

    void delete(Object id);

    T  findById(Object id);

    T update(T t);
    
}
