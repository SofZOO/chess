package joueurs;

import echiquier.IFabriquePiece;
import echiquier.IJoueur;
import echiquier.Plateau;

import java.util.Locale;
import java.util.Scanner;

public class Humain extends Joueur {

    public Humain(String nom, boolean blanc, IFabriquePiece fab) {
        super(nom, blanc, fab);
    }


    @Override
    public void joue(IJoueur autreJoueur, Plateau p) {
        Scanner sc = new Scanner(System.in);
        String coup;
        while (true) {
            System.out.println("Tour du joueur " + this.getNom());
             coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
             if(coup.equals("abandon")){
                 System.out.print("Le joueur " + this.getNom() +" a décidé d'abandonner la partie. Le joueur : " + autreJoueur.getNom() + " a donc " +
                         "gagné par forfait !");
                 System.exit(0);
             }
            if (p.doitRejouer(coup, this)) {
                System.out.println("Mauvais coup pour le joueur " + this.getNom());
            } else {
                déplacer(coup, autreJoueur, p);
                break;
            }
        }
    }
}
