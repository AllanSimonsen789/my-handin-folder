/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entity.Customer;
import com.google.gson.Gson;
import facade.facade;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author allan
 */
@Path("customer")
public class CustomerResource {

    private Gson gson = new Gson();
    private Random random = new Random();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of Entity.CustomerResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hellooooo";
    }

    @GET
    @Path("/all")
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJsonAll() {
        //TODO return proper representation object
        facade f = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        return gson.toJson(f.allCustomers());
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonRandom() {
        //TODO return proper representation object
        facade f = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        List<Customer> customers = f.allCustomers();
        return gson.toJson(customers.get(random.nextInt(customers.size())));
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookAsJson(@PathParam("id")int id) {
        facade f = facade.getFacade(Persistence.createEntityManagerFactory("ex2"));
        return gson.toJson(f.findByID(id));
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
