package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import jokefetcher.JokeFetcher;


/**
 * REST Web Service
 *
 * @author lam
 */
@Path("jokes")
public class JokeResource {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Context
    private UriInfo context;

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() throws IOException {
        return gson.toJson(JokeFetcher.fetchJokes());
    }

   
}
