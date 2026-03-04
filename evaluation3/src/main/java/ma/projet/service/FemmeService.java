package ma.projet.service;

import ma.projet.beans.Femme;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class FemmeService extends AbstractService<Femme, Integer> {


    public int nombreEnfantsEntreDates(int femmeId, Date debut, Date fin) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Femme.nombreEnfantsEntreDates");
            query.setParameter("femmeId", femmeId);
            query.setParameter("debut", debut);
            query.setParameter("fin", fin);
            Object result = query.getSingleResult();
            if (result == null) return 0;
            // Le résultat peut être un Integer ou un Object[] selon la version
            if (result instanceof Object[]) {
                return ((Number) ((Object[]) result)[0]).intValue();
            } else if (result instanceof Number) {
                return ((Number) result).intValue();
            }
            return 0;
        } finally {
            em.close();
        }
    }


    public List<Femme> femmesMarieesAuMoinsDeuxFois() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Femme> query = em.createNamedQuery("Femme.marieesAuMoinsDeuxFois", Femme.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}