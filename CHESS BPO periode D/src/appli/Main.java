package appli;

import piece.FabriquePiece;


import java.util.Locale;
import echiquier.Plateau;

import java.util.Scanner;


public class Main {
    public static void jouer(){
        Joueur jBlanc = new Joueur("BLANC",true,new FabriquePiece());
        Joueur jNoir = new Joueur("NOIR",false,new FabriquePiece());
        Plateau p = new Plateau(jBlanc,jNoir);
        System.out.println(p);
        Scanner sc = new Scanner(System.in);
        String coup = "";
        String choix;
        while (!coup.equals("fin")){
            System.out.println("Quel joueur souhaitez vous choisir : 1 = joueurBlanc, 2 = joueurNoir");
             choix = sc.nextLine().trim();
            System.out.println("Faites votre coup svp");
             if (choix.equals("1")){
                 coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                 p.déplacer(coup,jBlanc,jNoir);
             }
             else if (choix.equals("2")) {
                 coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                 p.déplacer(coup,jNoir,jBlanc);
             }
            p.déplacer(coup,jBlanc,jNoir);
            System.out.println(p);
            if(coup.equals("fin")) {
                System.out.println("fin du test ma gueule");
                System.exit(0);
                break;
            }
        }
        System.out.println("fin du test");
    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {
        jouer();
    }
}
