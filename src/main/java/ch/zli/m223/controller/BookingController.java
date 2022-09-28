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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Booking;
import ch.zli.m223.service.BookingService;

@Path("/bookings")
public class BookingController {

    @Inject
    BookingService bookingService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"Administrator", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all bookings", description = "Returns a list of all bookings")
    public List<Booking> getAll() {
        return bookingService.findAll();
    }

    @GET
    @RolesAllowed({"Administrator", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get one bokking.", description = "Returns a booking based on the id provided.")
    @Path("/{id}")
    public Response getById(long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking.getUser().getId().toString().equals(jwt.getClaim("id").toString()) || jwt.getGroups().contains("Administrator")) {
            return Response.ok(booking).build();
        }
        return Response.status(Status.UNAUTHORIZED).build();
    }

    @POST
    @RolesAllowed({"Administrator", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new booking.", description = "Creates a new booking")
    public Booking create(Booking booking) {
        return bookingService.createBooking(booking);
    }

    @DELETE
    @RolesAllowed({"Administrator", "Mitglied"})
    @Operation(summary = "Delete a booking.", description = "Deletes a booking.")
    @Path("/{id}")
    public void delete(long id) {
        bookingService.deleteBooking(id);
    }

    @PUT
    @RolesAllowed({"Administrator", "Mitglied"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a booking.", description = "Updates a booking based on the id provided.")
    @Path("/{id}")
    public Response update(long id, Booking booking) {
        if (booking.getUser().getId().toString().equals(jwt.getClaim("id").toString()) || jwt.getGroups().contains("Administrator")) {
            booking.setId(id);
            bookingService.updateBooking(booking);
            return Response.ok(booking).build();
        }
        return Response.status(Status.UNAUTHORIZED).build();
    }
}
