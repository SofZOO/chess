package appli;

import piece.FabriquePiece;
import échiquier.Plateau;

public class Main {
    public static void jouer(){

    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {

        Joueur jBlanc = new Joueur(true,new FabriquePiece());
        Joueur jNoir = new Joueur(false,new FabriquePiece());

        Plateau p = new Plateau(jBlanc,jNoir);

        System.out.println(p);
        p.déplacer("a8a6");
        System.out.println(p);
        p.déplacer("a6d6");
        System.out.println(p);
        p.déplacer("e1d1");
        System.out.println(p);

    }
}
