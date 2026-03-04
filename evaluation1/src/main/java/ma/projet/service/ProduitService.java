package ma.projet.service;

import ma.projet.classes.Produit;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public void create(Produit p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        } catch (Exception e) { if (tx != null) tx.rollback(); e.printStackTrace(); }
    }

    @Override
    public void update(Produit p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
        } catch (Exception e) { if (tx != null) tx.rollback(); e.printStackTrace(); }
    }

    @Override
    public void delete(Produit p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(p);
            tx.commit();
        } catch (Exception e) { if (tx != null) tx.rollback(); e.printStackTrace(); }
    }

    @Override
    public Produit findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Produit.class, id);
        }
    }

    @Override
    public List<Produit> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Produit", Produit.class).list();
        }
    }

    // 1. Produits commandés entre 2 dates
    public List<Produit> findProduitsByDate(LocalDate d1, LocalDate d2) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT lc.produit FROM LigneCommande lc " +
                    "WHERE lc.commande.dateCommande BETWEEN :d1 AND :d2";
            return session.createQuery(hql, Produit.class)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();
        }
    }

    // 2. Produits d’une commande donnée
    public List<LigneCommande> findProduitsByCommande(Long commandeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM LigneCommande lc WHERE lc.commande.id = :id";
            return session.createQuery(hql, LigneCommande.class)
                    .setParameter("id", commandeId)
                    .list();
        }
    }

    // 3. Produits avec prix > 100 DH (requête nommée)
    public List<Produit> findProduitsPrixSup100() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Produit.findByPrixSup100", Produit.class).list();
        }
    }
}