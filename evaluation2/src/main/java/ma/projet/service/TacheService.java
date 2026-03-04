package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import ma.projet.dao.IDao;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override public void delete(Tache t) {}
    @Override public void update(Tache t) {}
    @Override public Tache findById(Long id) {return null;}
    @Override public List<Tache> findAll() {return null;}

    public List<Tache> findTachesPrixSup1000() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> q = s.createNamedQuery("Tache.findByPrixSup1000", Tache.class);
        List<Tache> list = q.list();
        s.close();
        return list;
    }

    public List<Tache> findTachesEntreDates(Date start, Date end) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<Tache> q = s.createNamedQuery("Tache.findByDateBetween", Tache.class);
        q.setParameter("start", start);
        q.setParameter("end", end);
        List<Tache> list = q.list();
        s.close();
        return list;
    }
    @Override
    public void create(Tache tache) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(tache);
        tx.commit();
        s.close();
    }
}