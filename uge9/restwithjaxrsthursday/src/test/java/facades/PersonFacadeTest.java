package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private Person r1, r2, r3;

    public PersonFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = PersonFacade.getPersonFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() throws MissingInputException {
        EntityManager em = emf.createEntityManager();
        try {
            r1 = new Person("Allan", "Simonsen", "34567890", new Date(), new Date());
            r2 = new Person("Niels", "Simonsen", "45678", new Date(), new Date());
            r3 = new Person("Pia", "Simonsen", "0987654", new Date(), new Date());
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    
//      public PersonDTO addPerson(String fName, String lName, String phone);  
//  public PersonDTO deletePerson(int id);  
//  public PersonDTO editPerson(PersonDTO p); 
  
  
  @Test
    public void testgetPerson()throws PersonNotFoundException {
        assertEquals(new PersonDTO(r1), facade.getPerson(Math.toIntExact(r1.getId())));
    }

    @Test
    public void testGetPersons() {
        PersonsDTO persons = new PersonsDTO();
        persons.addPerson(r1);
        persons.addPerson(r2);
        persons.addPerson(r3);
        
        //Expected 
        List<PersonDTO> personList = persons.getAll();
        
        for (PersonDTO p : facade.getAllPersons().getAll()) {
            assertTrue(personList.contains(p));
        }
    }

    @Test
    public void testAddPerson() throws PersonNotFoundException, MissingInputException{
        PersonDTO pDTO = facade.addPerson("Jacob", "Simonsen", "234567890");
        assertEquals(pDTO, facade.getPerson(Math.toIntExact(pDTO.getId())));
    }

    @Test 
    public void testDeletePerson() throws PersonNotFoundException{
        assertEquals(new PersonDTO(r1), facade.deletePerson(Math.toIntExact(r1.getId())));
    }
    
    @Test
    public void testEditPerson() throws PersonNotFoundException, MissingInputException{
        assertEquals(new PersonDTO(r1), facade.getPerson(Math.toIntExact(r1.getId())));
        r1.setfName("Jacob");
        facade.editPerson(new PersonDTO(r1));
        assertEquals(new PersonDTO(r1), facade.getPerson(Math.toIntExact(r1.getId())));

    }
}
