package com.klef.project.services;



import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.project.models.User;



@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceImpl implements UserService
{

	@Override
	public String adduser(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(user); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "User Registered Successfully";
	}

	@Override
	public User checkuserlogin(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select u from User u where u.email=? and u.password=?");
		qry.setParameter(1, email);
		qry.setParameter(2, password);
		
		User us=null;
		if(qry.getResultList().size()>0)
		{
			us = (User)qry.getSingleResult();
		}
		em.close();
		emf.close();
		
		return us;
	}

	@Override
	public List<User> viewallusers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select u from User u");
		List<User> userlist = qry.getResultList();
		
	    em.close();
	    emf.close();
	    
	    return userlist;
	}
   
	
	


}
