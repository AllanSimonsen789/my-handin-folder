package facades;


import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getBankCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public CustomerDTO getCustomerByID(int id) {
        EntityManager em = getEntityManager();
        return new CustomerDTO(em.find(BankCustomer.class, (long) id));
    }
    
    public List<CustomerDTO> getCustomerByName(String name){
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT b FROM BankCustomer b WHERE b.firstName = :name", BankCustomer.class);
        q.setParameter("name", name);
        return q.getResultList();
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(cust);
        em.getTransaction().commit();
        return cust;
    }
    
        public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
        return q.getResultList();
    }
    

    public void clearDatabase() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Employee e").executeUpdate();
        em.getTransaction().commit();
    }
}
