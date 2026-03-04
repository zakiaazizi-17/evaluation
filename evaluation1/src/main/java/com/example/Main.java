package ma.projet;

import ma.projet.classes.Produit;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.service.ProduitService;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProduitService ps = new ProduitService();

        // 1️⃣ Création de produits
        Produit p1 = new Produit("ES12", 120);
        Produit p2 = new Produit("ZR85", 100);
        Produit p3 = new Produit("EE85", 200);

        ps.create(p1);
        ps.create(p2);
        ps.create(p3);

        // 2️⃣ Création d'une commande
        Commande c1 = new Commande(LocalDate.of(2013, 3, 14));

        // Persister la commande pour obtenir l'id
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(c1);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3️⃣ Création des lignes de commande
        LigneCommande lc1 = new LigneCommande(p1, c1, 7);
        LigneCommande lc2 = new LigneCommande(p2, c1, 14);
        LigneCommande lc3 = new LigneCommande(p3, c1, 5);

        // Ajouter les lignes aux produits et commande
        p1.getLignesCommandes().add(lc1);
        p2.getLignesCommandes().add(lc2);
        p3.getLignesCommandes().add(lc3);

        c1.getLignesCommandes().add(lc1);
        c1.getLignesCommandes().add(lc2);
        c1.getLignesCommandes().add(lc3);

        // Persister les lignes
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(lc1);
            session.persist(lc2);
            session.persist(lc3);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4️⃣ Afficher les produits d'une commande
        List<LigneCommande> lignes = ps.findProduitsByCommande(c1.getId());

        System.out.println("Commande : " + c1.getId() + "     Date : " + c1.getDateCommande());
        System.out.println("Liste des produits :");
        System.out.println("Référence   Prix    Quantité");
        for (LigneCommande lc : lignes) {
            System.out.println(lc.getProduit().getReference() + "      " +
                    lc.getProduit().getPrix() + " DH  " +
                    lc.getQuantite());
        }

        // 5️⃣ Produits commandés entre 2 dates
        List<Produit> produitsDate = ps.findProduitsByDate(
                LocalDate.of(2013,3,1),
                LocalDate.of(2013,3,31)
        );
        System.out.println("\nProduits commandés en mars 2013 :");
        produitsDate.forEach(p -> System.out.println(p.getReference() + " " + p.getPrix()));

        // 6️⃣ Produits avec prix > 100 DH
        List<Produit> produitsPrix = ps.findProduitsPrixSup100();
        System.out.println("\nProduits avec prix > 100 DH :");
        produitsPrix.forEach(p -> System.out.println(p.getReference() + " " + p.getPrix()));
    }
}