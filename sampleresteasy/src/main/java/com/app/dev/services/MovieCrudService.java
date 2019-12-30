package com.app.dev.services;

import com.app.dev.model.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieCrudService {

    private Map<String, Movie> inventory = new HashMap<>();

    @GET
    @Path("/")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response index() {
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("").build();
    }

    @GET
    @Path("/list")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Movie> listMovies() {

        return inventory.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }



    @GET
    @Path("/findById/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Movie movieByImdbId(@PathParam("id") String id) {

        System.out.println("*** Calling  getinfo for a given id ***");

        if (inventory.containsKey(id)) {
            return inventory.get(id);
        } else
            return null;
    }


    @POST
    @Path("/add/{id}/{title}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addMovie(@PathParam("id") String id,@PathParam("title") String title){

        System.out.println("*** Calling  addMovie method by parameter ***");

        if (null != inventory.get(id)) {
            return Response.status(Response.Status.NOT_MODIFIED).entity("Movie is Already in the database.").build();
        }

        inventory.put(id, new Movie(id,title));
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/addmovie")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addMovie(Movie movie) {

        System.out.println("*** Calling  addMovie ***");

        if (null != inventory.get(movie.getId())) {
            return Response.status(Response.Status.NOT_MODIFIED).entity("Movie is Already in the database.").build();
        }

        inventory.put(movie.getId(), movie);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/updatemovie")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateMovie(Movie movie) {

        System.out.println("*** Calling  updateMovie ***");

        if (null == inventory.get(movie.getId())) {
            return Response.status(Response.Status.NOT_MODIFIED)
                    .entity("Movie is not in the database.\nUnable to Update").build();
        }

        inventory.put(movie.getId(), movie);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/deletemovie")
    public Response deleteMovie(@QueryParam("imdbId") String imdbId) {

        System.out.println("*** Calling  deleteMovie ***");

        if (null == inventory.get(imdbId)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Movie is not in the database.\nUnable to Delete")
                    .build();
        }

        inventory.remove(imdbId);
        return Response.status(Response.Status.OK).build();
    }



}