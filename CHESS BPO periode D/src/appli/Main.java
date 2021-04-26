package appli;

import piece.FabriquePiece;
import échiquier.Plateau;


public class Main {
    public static void jouer(){

    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {

        Joueur joueurBlanc = new Joueur("BLANC",true,new FabriquePiece());
        Joueur joueurNoir = new Joueur("NOIR",false,new FabriquePiece());

        Plateau p = new Plateau(joueurBlanc,joueurNoir);

        System.out.println(p);
        p.déplacer("a8a6",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("a6d6",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("d6d1",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("e1e2",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("a1a8",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("d1d6",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("d1d8",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("e8e7",joueurNoir,joueurBlanc);
        System.out.println(p);
    }
}
