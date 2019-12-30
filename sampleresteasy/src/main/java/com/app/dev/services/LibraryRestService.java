package com.app.dev.services;

import javax.ws.rs.*;

@Path("/library")
public class LibraryRestService {

    @GET
    @Path("/books")
    public String getBooks() {
        return "hello";
    }

    @GET
    @Path("/book/{isbn}")
    public String getBook(@PathParam("isbn") String id) {
        // search my database and get a string representation and return it
        return "hello";
    }

    @PUT
    @Path("/book/{isbn}")
    public void addBook(@PathParam("isbn") String id, @QueryParam("name") String name) {
        //return "hello";
    }

    @DELETE
    @Path("/book/{id}")
    public void removeBook(@PathParam("id") String id ){

    }

}