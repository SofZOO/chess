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
        if((Integer.parseInt(choix) > 3 || Integer.parseInt(choix)<1))
            return true;
        return false;
    }

    private static boolean finPartie(IJoueur jBlanc, IJoueur jNoir, Plateau p, boolean echecEtMat, String nom, String autreNom) {

        System.out.println(p.affichePlateau(jBlanc, jNoir));
        if (echecEtMat) {
            p.partieFinie(2,nom,autreNom);
            return true;
        }
        if (p.partieNulle(jBlanc,jNoir)) {
            //todo:nul
            System.out.println("la partie est nulle");
            return true;
        }

        if(jBlanc.abandonne()){
            System.out.println("Le joueur " +jBlanc.getNom() +"a décidé d'abandonner la partie.  Le joueur " + jNoir.getNom()+ " a gagné par \\\"forfait\\\"!");
            return true;
        }

        if(jNoir.abandonne()){
            System.out.println("Le joueur " +jNoir.getNom() +"a décidé d'abandonner la partie. Le joueur " + jBlanc.getNom()+ " a gagné par \"forfait\"!");
            return true;
        }


        return false;
    }

    public static void jouer(IJoueur jBlanc, IJoueur jNoir, Plateau p) {

        System.out.println(p.affichePlateau(jBlanc, jNoir));

        while (!jBlanc.getEchecEtMat() || jNoir.getEchecEtMat()) {
            jBlanc.joue(jNoir, p);


            if (finPartie(jBlanc, jNoir, p, jNoir.getEchecEtMat(), jNoir.getNom(), jBlanc.getNom())) break;

            jNoir.joue(jBlanc, p);

            if (finPartie(jBlanc, jNoir, p, jBlanc.getEchecEtMat(), jBlanc.getNom(), jNoir.getNom())) break;
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
                joueurBlanc = new Humain("BLANC", true, new FabriquePiece());
                joueurNoir = new Humain("NOIR", false, new FabriquePiece());
                break;

            }
            case ("2"): {
                joueurBlanc = new Humain("BLANC", true, new FabriquePiece());
                joueurNoir = new Bot("NOIR", false, new FabriquePiece());
                break;

            }
            case ("3"): {
                joueurBlanc = new Bot("BLANC", true, new FabriquePiece());
                joueurNoir = new Bot("NOIR", false, new FabriquePiece());
                break;

            }
            default: {
                joueurBlanc = new Humain("BLANC", true, new FabriquePiece());
                joueurNoir = new Humain("NOIR", false, new FabriquePiece());
            }
        }
        Plateau p = new Plateau(joueurBlanc, joueurNoir);
        jouer(joueurBlanc, joueurNoir, p);

    }
}