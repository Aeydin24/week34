package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
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

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

    public CustomerDTO getCustomerById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer customer = em.find(BankCustomer.class, id);
            CustomerDTO cust = new CustomerDTO(customer);
            return cust;
        } finally {
            em.close();
        }
    }

    public List<CustomerDTO> getCustomerByName(String firstName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("SELECT c FROM CustomerDTO c WHERE c.firstname = :firstname", BankCustomer.class)
                    .setParameter("firstname", firstName);
            List<CustomerDTO> cust = new ArrayList(query.getResultList());
            return cust;
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("Select customer from BankCustomer customer", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
