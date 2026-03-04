package ma.projet.classes;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name="Tache.findByPrixSup1000", query="SELECT t FROM Tache t WHERE t.prix > 1000"),
        @NamedQuery(name="Tache.findByDateBetween", query="SELECT t FROM Tache t WHERE t.dateDebutReelle BETWEEN :start AND :end")
})
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Date dateDebutPlanifiee;
    private Date dateDebutReelle;
    private Date dateFinReelle;
    private double prix;

    @ManyToOne
    private Projet projet;

    @OneToMany(mappedBy = "tache", cascade = CascadeType.ALL)
    private Set<EmployeTache> employeTaches;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebutPlanifiee() {
        return dateDebutPlanifiee;
    }

    public void setDateDebutPlanifiee(Date dateDebutPlanifiee) {
        this.dateDebutPlanifiee = dateDebutPlanifiee;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Set<EmployeTache> getEmployeTaches() {
        return employeTaches;
    }

    public void setEmployeTaches(Set<EmployeTache> employeTaches) {
        this.employeTaches = employeTaches;
    }
}