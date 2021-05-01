package appli;

import piece.FabriquePiece;


import java.util.Locale;
import echiquier.Plateau;

import java.util.Scanner;


public class Main {
    public static void jouer(Joueur jBlanc, Joueur jNoir, Plateau p){
        System.out.println(p);
        Scanner sc = new Scanner(System.in);
        String coup;
        while (!jBlanc.getEchecEtMat() || jNoir.getEchecEtMat()){
            //TODO : l'autre joueur peut jouer les pieces de son adversaire, pas bon !!
            //TODO : si le joeuur se trompe, on passe pas à l'autre joueur

            while (true) { // todo : boucle de jeu du joueur BLANC
                System.out.println("Tour des Blanc");
                coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                if (p.doitRejouer(coup,jBlanc)) {
                    System.out.println("coup de merde pour le joueur " + jBlanc.getNom());
                    continue;
                }
                else {
                    p.déplacer(coup, jBlanc, jNoir);
                    break;
                }
            }
            System.out.println(p);
            if (jNoir.getEchecEtMat()) {
                System.out.println("Le joueur " + jBlanc.getNom() + " a gagné" );
                break;
            }

            while(true){// todo : boucle de jeu du joueur NOIR
                System.out.println("Tour des Noirs");
                coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                if(p.doitRejouer(coup,jNoir)){
                    System.out.println("coup de merde pour le joueur " + jNoir.getNom());
                    continue;
                }
                else{
                    p.déplacer(coup,jNoir,jBlanc);
                    break;// tu peux commit stp jpeux p att je test
                }
            }
            System.out.println(p);
            if (jBlanc.getEchecEtMat()) {
                System.out.println("Le joueur " + jNoir.getNom() + " a gagné" );
                break;
            }

        }
        System.out.println("fin du test");
    }
//TODO : penser a faire l'affichage des pièces mangées par les joueurs
    public static void main(String[] args) {
        Joueur joueurBlanc = new Joueur("BLANC",true,new FabriquePiece());
        Joueur joueurNoir = new Joueur("NOIR",false,new FabriquePiece());
        Plateau p = new Plateau(joueurBlanc,joueurNoir);

        jouer(joueurBlanc,joueurNoir,p);



    }
}
