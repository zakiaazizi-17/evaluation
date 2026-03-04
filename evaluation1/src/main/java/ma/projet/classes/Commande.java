package ma.projet.classes;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCommande;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private Set<LigneCommande> lignesCommandes = new HashSet<>();

    public Commande() {}
    public Commande(LocalDate dateCommande) { this.dateCommande = dateCommande; }

    // Getters et Setters
    public Long getId() { return id; }
    public LocalDate getDateCommande() { return dateCommande; }
    public void setDateCommande(LocalDate dateCommande) { this.dateCommande = dateCommande; }
    public Set<LigneCommande> getLignesCommandes() { return lignesCommandes; }
    public void setLignesCommandes(Set<LigneCommande> lignesCommandes) { this.lignesCommandes = lignesCommandes; }
}