package com.app.dev.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService {

    @GET
    public Response printMessage() {

        String result = "Restful example : Default message";

        return Response.status(200).entity(result).build();

    }


    @GET
    @Path("/{user}")
    public Response dispMessage(@PathParam("user") String msg) {
        String message = "Welcome " + msg + "!!!";

        return Response.status(200).entity(message).build();

    }


    @GET
    @Path("/users")
    public Response getAllUsers()  {
        String result = "<h1>RESTful Demo Application</h1>In real world application, a collection of users will be returned !!";
        return Response.status(200).entity(result).build();
    }

}
