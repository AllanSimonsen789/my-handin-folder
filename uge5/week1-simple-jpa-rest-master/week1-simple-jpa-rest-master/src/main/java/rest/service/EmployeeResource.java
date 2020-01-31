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
    
    //NOTE: Change Persistence unit name according to your setup
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    Gson gson = new Gson();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<EmployeeDTO> emdto = new ArrayList<>();
        for (Employee emp : facade.getAllEmployees()) {
            emdto.add(new EmployeeDTO(emp));
        }
        return gson.toJson(emdto);
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployeeWithId(@PathParam("id")int id) {
        return gson.toJson(new EmployeeDTO(facade.getEmployeeById(id)));
    }
    
    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaidEmployee() {
        return gson.toJson(new EmployeeDTO(facade.getEmployeeWithHighestSalary()));
    }
    
    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeByName(@PathParam("name")String name){
        return gson.toJson(new EmployeeDTO(facade.getEmployeesByName(name).get(0)));
    }
    
    
    

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create() {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(@PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
