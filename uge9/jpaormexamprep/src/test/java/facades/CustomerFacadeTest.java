package facades;

import entities.Customer;
import entities.ItemType;
import entities.MainOrder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static CustomerFacade facade;
    private static Customer c1, c2;
    private static ItemType it1;

    public CustomerFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = CustomerFacade.getCustomerFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = CustomerFacade.getCustomerFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("OrderLine.deleteAllRows").executeUpdate();
            em.createNamedQuery("MainOrder.deleteAllRows").executeUpdate();
            em.createNamedQuery("Customer.deleteAllRows").executeUpdate();
            em.createNamedQuery("ItemType.deleteAllRows").executeUpdate();
            c1 = new Customer("allan", "123@mail.com");
            c2 = new Customer("jonas", "312@mail.com");
            em.persist(c1);
            em.persist(c2);
            MainOrder o1 = new MainOrder();
            MainOrder o2 = new MainOrder();
            MainOrder o3 = new MainOrder();
            c1.addOrder(o1);
            c1.addOrder(o2);
            c2.addOrder(o3);
            it1 = new ItemType("cola", "meget sukker", 10);
            em.persist(it1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testAddPerson() {
        Customer customer = new Customer("Jacob", "333@mail.com");
        customer.addOrder(new MainOrder());
        Customer CustomerResult = facade.addCustomer(customer);
        assertNotNull(CustomerResult);
    }

    @Test
    public void testFindPerson() {
        assertEquals(c1, facade.findCustomerById(c1.getId()));
    }

    @Test
    public void testGetAllCustomers() {
        assertEquals(2, facade.getAllCustomers().size());
    }

    @Test
    public void testAddItemType() {
        assertNotNull(facade.createItemType(new ItemType("Svaneke bryg pilsner", "God Pilsner", 50)));

    }

    @Test
    public void testFindItemType() {
        assertEquals(it1, facade.findItemType(it1.getId()));

    }

    @Test
    public void testAddOrderToCustomer() {
        facade.createMainOrder(c1.getId());
    }
}
