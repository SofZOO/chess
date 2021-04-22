package appli;

import piece.FabriquePiece;
import échiquier.Plateau;

public class Main {
    public static void jouer(){

    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {
        Plateau p = new Plateau(new FabriquePiece());
        System.out.println(p);
        p.déplacer("a8a7");
        System.out.println(p);
        p.déplacer("a1a8");
        System.out.println(p);
    }
}
