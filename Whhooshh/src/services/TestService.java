package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.LoginDetail;


public class TestService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Whhooshh");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public void signUpUser(String usr,String pwd){
		System.out.println("signUpUser method invoked");
		if(em!=null)
		{
			et.begin();
			LoginDetail l = new LoginDetail();
			l.setUsername(usr);
			l.setPassword(pwd);
			em.persist(l);
			et.commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean fetchUserDetails(String usr, String pwd){
		System.out.println("Fetch user details");
		if(em!=null)
		{
			Query q  = em.createNativeQuery("select l.* from loginDetails l where l.username = ? and l.password = ?" , LoginDetail.class);
			q.setParameter(1, usr);
			q.setParameter(2, pwd);
			List<LoginDetail> resultSet = (List<LoginDetail>)q.getResultList();
			if(resultSet!=null && resultSet.size()>0)
				return true;
			else
				return false;
		}
		return false;
	}
	
}
