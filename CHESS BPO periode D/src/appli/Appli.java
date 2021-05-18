package appli;

import echiquier.IJoueur;
import echiquier.Plateau;
import joueurs.Bot;
import joueurs.Humain;
import piece.FabriquePiece;
import java.util.Scanner;

public class Appli {

    private static boolean erreurChoix(String choix){
        if (choix.length() !=1){
            return true;
        }
        if(!Character.isDigit(choix.charAt(0))){
         return true;
        }
        return Integer.parseInt(choix) > 3 || Integer.parseInt(choix) < 1;
    }

    private static boolean finPartie(IJoueur jBlanc, IJoueur jNoir, Plateau p, boolean echecEtMat, String nom, String autreNom) {

        System.out.println(p.affichePlateau(jBlanc, jNoir));
        if (echecEtMat) {
            System.out.println(p.partieFinie(2,nom,autreNom));
            return true;
        }
        if (p.partieNulle(jBlanc,jNoir)) {
            //todo:nul
            System.out.println("la partie est nulle");
            return true;
        }
        if(jBlanc.abandonne()){
            System.out.println("Le joueur " +jBlanc.getNom() +" a décidé d'abandonner la partie.  Le joueur " + jNoir.getNom()+ " a gagné par \"forfait\"!");
            return true;
        }
        if(jNoir.abandonne()){
            System.out.println("Le joueur " +jNoir.getNom() +" a décidé d'abandonner la partie. Le joueur " + jBlanc.getNom()+ " a gagné par \"forfait\"!");
            return true;
        }
        return false;
    }

    public static void jouer(IJoueur jBlanc, IJoueur jNoir, Plateau p) {

        System.out.println(p.affichePlateau(jBlanc, jNoir));

        while (!(jBlanc.isChessMat() || jNoir.isChessMat())) {
            jBlanc.joueUnTour(jNoir, p);

            if (finPartie(jBlanc, jNoir, p, jNoir.isChessMat(), jNoir.getNom(), jBlanc.getNom())) break;

            jNoir.joueUnTour(jBlanc, p);

            if (finPartie(jBlanc, jNoir, p, jBlanc.isChessMat(), jBlanc.getNom(), jNoir.getNom())) break;
        }
        System.out.println("fin du test");
    }


    public static void main(String[] args) {
        IJoueur joueurBlanc;
        IJoueur joueurNoir;

        System.out.println("""
                ********************************************************************************************************
                Bienvenue sur le menu de sélection d'adversaire, il suffit de taper : 1 pour le mode joueur VS joueur  *
                                                                                      2 pour le mode joueur VS bot     *
                                                                                      3 pour le mode bot VS bot        *
                ********************************************************************************************************""");

        Scanner sc = new Scanner(System.in);
        String choix;
        choix = sc.nextLine().trim();
        while(erreurChoix(choix)){
            System.out.println("Vous venez de faire une erreur sur la saisie de votre choix, il faut entrer une des 3 options et vous avez marqué : " + choix);
            choix=sc.nextLine().trim();
        }

        switch (choix) {
            case ("1"): {
                joueurBlanc = new Humain(true, new FabriquePiece());
                joueurNoir = new Humain(false, new FabriquePiece());
                break;

            }
            case ("2"): {
                joueurBlanc = new Humain(true, new FabriquePiece());
                joueurNoir = new Bot(false, new FabriquePiece());
                break;

            }
            case ("3"): {
                joueurBlanc = new Bot(true, new FabriquePiece());
                joueurNoir = new Bot(false, new FabriquePiece());
                break;

            }
            default: {
                joueurBlanc = new Humain(true, new FabriquePiece());
                joueurNoir = new Humain(false, new FabriquePiece());
            }
        }
        Plateau p = new Plateau(joueurBlanc, joueurNoir);
        jouer(joueurBlanc, joueurNoir, p);

    }
}