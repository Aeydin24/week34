package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
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
    
    public Employee findEmployeeById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee emp = em.find(Employee.class,id);
            return emp;
        }finally {
            em.close();
        }
    }
    
    public List<Employee> findEmployeeByName(String name) {
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
            TypedQuery<Employee> query
                    = em.createQuery("Select e from Employee e", Employee.class);
            return query.getResultList();
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
    public List<Employee> employeeWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery <Employee> query 
                    = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(z.salary) FROM Employee z)", Employee.class);
            return query.getResultList();
        } finally {
        em.close();
    }
        
    }
}
