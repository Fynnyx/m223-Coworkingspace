package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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

import ch.zli.m223.model.Place;
import ch.zli.m223.service.PlaceService;

@Path("/places")
public class PlaceController {

    @Inject
    PlaceService placeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all places", description = "Returns a list of all places")
    public List<Place> getAll() {
        return placeService.findAll();
    }

    @GET
    @RolesAllowed("Administrator")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one bokking.", description = "Returns a place based on the id provided.")
    @Path("/{id}")
    public Place getById(long id) {
        return placeService.getPlaceById(id);
    }

    @POST
    @RolesAllowed("Administrator")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new place.", description = "Creates a new place")
    public Place create(Place place) {
        System.out.println();
        return placeService.createPlace(place);
    }

    @DELETE
    @RolesAllowed("Administrator")
    @Operation(summary = "Delete a place.", description = "Deletes a place.")
    @Path("/{id}")
    public void delete(long id) {
        placeService.deletePlace(id);
    }

    @PUT
    @RolesAllowed("Administrator")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a place.", description = "Updates a place based on the id provided.")
    @Path("/{id}")
    public Place update(long id, Place place) {
        place.setId(id);
        placeService.updatePlace(place);
        return place;
    }
}
