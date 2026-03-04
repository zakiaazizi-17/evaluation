package ma.projet.classes;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
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

    public Set<EmployeTache> getEmployeTaches() {
        return employeTaches;
    }

    public void setEmployeTaches(Set<EmployeTache> employeTaches) {
        this.employeTaches = employeTaches;
    }
}