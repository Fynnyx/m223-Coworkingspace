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

import ch.zli.m223.model.KaffeeLog;
import ch.zli.m223.service.KaffeeLogService;

@Path("/kaffeeLogs")
public class KaffeeLogController {

    @Inject
    KaffeeLogService kaffeeLogService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all kaffeeLogs", description = "Returns a list of all kaffeeLogs")
    public List<KaffeeLog> getAll() {
        return kaffeeLogService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one bokking.", description = "Returns a kaffeeLog based on the id provided.")
    @Path("/{id}")
    public KaffeeLog getById(long id) {
        return kaffeeLogService.getKaffeeLogById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new kaffeeLog.", description = "Creates a new kaffeeLog")
    public KaffeeLog create(KaffeeLog kaffeeLog) {
        return kaffeeLogService.createKaffeeLog(kaffeeLog);
    }

    @DELETE
    @Operation(summary = "Delete a kaffeeLog.", description = "Deletes a kaffeeLog.")
    @Path("/{id}")
    public void delete(long id) {
        kaffeeLogService.deleteKaffeeLog(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a kaffeeLog.", description = "Updates a kaffeeLog based on the id provided.")
    @Path("/{id}")
    public KaffeeLog update(long id, KaffeeLog kaffeeLog) {
        kaffeeLog.setId(id);
        kaffeeLogService.updateKaffeeLog(kaffeeLog);
        return kaffeeLog;
    }
}
