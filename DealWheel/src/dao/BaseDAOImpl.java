package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BaseDAOImpl<T> implements BaseDAO<T> {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("DealWheel");
	EntityManager em ;
	
	private Class<T> type;
	
	public BaseDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        em = emf.createEntityManager();
    }

    @Override
    public long countAll(final Map<String, Object> params) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(type.getSimpleName()).append(" o ");
//        queryString.append(this.getQueryClauses(params, null));

        final Query query = em.createQuery(queryString.toString());

        return (Long) query.getSingleResult();

    }

    @Override
    public T insert(final T t) {
    	EntityTransaction txn = null;
    	try{
    		txn = em.getTransaction();
        	if(!txn.isActive())
        		txn.begin();
        	em.persist(t);
        	txn.commit();
    	}catch(Exception e){
    		if(txn.isActive())
    			txn.rollback();
    	}finally{
    		if(em.isOpen())
    			em.close();
    	}
        return t;
    }

    @Override
    public void delete(final Object id) {
    	EntityTransaction txn = null;
    	try{
    		txn = em.getTransaction();
        	if(!txn.isActive())
        		txn.begin();
        	em.remove(em.getReference(type, id));
        	txn.commit();
    	}catch(Exception e){
    		if(txn.isActive())
    			txn.rollback();
    	}finally{
    		if(em.isOpen())
    			em.close();
    	}
    }

    @Override
    public T findById(final Object id) {
        return (T) em.find(type, id);
    }

    @Override
    public T update(final T t) {
    	EntityTransaction txn = null;
    	try{
    		txn = em.getTransaction();
        	if(!txn.isActive())
        		txn.begin();
        	em.merge(t);
        	txn.commit();
    	}catch(Exception e){
    		if(txn.isActive())
    			txn.rollback();
    	}finally{
    		if(em.isOpen())
    			em.close();
    	}
        return t;
    }
}
