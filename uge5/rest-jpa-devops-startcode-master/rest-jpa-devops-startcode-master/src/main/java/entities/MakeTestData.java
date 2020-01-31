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
public class MakeTestData {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    static EntityManager em = emf.createEntityManager();
    
    public static void main(String[] args){
        em.getTransaction().begin();
        em.persist(new BankCustomer("Allan", "Simonsen", "420", 69.0, 100, "Very good customer"));
        em.persist(new BankCustomer("William", "sdfghjk", "123", 67.0, 100, "customer"));
        em.persist(new BankCustomer("Ulrik", "HOLM", "1234", 68.0, 100, "Very nice customer"));
        em.persist(new BankCustomer("Pølle", "Paulsen", "12345", 0.0, 100, "bad customer"));
        em.getTransaction().commit();
    }
}
