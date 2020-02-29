package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import interfaces.IPersonFacade;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) throws MissingInputException {
        EntityManager em = getEntityManager();
        try{
        Person p = new Person(fName, lName, phone, new Date(), new Date());
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        return new PersonDTO(p);
        }catch(Exception e){
            throw new MissingInputException("Missing input");
        }
    }

    @Override
    public PersonDTO deletePerson(int id)throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try{
        Person p = em.find(Person.class, (long)id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return new PersonDTO(p);
        }catch(Exception e){
            throw new PersonNotFoundException("Person does not exist");
        }
        
    }

    @Override
    public PersonDTO getPerson(int id)throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try{
        return new PersonDTO(em.find(Person.class, (long)id));
        }catch(Exception e){
            throw new PersonNotFoundException("Person does not exist");
        }
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p", Person.class);
        PersonsDTO pDTO = new PersonsDTO();
        pDTO.addPersonsArray(q.getResultList());
        return pDTO;
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException, MissingInputException {
        EntityManager em = getEntityManager();
        try{
        Person person = em.find(Person.class, (long)p.getId());
        em.getTransaction().begin();
        person.setfName(p.getfName());
        person.setlName(p.getlName());
        person.setPhone(p.getPhone());
        person.setLastEdited(new Date());
        if(person.getfName() == null || person.getlName() == null || person.getPhone() == null){
            throw new NullPointerException();
        }
        em.getTransaction().commit();
        return new PersonDTO(person);
        }catch(NullPointerException e){
            throw new MissingInputException("Missing input");   
        }catch(Exception e){
            throw new PersonNotFoundException("Person does not exist");
        }
        
    }
    
    

}
