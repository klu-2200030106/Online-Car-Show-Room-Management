package com.klef.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.project.models.Seller;



@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SellerServiceImpl implements SellerService
{
	public String addseller(Seller seller) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(seller); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Seller Registered Successfully";
	}

	@Override
	public Seller checksellerlogin(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select s from Seller s where s.email=? and s.password=?");
		qry.setParameter(1, email);
		qry.setParameter(2, password);
		
		Seller sel=null;
		if(qry.getResultList().size()>0)
		{
			sel = (Seller)qry.getSingleResult();
		}
		em.close();
		emf.close();
		
		return sel;
	}

	@Override
	public List<Seller> viewallsellers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select s from Seller s");
		List<Seller> emplist = qry.getResultList();
		
	    em.close();
	    emf.close();
	    
	    return emplist;
	}

	@Override
	public String deleteseller(int sid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Seller e = em.find(Seller.class, sid);
		em.remove(e);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Seller Deleted Successfully";
	}
	
	@Override
	public String updateseller(Seller seller) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
	    EntityManager em = emf.createEntityManager();
	    
	    em.getTransaction().begin();
	    
	    Seller e = em.find(Seller.class, seller.getId());
	    e.setContact(seller.getContact());
	    e.setName(seller.getName());
	    e.setPassword(seller.getPassword());
	    
	    em.getTransaction().commit();
	    em.close();
	    emf.close();
	    
	    return "Seller Updated Successfully";
	  }
	
    @Override
	public Seller viewsellerbyid(int sid) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
	    EntityManager em = emf.createEntityManager();
	    
	    Seller e = em.find(Seller.class, sid);
	    
	    if(e==null)
	    {
	      em.close();
	      emf.close();
	      return null;
	    }
	    
	    em.close();
	    emf.close();
	    
	    return e;
	  }
}
