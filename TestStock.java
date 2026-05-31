import java.util.Scanner;

// =========================
// Classe d'exécution
// =========================
public class TestStock {
    public static void main(String[] args) {
        Stock stock = new Stock(5); // stock de taille 5
        stock.afficheMenu();
    }
}

// =========================
// Classe Produit
// =========================
class Produit {
    private String reference;
    private int dateEntree;

    // Constructeur : reçoit la date du jour et lit la référence au clavier
    public Produit(int dateJour) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer la reference du produit : ");
        this.reference = sc.nextLine();
        this.dateEntree = dateJour;
    }

    public int getDateEntree() {
        return dateEntree;
    }

    public String getReference() {
        return reference;
    }

    public void afficher() {
        System.out.println("Produit [ref=" + reference + ", dateEntree=" + dateEntree + "]");
    }

    @Override
    public String toString() {
        return "Produit [ref=" + reference + ", dateEntree=" + dateEntree + "]";
    }
}

// =========================
// Classe Pile
// =========================
class Pile {
    private Produit[] tab;
    private int indice; // -1 = pile vide

    public Pile(int max) {
        tab = new Produit[max];
        indice = -1;
    }

    public boolean pileVide() {
        return indice == -1;
    }

    public boolean pilePleine() {
        return indice == tab.length - 1;
    }

    public void empiler(Produit p) {
        if (pilePleine()) {
            System.out.println("Stock plein : impossible d'ajouter !");
        } else {
            indice++;
            tab[indice] = p;
        }
    }

    public void depiler() {
        if (pileVide()) {
            System.out.println("Pile vide : rien a retirer !");
        } else {
            tab[indice] = null;
            indice--;
        }
    }

    public Produit sommet() {
        if (pileVide()) return null;
        return tab[indice];
    }

    public void afficherStock() {
        if (pileVide()) {
            System.out.println("Stock vide.");
            return;
        }

        System.out.println("=== Stock (sommet -> bas) ===");
        for (int i = indice; i >= 0; i--) {
            System.out.println(tab[i]);
        }
    }
}

// =========================
// Classe Stock
// =========================
class Stock {
    private Pile pile;
    private int dateJour;

    public Stock(int taille) {
        pile = new Pile(taille);
        dateJour = 1;
    }

    // Entrer un produit dans le stock
    public void entrer(Produit p) {
        if (pile.pilePleine()) {
            System.out.println("Stock plein : entree impossible !");
        } else {
            pile.empiler(p);
            System.out.println("Produit " + p.getReference() + " ajouté au stock");
        }
    }

    // Sortir le produit le plus frais (sommet)
    // dateJ = date du jour
    public void sortir(int dateJ) {
        if (pile.pileVide()) {
            System.out.println("Stock vide : aucun produit a sortir.");
            return;
        }

        // On retire les produits périmés en premier
        while (!pile.pileVide()) {
            Produit p = pile.sommet();
            int age = dateJ - p.getDateEntree();

            if (age > 5) {
                System.out.println("Produit " + p.getReference() + " périmé, stock intégralement supprimé");
                pile.depiler();
            } else {
                System.out.println("Produit " + p.getReference() + " sorti du stock");
                pile.depiler();
                return;
            }
        }

        // Si on arrive ici, tout était périmé
        System.out.println("Tous les produits etaient perimes. Stock vide maintenant.");
    }

    // Incrémenter la date du jour
    public void incrementerDate() {
        dateJour++;
        System.out.println("Date du jour incrementee : " + dateJour);
    }

    // Afficher menu + gestion utilisateur
    public void afficheMenu() {
        Scanner sc = new Scanner(System.in);
        char choix;

        do {
            System.out.println("\n===== MENU STOCK =====");
            System.out.println("e : entrée d’un produit dans le stock");
            System.out.println("s : sortie d’un produit du stock");
            System.out.println("i : incrémenter la date du jour");
            System.out.println("a : afficher stock");
            System.out.println("q : quitter");
            System.out.print("Votre choix : ");

            choix = sc.nextLine().toLowerCase().charAt(0);

            switch (choix) {
                case 'e':
                    Produit p = new Produit(dateJour);
                    entrer(p);
                    break;

                case 's':
                    sortir(dateJour);
                    break;

                case 'i':
                    incrementerDate();
                    break;

                case 'a':
                    pile.afficherStock();
                    break;

                case 'q':
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }

        } while (choix != 'q');
    }
}