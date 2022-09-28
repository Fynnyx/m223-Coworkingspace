package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;

@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    @Inject
    Validator validator;

    @GET
    @RolesAllowed({ "Administrator", "Mitglied" })
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GET
    @RolesAllowed({ "Administrator", "Mitglied" })
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index one user.", description = "Returns a user based of the id provided.")
    @Path("/{id}")
    public Response getById(long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.status(Status.BAD_REQUEST).entity("No user found by this id").build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new user.", description = "Creates a new user")
    public Response create(User user) {
        try {
            return Response.ok(userService.createUser(user)).build();
        } 
        catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Email allready exists").build();
        } 
        catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getConstraintViolations().toString()).build();
        }
    }

    @DELETE
    @RolesAllowed({ "Administrator", "Mitglied" })
    @Operation(summary = "Delete a user.", description = "Deletes a user.")
    @Path("/{id}")
    public void delete(long id) {
        userService.deleteUser(id);
    }

    @PUT
    @RolesAllowed({ "Administrator", "Mitglied" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a user.", description = "Updates a user based on the id provided.")
    @Path("/{id}")
    public User update(long id, User user) {
        user.setId(id);
        userService.updateUser(user);
        return user;
    }
}
