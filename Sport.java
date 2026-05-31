import java.util.*;
import java.util.ArrayList;

public class Sport {

    // Variables d'instance
    private int code;
    private String libelle;

    
    public Sport(int code, String libelle) {    // Constructeur avec paramètres
        this.code = code;
        this.libelle = libelle;
    }

    // Accesseur pour le code
    public int getCode() {
        return code;
    }

    // Accesseur pour le libellé
    public String getLibelle() {
        return libelle;
    }

    

    public String toString() {
        return "Sport [code=" + code + ", libelle=" + libelle + "]";  
    }

    // Méthode d'affichage utilisant toString()
    public void affiche() {
        System.out.println(this.toString());
    }
}


class SportCo extends Sport {

   
    private int nbJoueurs;

    // Constructeur
    public SportCo(int code, String libelle, int nbJoueurs) {
        // Appel du constructeur de la classe mère
        super(code, libelle);
        this.nbJoueurs = nbJoueurs;
    }

    // Accesseur
    public int getNbJoueurs() {
        return nbJoueurs;
    }

    // Redéfinition de toString
    public String toString() {
        return super.toString() + ", nbJoueurs=" + nbJoueurs;
    }
}


class LesSports {

    // Liste contenant des Sports
    private ArrayList<Sport> listeSports;

    // Constructeur
    public LesSports() {
        listeSports = new ArrayList<>();
    }

    // Ajoute un sport dans la liste
    public void ajouterSport(Sport s) {
        listeSports.add(s);
    }

    // Affiche tous les sports enregistrés
    public void afficherSports() {
        for (Sport s : listeSports) {
            s.affiche();
        }
    }
}

public class ProgSports {

    public static void main(String[] args) {

        // Création de la collection
        LesSports lesSports = new LesSports();

        // Création de sports
        Sport s1 = new Sport(1, "Tennis");
        Sport s2 = new Sport(2, "Natation");

        // Création de sports collectifs
        SportCo sc1 = new SportCo(3, "Football", 11);
        SportCo sc2 = new SportCo(4, "Basketball", 5);
        SportCo sc3 = new SportCo(5, "Handball", 7);

        // Ajout des sports
        lesSports.ajouterSport(s1);
        lesSports.ajouterSport(s2);
        lesSports.ajouterSport(sc1);
        lesSports.ajouterSport(sc2);
        lesSports.ajouterSport(sc3);

        // Affichage
        lesSports.afficherSports();
    }
}