package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@Path("persons")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        return GSON.toJson(FACADE.getAllPersons());
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerson(@PathParam("id")int id)throws PersonNotFoundException {
        return GSON.toJson(FACADE.getPerson(id));
    }
    
    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addPerson(String person)throws MissingInputException {
        PersonDTO pDTO = GSON.fromJson(person, PersonDTO.class);
        pDTO = FACADE.addPerson(pDTO.getfName(), pDTO.getlName(), pDTO.getPhone());
        return Response.ok(pDTO).build();
    }
    
    @PUT
    @Path("edit")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPerson(String person) throws PersonNotFoundException, MissingInputException{
        PersonDTO pDTO = GSON.fromJson(person, PersonDTO.class);
        pDTO = FACADE.editPerson(pDTO);
        return Response.ok(pDTO).build();
    }
    
    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePerson(@PathParam("id") int id) throws PersonNotFoundException{
        PersonDTO pDTO = FACADE.deletePerson(id);
        return Response.ok(pDTO).build();
    }
    
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populateDB() throws MissingInputException {
        FACADE.addPerson("Allan", "Simonsen", "34567890");
        FACADE.addPerson("Niels", "Simonsen", "34567890");
        FACADE.addPerson("Pia", "Simonsen", "34567890");
        return GSON.toJson("Done");
    }
    
    

 
}
