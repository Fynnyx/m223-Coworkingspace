package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Place;


@ApplicationScoped
public class PlaceService {
    @Inject
    private EntityManager entityManager;

    public List<Place> findAll() {
        var query = entityManager.createQuery("FROM Place", Place.class);
        return query.getResultList();
    }

    @Transactional
    public Place createPlace(Place place) {
        entityManager.persist(place);
        return place;
    }

    @Transactional
    public void deletePlace(long id) {
        entityManager.remove(getPlaceById(id));
    }

    @Transactional
    public void updatePlace(Place place){
        entityManager.merge(place);
    }

    public Place getPlaceById(long id) {
        return entityManager.find(Place.class, id);
    }
}
