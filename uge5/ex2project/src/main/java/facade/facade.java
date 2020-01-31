/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Entity.Customer;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author allan
 */
public class facade {

    private static EntityManagerFactory emf;
    private static facade instance;

    private facade() {
    }

    public static facade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new facade();
            facade f = getFacade(Persistence.createEntityManagerFactory("ex2"));
            f.addCustomer("Allan", "Simonsen");
            f.addCustomer("Helga", "Nielsen");
            f.addCustomer("Alfred", "Nilats");
        }
        return instance;
    }

    EntityManager getManager() {
        return emf.createEntityManager();
    }

    public Customer findByID(int id) {
        EntityManager em = getManager();
        return em.find(Customer.class, (long) id);
    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = getManager();
        TypedQuery q = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :name", Customer.class);
        q.setParameter("name", name);
        return q.getResultList();
    }

    public int getNumberOfCustomers() {
        EntityManager em = getManager();
        TypedQuery q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return q.getResultList().size();
    }

    public List<Customer> allCustomers() {
        EntityManager em = getManager();
        TypedQuery q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return q.getResultList();
    }

    public Customer addCustomer(String fName, String lName) {
        EntityManager em = getManager();
        Customer c = new Customer(fName, lName);
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }

    public void clearDatabase() {
        EntityManager em = getManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Customer c").executeUpdate();
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        facade f = getFacade(Persistence.createEntityManagerFactory("ex2"));
        f.addCustomer("Allan", "Simonsen");
        f.addCustomer("Helga", "Nielsen");
        f.addCustomer("Alfred", "Nilats");
        System.out.println("Number of customers: " + f.getNumberOfCustomers());
        System.out.println("All customers: " + Arrays.asList(f.allCustomers()));
        System.out.println("Find by id(2): " + f.findByID(2));
        System.out.println("Find by name: " + f.findByLastName("Simonsen"));

    }

}
