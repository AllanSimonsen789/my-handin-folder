/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import facade.CustomerFacade;
import javax.persistence.EntityManager;

/**
 *
 * @author allan
 */
public class Tester {


    public static void main(String[] args) {
        Customer customer1 = new Customer("Allan", "Simonsen");
        Customer customer2 = new Customer("Nina", "Lisakowski");
        Customer customer3 = new Customer("Tobias", "Jørgensen");

        Address address1 = new Address("Kagsåkollegiet", "Søborg");
        Address address2 = new Address("Gunlogsgade", "AMAGER");
        Address address3 = new Address("JerseyShore", "provinsen");
        Address address4 = new Address("Nørgaardsvej", "Lyngby");
        
        EntityManager em = CustomerFacade.emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);
        em.persist(address1);
        em.persist(address2);
        em.persist(address3);
        em.persist(address4);

        
        customer1.addAddresses(address1);
        customer2.addAddresses(address2);
        customer3.addAddresses(address3);
        customer1.addAddresses(address4);
        customer2.addAddresses(address4);
        customer3.addAddresses(address4);
        
        em.getTransaction().commit();
        CustomerFacade pf = new CustomerFacade();
        customer3.setfName("CAROLINE");
        pf.editCustomer(customer3);
        pf.deleteCustomer(Math.toIntExact(customer2.getId()));
        
        
        //Persistence.generateSchema("pu", null);
    }
}
