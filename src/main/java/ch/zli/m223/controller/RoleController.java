package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Role;
import ch.zli.m223.service.RoleService;

@Path("/roles")
public class RoleController {

    @Inject
    RoleService roleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all roles", description = "Returns a list of all roles")
    public List<Role> getAll() {
        return roleService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one role.", description = "Returns a role based on the id provided.")
    @Path("/{id}")
    public Role getById(long id) {
        return roleService.getRoleById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new role.", description = "Creates a new role")
    public Role create(Role role) {
        return roleService.createRole(role);
    }

    @DELETE
    @Operation(summary = "Delete a role.", description = "Deletes a role.")
    @Path("/{id}")
    public void delete(long id) {
        roleService.deleteRole(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a role.", description = "Updates a role based on the id provided.")
    @Path("/{id}")
    public Role update(long id, Role role) {
        role.setId(id);
        roleService.updateRole(role);
        return role;
    }
}
