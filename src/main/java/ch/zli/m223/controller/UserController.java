package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;

@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one user.", description = "Returns a user based of the id provided.")
    @Path("/{id}")
    public List<User> getById(long id) {
        if (id > 0) {
            return List.of(userService.getUserById(id));
        }
        return userService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new user.", description = "Creates a new user")
    public User create(User user) {
        return userService.createUser(user);
    }

    @DELETE
    @Operation(summary = "Delete a user.", description = "Deletes a user.")
    @Path("/{id}")
    public void delete(long id) {
        userService.deleteUser(id);
    }

    @PUT
    @Operation(summary = "Update a user.", description = "Updates a user based on the id provided.")
    @Path("/{id}")
    public User update(long id, User user) {
        user.setId(id);
        userService.updateUser(user);
        return user;
    }
}
