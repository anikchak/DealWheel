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
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	private Class<T> type;
	
	public EntityManager getEntityManager(){
		return em;
	}
	
    public BaseDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public long countAll(final Map<String, Object> params) {

        final StringBuffer queryString = new StringBuffer(
                "SELECT count(o) from ");

        queryString.append(type.getSimpleName()).append(" o ");
//        queryString.append(this.getQueryClauses(params, null));

        final Query query = getEntityManager().createQuery(queryString.toString());

        return (Long) query.getSingleResult();

    }

    @Override
    public T insert(final T t) {
    	getEntityManager().getTransaction().begin();
    	getEntityManager().persist(t);
    	getEntityManager().getTransaction().commit();
        return t;
    }

    @Override
    public void delete(final Object id) {
        getEntityManager().remove(getEntityManager().getReference(type, id));
    }

    @Override
    public T findById(final Object id) {
        return (T) getEntityManager().find(type, id);
    }

    @Override
    public T update(final T t) {
        return getEntityManager().merge(t);    
    }
}
