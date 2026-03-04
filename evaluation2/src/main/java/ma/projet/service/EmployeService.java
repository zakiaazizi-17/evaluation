package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import ma.projet.dao.IDao;

import java.util.List;

public class EmployeService implements IDao<Employe> {
    @Override public void create(Employe e) {}
    @Override public void delete(Employe e) {}
    @Override public void update(Employe e) {}
    @Override public Employe findById(Long id) {return null;}
    @Override public List<Employe> findAll() {return null;}

    public void afficherTachesParEmploye(Long employeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Employe e = s.get(Employe.class, employeId);
        System.out.println("Employé : " + e.getNom());
        e.getEmployeTaches().forEach(et -> {
            System.out.println("Tâche : " + et.getTache().getNom() +
                    "  Date Début : " + et.getDateDebut() +
                    "  Date Fin : " + et.getDateFin());
        });
        s.close();
    }

    public void afficherProjetsParEmploye(Long employeId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Employe e = s.get(Employe.class, employeId);
        System.out.println("Projets gérés par " + e.getNom() + " :");
        e.getEmployeTaches().stream()
                .map(et -> et.getTache().getProjet())
                .distinct()
                .forEach(p -> System.out.println(p.getNom()));
        s.close();
    }
}