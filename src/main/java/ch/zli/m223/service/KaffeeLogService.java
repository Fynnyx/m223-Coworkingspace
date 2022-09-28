package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import ch.zli.m223.model.KaffeeLog;


@ApplicationScoped
public class KaffeeLogService {
    @Inject
    private EntityManager entityManager;

    public List<KaffeeLog> findAll() {
        var query = entityManager.createQuery("FROM KaffeeLog", KaffeeLog.class);
        return query.getResultList();
    }

    @Transactional
    public KaffeeLog createKaffeeLog(@Valid KaffeeLog kaffeeLog) {
        entityManager.persist(kaffeeLog);
        return kaffeeLog;
    }

    @Transactional
    public void deleteKaffeeLog(long id) {
        entityManager.remove(getKaffeeLogById(id));
    }

    @Transactional
    public void updateKaffeeLog(KaffeeLog kaffeeLog){
        entityManager.merge(kaffeeLog);
    }

    public KaffeeLog getKaffeeLogById(long id) {
        return entityManager.find(KaffeeLog.class, id);
    }
}
