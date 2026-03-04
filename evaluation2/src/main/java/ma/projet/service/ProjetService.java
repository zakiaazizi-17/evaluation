package ma.projet.service;
import ma.projet.dao.IDao;
import ma.projet.classes.Projet;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProjetService implements IDao<Projet> {
    @Override
    public void create(Projet p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(p);
        t.commit();
        s.close();
    }

    @Override
    public void delete(Projet p) {}
    @Override
    public void update(Projet p) {}
    @Override
    public Projet findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Projet p = s.get(Projet.class, id);
        s.close();
        return p;
    }
    @Override
    public List<Projet> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Projet> list = s.createQuery("from Projet", Projet.class).list();
        s.close();
        return list;
    }

    public void afficherTachesParProjet(Long projetId) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Projet p = s.get(Projet.class, projetId);

        System.out.println("Projet : " + p.getId() +
                "  Nom : " + p.getNom() +
                "  Date début : " + p.getDateDebut());

        System.out.println("Liste des tâches:");
        System.out.println("Num Nom            Date Début Réelle   Date Fin Réelle");

        p.getTaches().forEach(t -> {
            System.out.printf("%d  %-12s %-15s %-15s\n",
                    t.getId(),
                    t.getNom(),
                    t.getDateDebutReelle(),
                    t.getDateFinReelle());
        });

        s.close();   // 🔥 IMPORTANT : on ferme après utilisation
    }
}