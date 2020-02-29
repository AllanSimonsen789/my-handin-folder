    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author allan
 */
public class Tester {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public static void main(String[] args){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer c1 = new Customer("Allan", "Simonsen");
        Customer c2 = new Customer("Jon", "Berthelsen");
        c1.addHobby("oel");
        c1.addHobby("swimming");
        c2.addHobby("Coding");
        c1.addPhone("123", "Allan");
        c1.addPhone("321", "Tobias");
        c2.addPhone("666", "Britta Nielsen");
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();
    }
}
