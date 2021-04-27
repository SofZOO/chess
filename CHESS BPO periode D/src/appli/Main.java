package appli;


import piece.FabriquePiece;
import échiquier.Plateau;

import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void jouer(){
        Joueur jBlanc = new Joueur("BLANC",true,new FabriquePiece());
        Joueur jNoir = new Joueur("NOIR",false,new FabriquePiece());

        Plateau p = new Plateau(jBlanc,jNoir);
        System.out.println(p);
        Scanner sc = new Scanner(System.in);
        String coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        while (!coup.equals("fin")){
            p.déplacer(coup,jBlanc,jNoir);
            System.out.println(p);
            coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
            if(coup.equals("fin")) {
                System.out.println("fin du test ma gueule");
                System.exit(0);
                break;
            }
            System.out.println(coup);
            p.déplacer(coup,jNoir,jBlanc);
            System.out.println(p);

        }
        System.out.println("fin du test");
    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {

        //jouer();

        Joueur joueurBlanc = new Joueur("BLANC",true,new FabriquePiece());
        Joueur joueurNoir = new Joueur("NOIR",false,new FabriquePiece());

        Plateau p = new Plateau(joueurBlanc,joueurNoir);

        System.out.println(p);
        p.déplacer("a8a7",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("a1a3",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("a7e7",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("a3e3",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("e3d3",joueurBlanc,joueurNoir);
        System.out.println(p);



    }
}
