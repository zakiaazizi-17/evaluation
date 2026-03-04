package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.beans.MariageId;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class MariageService extends AbstractService<Mariage, MariageId> {


    public List<Mariage> findMariagesByHomme(int hommeId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Mariage> query = em.createQuery(
                    "SELECT m FROM Mariage m WHERE m.id.homme.id = :hommeId", Mariage.class);
            query.setParameter("hommeId", hommeId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    public List<Mariage> findMariagesByFemme(int femmeId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Mariage> query = em.createQuery(
                    "SELECT m FROM Mariage m WHERE m.id.femme.id = :femmeId", Mariage.class);
            query.setParameter("femmeId", femmeId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    public List<Mariage> findMariagesBetweenDates(Date debut, Date fin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Mariage> query = em.createQuery(
                    "SELECT m FROM Mariage m WHERE m.id.dateDebut BETWEEN :debut AND :fin", Mariage.class);
            query.setParameter("debut", debut);
            query.setParameter("fin", fin);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


}