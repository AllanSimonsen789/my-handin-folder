package facades;

import entities.Employee;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(int id) {
        EntityManager em = getEntityManager();
        return em.find(Employee.class, (long) id);
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
        q.setParameter("name", name);
        return q.getResultList();
    }

    public Employee getEmployeeWithHighestSalary() {
        EntityManager em = getEntityManager();
        TypedQuery<Employee> q = em.createQuery("select e from Employee e where e.salary = (SELECT MAX(s.salary) FROM Employee s)", Employee.class);
        return q.getSingleResult();
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return q.getResultList();
    }

    public Employee addEmployee(Employee e) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        return e;
    }

    public void clearDatabase() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Employee e").executeUpdate();
        em.getTransaction().commit();
    }

    public static void main(String args[]) {
        EmployeeFacade.getEmployeeFacade(Persistence.createEntityManagerFactory("pu"));
        instance.clearDatabase();
        instance.addEmployee(new Employee("ULRIK", "SKOVBRYNET 420", 100000));
        instance.addEmployee(new Employee("Allan", "Kinavej 69", 99999999));
        instance.addEmployee(new Employee("Paulsen", "coronastræde 47", 45678));
        instance.addEmployee(new Employee("william", "Højegladsaxe 3,14", 420));
        System.out.println(Arrays.toString(instance.getAllEmployees().toArray()));
        System.out.println("Highest payed: " + instance.getEmployeeWithHighestSalary());
        System.out.println("Employee with id 3: " + instance.getEmployeeById(3));
        System.out.println("Employee with name Ulrik: " + instance.getEmployeesByName("Ulrik"));

    }
}
