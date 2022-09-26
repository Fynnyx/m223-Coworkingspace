package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.User;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Transactional
    public void updateUser(User user){
        entityManager.merge(user);
    }

    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}
