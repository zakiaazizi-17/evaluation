package ma.projet.classes;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Produit.findByPrixSup100", query = "SELECT p FROM Produit p WHERE p.prix > 100")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private double prix;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private Set<LigneCommande> lignesCommandes = new HashSet<>();

    public Produit() {}
    public Produit(String reference, double prix) {
        this.reference = reference;
        this.prix = prix;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public Set<LigneCommande> getLignesCommandes() { return lignesCommandes; }
    public void setLignesCommandes(Set<LigneCommande> lignesCommandes) { this.lignesCommandes = lignesCommandes; }
}