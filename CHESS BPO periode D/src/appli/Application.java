package appli;

import echiquier.IJoueur;
import echiquier.Plateau;
import joueurs.Bot;
import joueurs.Humain;
import piece.FabriquePiece;

import java.util.Scanner;

public class Application {
    private static boolean choixPartie = false; // permet de définir le type de partie jouée : une finale ou alors une partie avec nos autres pièces


    public static boolean getChoixPartie() {
        return choixPartie;
    }

    public static void setChoixPartieFinale(boolean choix) {
        choixPartie = choix;
    }

    /**
     * Permet d'éviter les erreur du choix de la partie pour l'utilisateur
     *
     * @param choix  le choix de l'utilisateur
     * @param option le nombre de choix possibles pour l'utilisateur
     * @return
     */
    private static boolean erreurChoix(String choix, int option) {
        if (choix.length() != 1) {
            return true;
        }
        if (!Character.isDigit(choix.charAt(0))) {
            return true;
        }
        return Integer.parseInt(choix) > option || Integer.parseInt(choix) < 1;
    }

    /**
     * Permet d'illustrer tous les cas qui provoquent une fin de partie
     *
     * @param courant    le IJoueur qui joue
     * @param pasCourant l'autre IJoueur
     * @param p          le plateau
     * @return true si c'est une fin de partie
     */
    private static boolean finPartie(IJoueur courant, IJoueur pasCourant, Plateau p) {

        if (pasCourant.isChessMat()) {
            System.out.println(p.partieFinieMat(pasCourant.getNom(), courant.getNom()));
            return true;
        }
        if (p.partieNulle(pasCourant)) {
            return true;
        }
        if (courant.abandonne()) {
            System.out.println("Le joueur " + courant.getNom() + " a décidé d'abandonner la partie.  Le joueur " + pasCourant.getNom() + " a gagné par \"forfait\"!");
            return true;
        }

        return false;
    }

    /**
     * Algo de jeu d'une partie pour un joueur
     *
     * @param jBlanc le joueur BLANC
     * @param jNoir  le joueur NOIR
     * @param p      le plateau
     */
    public static void jouer(IJoueur jBlanc, IJoueur jNoir, Plateau p) {

        System.out.println(p.affichePlateau(jBlanc, jNoir));

        while (!(jBlanc.isChessMat() || jNoir.isChessMat())) {
            jBlanc.joueUnTour(jNoir, p);
            System.out.println(p.affichePlateau(jBlanc, jNoir));
            if (finPartie(jBlanc, jNoir, p)) break;
            jNoir.joueUnTour(jBlanc, p);
            System.out.println(p.affichePlateau(jBlanc, jNoir));
            if (finPartie(jNoir, jBlanc, p)) break;
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