package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.BankCustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("bankcustomer")
public class BankCustomerResource {

    private static Gson gson = new Gson();
//    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/startcode",
//                "dev",
//                "ax2",
//                EMF_Creator.Strategy.CREATE);
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final BankCustomerFacade FACADE = BankCustomerFacade.getBankCustomerFacade(emf);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerByID(@PathParam("id") int id) {
        return GSON.toJson(FACADE.getCustomerByID(id));
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCustomers() {
        return GSON.toJson(FACADE.getAllBankCustomers());
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        //long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        //return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
        return null;
    }

}
