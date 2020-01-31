/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author allan
 */
public class entityTested {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ex2");

    EntityManager getManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Customer customer1 = new Customer("Helga", "Poulsen");
        Customer customer2 = new Customer("Jonas", "Poulsen");

        em.getTransaction().begin();
        em.persist(customer1);
        em.persist(customer2);
        em.getTransaction().commit();

    }
}
