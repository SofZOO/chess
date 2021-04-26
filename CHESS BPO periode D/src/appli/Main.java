package appli;

import piece.FabriquePiece;
import échiquier.Plateau;


public class Main {
    public static void jouer(){

    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {

        Joueur jBlanc = new Joueur("BLANC",true,new FabriquePiece());
        Joueur jNoir = new Joueur("NOIR",false,new FabriquePiece());

        Plateau p = new Plateau(jBlanc,jNoir);

        System.out.println(p);
        p.déplacer("a8a6",jNoir,jBlanc);
        System.out.println(p);
        p.déplacer("a6d6",jNoir,jBlanc);
        System.out.println(p);
        p.déplacer("d6d1",jNoir,jBlanc);
        System.out.println(p);
        p.déplacer("e1e2",jBlanc,jNoir);
        System.out.println(p);
        p.déplacer("a1a8",jNoir,jBlanc);
        System.out.println(p);
        p.déplacer("d1d6",jNoir,jBlanc);
        System.out.println(p);
        p.déplacer("d1d8",jNoir,jBlanc);
        System.out.println(p);
        p.déplacer("e8e7",jNoir,jBlanc);
        System.out.println(p);
    }
}
