package appli;

import echiquier.IJoueur;
import echiquier.Plateau;
import joueurs.Bot;
import joueurs.Humain;
import piece.FabriquePiece;

import java.util.Scanner;

public class Application {
    private static boolean choixPartie = false;

    public static boolean getChoixPartie() {
        return choixPartie;
    }

    public static void setChoixPartieFinale(boolean choix) {
        choixPartie = choix;
    }

    private static boolean erreurChoix(String choix, int option) {
        if (choix.length() != 1) {
            return true;
        }
        if (!Character.isDigit(choix.charAt(0))) {
            return true;
        }
        return Integer.parseInt(choix) > option || Integer.parseInt(choix) < 1;
    }


    private static boolean finPartie(IJoueur jBlanc, IJoueur jNoir, Plateau p, boolean echecEtMat, String nom, String autreNom) {

        System.out.println(p.affichePlateau(jBlanc, jNoir));
        if (echecEtMat) {
            System.out.println(p.partieFinieMat(nom, autreNom));
            return true;
        }
        if (p.partieNulle(jBlanc, jNoir)) {
            System.out.println("La partie est nulle");
            return true;
        }
        if (jBlanc.abandonne()) {
            System.out.println("Le joueur " + jBlanc.getNom() + " a décidé d'abandonner la partie.  Le joueur " + jNoir.getNom() + " a gagné par \"forfait\"!");
            return true;
        }
        if (jNoir.abandonne()) {
            System.out.println("Le joueur " + jNoir.getNom() + " a décidé d'abandonner la partie. Le joueur " + jBlanc.getNom() + " a gagné par \"forfait\"!");
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
        System.out.println("Fin de la partie");
    }

    public static void main(String[] args) {
        IJoueur joueurBlanc;
        IJoueur joueurNoir;
        Scanner sc = new Scanner(System.in);
        String choix;
        System.out.println("Quelle disposition souhaitez avoir pour cette partie ? Taper 1 pour une finale avec seulement Roi et Tour"
                + System.lineSeparator() + "Taper 2 pour une partie avec Cavalier, Fou, Tour, Roi");
        choix = sc.nextLine().trim();
        while (erreurChoix(choix, 2)) {
            System.out.println("Vous venez de faire une erreur sur la saisie de votre choix, il faut entrer une des 2 options et vous avez marqué : " + choix);
            choix = sc.nextLine().trim();
        }
        switch (choix) {
            case ("1"):
                choixPartie = true;
                break;
            case ("2"):
                break;
        }

        System.out.println("" +
                "********************************************************************************************************\n" +
                "Bienvenue sur le menu de sélection d'adversaire, il suffit de taper : 1 pour le mode joueur VS joueur.  *\n" +
                "                                                                      2 pour le mode joueur VS bot.     *\n" +
                "                                                                      3 pour le mode bot VS bot.        *\n" +
                "********************************************************************************************************");

        choix = sc.nextLine().trim();
        while (erreurChoix(choix, 3)) {
            System.out.println("Vous venez de faire une erreur sur la saisie de votre choix, il faut entrer une des 3 options et vous avez marqué : " + choix);
            choix = sc.nextLine().trim();
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