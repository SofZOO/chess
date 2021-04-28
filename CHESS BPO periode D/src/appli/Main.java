package appli;


import com.sun.security.jgss.GSSUtil;
import piece.FabriquePiece;
import échiquier.Plateau;

import java.util.Arrays;
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
         p.déplacer("a1a7",joueurBlanc,joueurNoir);
         System.out.println(p);
        p.déplacer("e1e2",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("e2e3",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("e3e4",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("e4e5",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("e5e6",joueurBlanc,joueurNoir);
        System.out.println(p);
        /*p.déplacer("a7a8",joueurBlanc,joueurNoir);
        System.out.println(p);*///TODO : c la merde



    }
}
