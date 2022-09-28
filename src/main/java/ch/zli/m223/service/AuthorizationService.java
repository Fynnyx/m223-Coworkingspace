package ch.zli.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.zli.m223.model.User;



@ApplicationScoped
public class AuthorizationService {
    @Inject
    private EntityManager entityManager;

    // get user by email
    public User getUserByEmail(String email) {
        var query = entityManager.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
