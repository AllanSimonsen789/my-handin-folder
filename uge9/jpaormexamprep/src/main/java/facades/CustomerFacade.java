package facades;

import entities.Customer;
import entities.ItemType;
import entities.MainOrder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer addCustomer(Customer c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            return c;
        }
    }

    public Customer findCustomerById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = getEntityManager();
        TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return q.getResultList();
    }

    public ItemType createItemType(ItemType it) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(it);
        em.getTransaction().commit();
        return it;
    }

    public ItemType findItemType(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(ItemType.class, id);
    }

    public MainOrder createMainOrder(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        MainOrder order = new MainOrder();
        customer.addOrder(order);
        em.getTransaction().commit();
        return order;
    }
}
