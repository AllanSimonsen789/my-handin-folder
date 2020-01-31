package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this tes
@Disabled
public class BankCustomerFacadeTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static BankCustomerFacade facade = BankCustomerFacade.getBankCustomerFacade(emf);

    public BankCustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        

    }

    @Before
    public void setUp() {
        facade.clearDatabase();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new BankCustomer("Rasmus", "Høgh", "4321", 123.0, 50, "Student Customer"));
        em.persist(new BankCustomer("Jacob", "Christensen", "321", 321.0, 10, "Business Customer"));
        em.persist(new BankCustomer("Simone", "Wiedenbein", "21", 111.0, 99, "RKI Customer"));
        em.getTransaction().commit();

    }

    @Test
    public void getCustomerByIDTest() {
        System.out.println("findByID");
        CustomerDTO expResult = new CustomerDTO(facade.addCustomer(new BankCustomer("Lærke", "Lærkesen", "555", 222.0, 44, "New Customer")));
        CustomerDTO result = facade.getCustomerByID(Math.toIntExact(expResult.getCustomerID()));
        assertEquals(expResult, result);

    }

    /**
     * Test of findByLastName method, of class facade.
     */
    @Test
    public void testFindBytNameTest() {
        System.out.println("findByLastName");
        List<CustomerDTO> expResult = new ArrayList<>();
        expResult.add(new CustomerDTO(facade.addCustomer(new BankCustomer("Jonathan", "Andersen", "888", 567.0, 77, "Student Customer"))));
        List<CustomerDTO> result = facade.getCustomerByName("Jonathan");
        assertArrayEquals(expResult.toArray(), result.toArray());

    }

    /**
     * Test of getNumberOfCustomers method, of class facade.
     */
    @Test
    public void addCustomerTest() {
        BankCustomer bc = facade.addCustomer(new BankCustomer("Jonathan", "Andersen", "888", 567.0, 77, "Student Customer"));
        assertNotNull(bc);
    }
    /**
     * Test of allCustomers method, of class facade.
     */
    @Test
    public void getAllBankCustomersTest() {
        System.out.println("allCustomers");
        List<BankCustomer> result = facade.getAllBankCustomers();
        assertEquals(3, result.size());
    }

}
