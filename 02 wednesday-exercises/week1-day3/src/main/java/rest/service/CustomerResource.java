package rest.service;

import com.google.gson.Gson;
import entities.Customer;
import facades.CustomerFacade;
import java.util.List;
import java.util.Random;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("customer")
public class CustomerResource {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CustomerFacade facade = CustomerFacade.getcustomerFacade(emf);
    private static Gson gson = new Gson();
    
    @Context
    private UriInfo context;

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        List<Customer> customers = facade.getAllCustomers();
        return gson.toJson(customers);
    }
    
    @GET
    @Path("/random")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo1() {
        List<Customer> customers = facade.getAllCustomers();
       Random rand = new Random();
       String customerAsJson = gson.toJson(customers.get(rand.nextInt(customers.size())));
       return customerAsJson;
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo2(@PathParam("id") int id) {
        return gson.toJson(facade.findCustomerById(id));
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Customer entity) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Customer entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
