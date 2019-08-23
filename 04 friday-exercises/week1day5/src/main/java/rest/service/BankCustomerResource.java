package rest.service;

import com.google.gson.Gson;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BankCustomerResource {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    private static Gson gson = new Gson();
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo2(@PathParam("id") int id) {
        return gson.toJson(facade.getCustomerById(id));
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo3() {
        List<BankCustomer> customers = facade.getAllCustomers();
        return gson.toJson(customers);
    }
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer bc1 = new BankCustomer("Jenni", "Hansen", "100-1234", 10092, 3, "Medlem siden 1997-10-05");
            BankCustomer bc2 = new BankCustomer("Sten","Frederiksen","100-7362",6857, 8, "Medlem siden 1985-01-09");
            BankCustomer bc3 = new BankCustomer("Tom","Mikkelsen","100-8271", 983, 10, "Medlem siden 1992-03-23");
            BankCustomer bc4 = new BankCustomer("Dorte","Jørgensen","100-8251", 10, 2, "Medlem siden 2003-11-30");
            em.getTransaction().begin(); //begin transaction
            em.persist(bc1);
            em.persist(bc2);
            em.persist(bc3);
            em.persist(bc4);
            em.getTransaction().commit(); //commit transactions
        } finally {
            em.close();
        }
        return "Populated the Database successfully. Don't run this again.";
    }
}
