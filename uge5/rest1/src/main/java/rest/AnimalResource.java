/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author allan
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;
    
    private Gson gson = new Gson();
    
    private List<Animal> animals = new ArrayList<>();
    
    private Random random = new Random();

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
        animals.add(new Animal("Dog", 1919, "BARK"));
        animals.add(new Animal("Duck", 2020, "Quack"));
        animals.add(new Animal("Cat", 2000, "meow"));
        animals.add(new Animal("Fox", 2013, "What does the fox say?"));
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello from my first web service";
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom(){
        return gson.toJson(animals.get(random.nextInt(animals.size())));
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
