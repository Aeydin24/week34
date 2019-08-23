package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
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
        List<Employee> emp = facade.getAllEmployees();
        List<EmployeeDTO> edto = new ArrayList();
        
        for(Employee e : emp)
            edto.add(new EmployeeDTO(e));
        return gson.toJson(edto);
        }
        
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo2(@PathParam("id") int id) {
        return gson.toJson(new EmployeeDTO(facade.findEmployeeById(id)));
    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo3() {
        List<Employee> emp = facade.employeeWithHighestSalary();
        List<EmployeeDTO> empdto = new ArrayList<>();
        for(Employee e : emp) {
            empdto.add(new EmployeeDTO(e));
        }
        return gson.toJson(empdto);
    }
    @GET
    @Path("names/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo4(@PathParam("name")String name) {
        List<Employee> emp = facade.findEmployeeByName(name);
        List<EmployeeDTO> empdto = new ArrayList();
        for(Employee e : emp) {
            empdto.add(new EmployeeDTO(e));
        }
        return gson.toJson(empdto);
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            Employee bc1 = new Employee("Jenni Hansen", "Ostemadsvej 3452", "66346");
            Employee bc2 = new Employee("Sten Frederiksen","Frederiksundsvej 43","20000");
            Employee bc3 = new Employee("Tom Mikkelsen","Bangsbovej 435","3265456");
            Employee bc4 = new Employee("Dorte Jørgensen","Lundebjerg 582","664345");
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
