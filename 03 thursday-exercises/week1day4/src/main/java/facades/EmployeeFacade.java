package facades;

import entities.Employee;
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
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee findCustomerById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee customer = em.find(Employee.class,id);
            return customer;
        }finally {
            em.close();
        }
    }
    
    public List<Employee> findCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT b FROM Employee b WHERE b.name = :name",Employee.class)
                    .setParameter("name", name);
            return query.getResultList();
        } finally {
        em.close();
    }
}
    
    public List<Employee> getAllEmployees () {
        EntityManager em = getEntityManager();
        try {
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e").getResultList();
            return employees;
        } finally {
            em.close();
        }
    }
    public Employee addEmployee(String name, String address, String salary) {
        Employee emp = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        }finally {
            em.close();
        }
    }
//    public Employee employeeWithHighestSalary() {
//        EntityManager em = getEntityManager();
//        try {
//            TypedQuery query = em.createQuery("SELECT b FROM Employee b WHERE b.salary < :salary",Employee.class)
//                    .setParameter("salary", salary);
//            return query.getResultList();
//        } finally {
//        em.close();
//    }
//        
//    }
}
