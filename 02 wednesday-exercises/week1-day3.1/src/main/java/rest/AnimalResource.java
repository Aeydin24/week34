/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.Random;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
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
 * @author benjaminbajrami
 */
@Path("animal")
public class AnimalResource {
    
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getRandomAnimal() {
    ArrayList<Animal> array = new ArrayList();
    Animal animal1 = new Animal("Duck", 2015, "Quack!");
    Animal animal2 = new Animal("Cow", 2013, "Mooo!");
    Animal animal3 = new Animal("Horse", 2010, "Prrrrh");
    Animal animal4 = new Animal("Cat", 2011, "Miaw");
    array.add(animal1);
    array.add(animal2);
    array.add(animal3);
    array.add(animal4);
    Random rand = new Random();
        Animal randomAnimal = array.get(rand.nextInt(array.size()));
        return gson.toJson(randomAnimal);
    }
    
    
    @GET
    public String getJson() {
        //TODO return proper representation object
        return "Hello from my first ever webservice";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {

    }
}
/* the steps I took was mainly to set the netbeans preferences to no proxy so Tomcat would actually 
function like a normal piece of shit software goddamnit how I fucking despise tomcat and everything 
it contains */
