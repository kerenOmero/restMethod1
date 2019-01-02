package com.users.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;



@Path("/users")
public class UserResource {

    private ArrayList<User> usersDataBase = new ArrayList<User>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return usersDataBase;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") String userId) {
        User user = null;
        for (int i = 0; i < usersDataBase.size(); i++)
        {
            if (usersDataBase.get(i).getId() == Integer.parseInt(userId)) {
                user = usersDataBase.get(i);
            }
        }
        return user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        usersDataBase.add(user);
        return Response.status(Response.Status.CREATED).entity(user.getId()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserById(@PathParam("id") String userId, User newUser) {
        User user = null;
        for (int i = 0; i < usersDataBase.size(); i++) {
            if (usersDataBase.get(i).getId() == Integer.parseInt(userId)) {
                user = usersDataBase.get(i);
                user.setFirstName(newUser.getFirstName());
                user.setFirstName(newUser.getLastName());
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteUserById(@PathParam("id") String userId) {
        for (int i = 0; i < usersDataBase.size(); i++) {
            if (usersDataBase.get(i).getId() == Integer.parseInt(userId)) {
                usersDataBase.remove(i);
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
