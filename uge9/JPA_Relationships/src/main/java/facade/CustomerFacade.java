/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author allan
 */
public class CustomerFacade {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");


    public List<Customer> getCustomers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return q.getResultList();
    }

    public Customer addCustomer(Customer cust) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cust);
        em.getTransaction().commit();
        return cust;
    }

    public Customer getCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, (long) id);
    }
    
    public Customer deleteCustomer(int id){
        EntityManager em = emf.createEntityManager();
        Customer cust = em.find(Customer.class, (long) id);
        em.getTransaction().begin();
        em.remove(cust);
        em.getTransaction().commit();
        return cust;
    }
    
    public Customer editCustomer(Customer cust){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(cust);
        em.getTransaction().commit();
        return cust;
    }
}
