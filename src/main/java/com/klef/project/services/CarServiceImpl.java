package com.klef.project.services;
import com.klef.project.models.Car;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CarServiceImpl implements CarService
{

  @Override
  public String addCar(Car car) {
      EntityManagerFactory emf= Persistence.createEntityManagerFactory("epjpa");
       EntityManager  em= emf.createEntityManager();
       
          em.getTransaction().begin();
          em.persist(car);
          em.getTransaction().commit();
          
          
          em.close();
          emf.close();
           return "success";
  }

  @Override
  public String updateCar(Car car) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("epjpa");
	    EntityManager em = emf.createEntityManager();
	    
	    em.getTransaction().begin();
	    
	    Car e = em.find(Car.class, car.getId());
	    e.setName(car.getName());
	        e.setModel(car.getModel());
	        e.setDescription(car.getDescription());
	        e.setPrice(car.getPrice());
	    
	    em.getTransaction().commit();
	    em.close();
	    emf.close();
	    
	    return "car Updated Successfully";
	  }
  
  @Override
  public String deleteCar(int eid) {
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("epjpa");
    EntityManager  em= emf.createEntityManager();
    
      em.getTransaction().begin();
          Car e= em.find(Car.class, eid);
          if (e != null) {
              em.remove(e);
              em.getTransaction().commit();
          } else {
              em.getTransaction().rollback();
              em.close();
              return "Event not found";
          }
          em.close();
          return "Event Deleted Successfully";
  }

  @Override
  public List<Car> viewallCars() {
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("epjpa");
    EntityManager  em= emf.createEntityManager();
    
     List<Car> cars = em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
          em.close();
          return cars;
  }

  @Override
  public Car viewCarById(int id) {
    
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("epjpa");
    EntityManager  em= emf.createEntityManager();
         
    Car e = em.find(Car.class, id);
    
    if(e==null) {
      em.close();
      emf.close();
      
      return null;
    }
    em.close();
    emf.close();
    
    return e;   
    
  }

}