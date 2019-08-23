package rest.service;

import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    EntityManagerFactory emf; 
    EmployeeFacade facade =  EmployeeFacade.getFacadeExample(emf);

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Employee> demo() {
        return facade.getAllEmployees();
    }
}