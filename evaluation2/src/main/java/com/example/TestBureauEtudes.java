package com.example;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestBureauEtudes {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ProjetService projetService = new ProjetService();
        EmployeService employeService = new EmployeService();
        TacheService tacheService = new TacheService();

        // Création projet
        Projet p1 = new Projet();
        p1.setNom("Gestion de stock");
        p1.setDateDebut(sdf.parse("14/01/2013"));
        projetService.create(p1);

        // Création tâches
        Tache t1 = new Tache();
        t1.setNom("Analyse");
        t1.setDateDebutReelle(sdf.parse("10/02/2013"));
        t1.setDateFinReelle(sdf.parse("20/02/2013"));
        t1.setProjet(p1);

        Tache t2 = new Tache();
        t2.setNom("Conception");
        t2.setDateDebutReelle(sdf.parse("10/03/2013"));
        t2.setDateFinReelle(sdf.parse("15/03/2013"));
        t2.setProjet(p1);

        Tache t3 = new Tache();
        t3.setNom("Développement");
        t3.setDateDebutReelle(sdf.parse("10/04/2013"));
        t3.setDateFinReelle(sdf.parse("25/04/2013"));
        t3.setProjet(p1);


        tacheService.create(t1);
        tacheService.create(t2);
        tacheService.create(t3);
        projetService.afficherTachesParProjet(p1.getId());
    }

}