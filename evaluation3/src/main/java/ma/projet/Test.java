package ma.projet;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();


        Date dateNaissanceRef = sdf.parse("01/01/1980");
        String[] prenomsFemmes = {"Fatima", "Amina", "Khadija", "Sara", "Imane", "Nadia", "Samira", "Leila", "Hind", "Meryem"};
        String[] nomsFemmes = {"Alami", "Benali", "El Fassi", "Mansouri", "Tazi", "Berrada", "Bennani", "Joundi", "Skalli", "Bensouda"};

        for (int i = 0; i < prenomsFemmes.length; i++) {
            Femme f = new Femme(nomsFemmes[i], prenomsFemmes[i], "06000000" + (i+1), "Adresse " + (i+1),
                    new Date(dateNaissanceRef.getTime() + (i * 1000L * 60 * 60 * 24 * 365)));
            femmeService.create(f);
        }

        String[] prenomsHommes = {"Mohamed", "Ahmed", "Hassan", "Youssef", "Omar"};
        String[] nomsHommes = {"El Amrani", "Boukhriss", "Fahmi", "Guedira", "Lahlou"};

        for (int i = 0; i < prenomsHommes.length; i++) {
            Homme h = new Homme(nomsHommes[i], prenomsHommes[i], "07000000" + (i+1), "Adresse " + (i+1),
                    new Date(dateNaissanceRef.getTime() + (i * 1000L * 60 * 60 * 24 * 365)));
            hommeService.create(h);
        }


        List<Homme> hommes = hommeService.findAll();
        List<Femme> femmes = femmeService.findAll();


        Homme h1 = hommes.get(0);
        Homme h2 = hommes.get(1);
        Femme f1 = femmes.get(0);
        Femme f2 = femmes.get(1);
        Femme f3 = femmes.get(2);
        Femme f4 = femmes.get(3);
        Femme f5 = femmes.get(4);

        Date debut1 = sdf.parse("01/01/2000");
        Date fin1 = sdf.parse("01/01/2005");
        Date debut2 = sdf.parse("15/03/2006");
        Date debut3 = sdf.parse("20/07/2010");
        Date fin3 = sdf.parse("20/07/2015");
        Date debut4 = sdf.parse("10/10/2018");

        mariageService.create(new Mariage(h1, f1, debut1, fin1, 2));
        mariageService.create(new Mariage(h1, f2, debut2, null, 3));
        mariageService.create(new Mariage(h1, f3, debut3, fin3, 1));
        mariageService.create(new Mariage(h1, f4, debut4, null, 4));
        mariageService.create(new Mariage(h2, f5, debut1, null, 0));
        Date secondMariage = sdf.parse("01/01/2015");
        mariageService.create(new Mariage(h2, f1, secondMariage, null, 1));

        System.out.println("--- Liste des femmes ---");
        for (Femme f : femmeService.findAll()) {
            System.out.println(f.getPrenom() + " " + f.getNom() + " - " + f.getDateNaissance());
        }


        Femme plusAgee = null;
        for (Femme f : femmeService.findAll()) {
            if (plusAgee == null || f.getDateNaissance().before(plusAgee.getDateNaissance())) {
                plusAgee = f;
            }
        }
        System.out.println("\n--- Femme la plus âgée ---");
        System.out.println(plusAgee.getPrenom() + " " + plusAgee.getNom() + " née le " + sdf.format(plusAgee.getDateNaissance()));


        System.out.println("\n--- Épouses de " + h1.getPrenom() + " " + h1.getNom() + " entre 2000 et 2010 ---");
        Date debutPeriode = sdf.parse("01/01/2000");
        Date finPeriode = sdf.parse("31/12/2010");
        hommeService.afficherEpousesEntreDates(h1.getId(), debutPeriode, finPeriode);


        System.out.println("\n--- Nombre d'enfants de " + f1.getPrenom() + " " + f1.getNom() + " entre 2000 et 2005 ---");
        int nbEnfants = femmeService.nombreEnfantsEntreDates(f1.getId(), debutPeriode, sdf.parse("31/12/2005"));
        System.out.println("Total enfants : " + nbEnfants);


        System.out.println("\n--- Femmes mariées au moins deux fois ---");
        List<Femme> femmesMariees2Plus = femmeService.femmesMarieesAuMoinsDeuxFois();
        for (Femme f : femmesMariees2Plus) {
            System.out.println(f.getPrenom() + " " + f.getNom());
        }

        System.out.println("\n--- Nombre d'hommes mariés à quatre femmes entre 2000 et 2020 (via Criteria) ---");
        long nbHommes = hommeService.compterHommesMarieQuatreFemmesEntreDates(sdf.parse("01/01/2000"), sdf.parse("31/12/2020"));
        System.out.println("Résultat : " + nbHommes);


        System.out.println("\n--- Détail des mariages de " + h1.getPrenom() + " " + h1.getNom() + " ---");
        hommeService.afficherMariagesHomme(h1.getId());


        HibernateUtil.shutdown();
    }
}