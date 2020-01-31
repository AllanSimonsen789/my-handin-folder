/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author allan
 */
public class facadeTest {

    public facadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        facade instance = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        instance.clearDatabase();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findByID method, of class facade.
     */
    @Test
    public void testFindByID() {
        System.out.println("findByID");
        facade instance = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        Customer expResult = instance.addCustomer("Allan", "Simonsen");
        Customer result = instance.findByID(Math.toIntExact(expResult.getId()));
        assertEquals(expResult, result);

    }

    /**
     * Test of findByLastName method, of class facade.
     */
    @Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        facade instance = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        List<Customer> expResult = new ArrayList<>();
        expResult.add(instance.addCustomer("Allan", "Simonsen"));
        expResult.add(instance.addCustomer("Niels", "Simonsen"));
        List<Customer> result = instance.findByLastName("Simonsen");
        assertArrayEquals(expResult.toArray(), result.toArray());

    }

    /**
     * Test of getNumberOfCustomers method, of class facade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        facade instance = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        int expResult = 3;
        instance.addCustomer("Allan", "Simonsen");
        instance.addCustomer("Helga", "Poulsen");
        instance.addCustomer("Thomas", "Hartmann");

        int result = instance.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of allCustomers method, of class facade.
     */
    @Test
    public void testAllCustomers() {
        System.out.println("allCustomers");
        facade instance = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        List<Customer> expResult = new ArrayList<>();
        expResult.add(instance.addCustomer("Allan", "Simonsen"));
        expResult.add(instance.addCustomer("Niels", "Simonsen"));
        expResult.add(instance.addCustomer("Helga", "Poulsen"));
        expResult.add(instance.addCustomer("Thomas", "Hartmann"));
        List<Customer> result = instance.allCustomers();
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of addCustomer method, of class facade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        facade instance = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        Customer expResult = instance.addCustomer("Allan", "Simonsen");
        assertNotNull(expResult);
    }

}
