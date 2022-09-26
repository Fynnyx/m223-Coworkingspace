package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Role;


@ApplicationScoped
public class RoleService {
    @Inject
    private EntityManager entityManager;

    public List<Role> findAll() {
        var query = entityManager.createQuery("FROM Role", Role.class);
        return query.getResultList();
    }

    @Transactional
    public Role createRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Transactional
    public void deleteRole(long id) {
        entityManager.remove(getRoleById(id));
    }

    @Transactional
    public void updateRole(Role role){
        entityManager.merge(role);
    }

    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }
}
