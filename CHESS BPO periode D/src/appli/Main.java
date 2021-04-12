package appli;

import échiquier.Plateau;

public class Main {
    public static void jouer(){

    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {
        Plateau p = new Plateau();
        p.initialiserEchiquier();
        System.out.println(p);
        p.déplacer("e1e2");
        System.out.println(p);
        p.déplacer("a8a6");
        System.out.println(p);
        p.déplacer("e2e4");
        System.out.println(p);
        p.déplacer("a6b5");
        System.out.println(p);
        p.déplacer("a1a6");
        System.out.println(p);
        p.déplacer("a6a2");
        System.out.println(p);
        p.déplacer("a2e2");
        System.out.println(p);
        p.déplacer("a2d2");
        System.out.println(p);
        p.déplacer("d2a2");
        System.out.println(p);
        p.déplacer("a2a6");
        System.out.println(p);
        p.déplacer("a6a8");
        System.out.println(p);
        p.déplacer("a8e8");
        System.out.println(p);
        p.déplacer("e8h8");
        System.out.println(p);
        p.déplacer("h8e8");
        System.out.println(p);
    }
}
