package ma.projet.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class MariageId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "homme_id")
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme_id")
    private Femme femme;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    public MariageId() {}

    public MariageId(Homme homme, Femme femme, Date dateDebut) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
    }

    // Getters et setters
    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }

    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MariageId mariageId = (MariageId) o;
        return Objects.equals(homme, mariageId.homme) &&
                Objects.equals(femme, mariageId.femme) &&
                Objects.equals(dateDebut, mariageId.dateDebut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homme, femme, dateDebut);
    }
}