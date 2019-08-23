package rest.service;

import com.google.gson.Gson;
import entities.Employee;
import facades.EmployeeFacade;
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


@Path("employee")
public class EmployeeResource {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    private static Gson gson = new Gson();

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return gson.toJson(facade.getAllEmployees());
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo2(@PathParam("id") int id) {
        return gson.toJson(facade.findEmployeeById(id));
    }
    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo3() {
        return gson.toJson(facade.employeeWithHighestSalary());
    }
    @GET
    @Path("names/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo4(@PathParam("name")String name) {
        return gson.toJson(facade.findEmployeeByName(name));
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            Employee bc1 = new Employee("Jenni", "Hansen", "66346");
            Employee bc2 = new Employee("Sten","Frederiksen","20000");
            Employee bc3 = new Employee("Tom","Mikkelsen","3265456");
            Employee bc4 = new Employee("Dorte","Jørgensen","664345");
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
