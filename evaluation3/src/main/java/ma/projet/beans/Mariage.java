package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mariage {
    @EmbeddedId
    private MariageId id;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private int nbrEnfant;

    public Mariage() {}

    public Mariage(Homme homme, Femme femme, Date dateDebut, Date dateFin, int nbrEnfant) {
        this.id = new MariageId(homme, femme, dateDebut);
        this.dateFin = dateFin;
        this.nbrEnfant = nbrEnfant;
    }


    public MariageId getId() { return id; }
    public void setId(MariageId id) { this.id = id; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public int getNbrEnfant() { return nbrEnfant; }
    public void setNbrEnfant(int nbrEnfant) { this.nbrEnfant = nbrEnfant; }


    public Homme getHomme() { return id.getHomme(); }
    public Femme getFemme() { return id.getFemme(); }
    public Date getDateDebut() { return id.getDateDebut(); }
}