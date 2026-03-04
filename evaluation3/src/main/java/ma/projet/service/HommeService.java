package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class HommeService extends AbstractService<Homme, Integer> {


    public void afficherEpousesEntreDates(int hommeId, Date debut, Date fin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Mariage> query = em.createQuery(
                    "SELECT m FROM Mariage m WHERE m.id.homme.id = :hommeId " +
                            "AND m.id.dateDebut BETWEEN :debut AND :fin", Mariage.class);
            query.setParameter("hommeId", hommeId);
            query.setParameter("debut", debut);
            query.setParameter("fin", fin);
            List<Mariage> mariages = query.getResultList();
            System.out.println("Épouses de l'homme " + hommeId + " entre " + debut + " et " + fin + " :");
            for (Mariage m : mariages) {
                System.out.println(" - " + m.getFemme().getPrenom() + " " + m.getFemme().getNom()
                        + " (début: " + m.getDateDebut() + ")");
            }
        } finally {
            em.close();
        }
    }


    public long compterHommesMarieQuatreFemmesEntreDates(Date debut, Date fin) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.criteria.CriteriaBuilder cb = em.getCriteriaBuilder();
            javax.persistence.criteria.CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            javax.persistence.criteria.Root<Mariage> root = cq.from(Mariage.class);

            cq.select(cb.countDistinct(root.get("id").get("homme")));
            cq.where(cb.and(
                    cb.between(root.get("id").get("dateDebut"), debut, fin),
                    cb.equal(root.get("nbrEnfant"), 4)  // condition "quatre femmes" ? Le libellé est ambigu.

            ));
            return em.createQuery(cq).getSingleResult();
        } finally {
            em.close();
        }
    }


    public void afficherMariagesHomme(int hommeId) {
        EntityManager em = getEntityManager();
        try {
            Homme homme = em.find(Homme.class, hommeId);
            if (homme == null) {
                System.out.println("Homme introuvable");
                return;
            }
            System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());
            List<Mariage> mariages = homme.getMariages();

            System.out.println("Mariages En Cours :");
            int index = 1;
            for (Mariage m : mariages) {
                if (m.getDateFin() == null) {
                    System.out.printf("%d. Femme : %s %s   Date Début : %td/%tm/%tY    Nbr Enfants : %d%n",
                            index++, m.getFemme().getPrenom(), m.getFemme().getNom(),
                            m.getDateDebut(), m.getDateDebut(), m.getDateDebut(),
                            m.getNbrEnfant());
                }
            }

            System.out.println("Mariages échoués :");
            index = 1;
            for (Mariage m : mariages) {
                if (m.getDateFin() != null) {
                    System.out.printf("%d. Femme : %s %s   Date Début : %td/%tm/%tY    Date Fin : %td/%tm/%tY    Nbr Enfants : %d%n",
                            index++, m.getFemme().getPrenom(), m.getFemme().getNom(),
                            m.getDateDebut(), m.getDateDebut(), m.getDateDebut(),
                            m.getDateFin(), m.getDateFin(), m.getDateFin(),
                            m.getNbrEnfant());
                }
            }
        } finally {
            em.close();
        }
    }


}