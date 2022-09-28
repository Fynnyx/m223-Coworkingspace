package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Booking;


@ApplicationScoped
public class BookingService {
    @Inject
    private EntityManager entityManager;

    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Booking", Booking.class);
        return query.getResultList();
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        entityManager.persist(booking);
        return booking;
    }

    @Transactional
    public void deleteBooking(long id) {
        entityManager.remove(getBookingById(id));
    }

    @Transactional
    public void updateBooking(Booking booking){
        entityManager.merge(booking);
    }

    public Booking getBookingById(long id) {
        return entityManager.find(Booking.class, id);
    }
}
