package appli;

import echiquier.Coord;
import echiquier.IJoueur;
import piece.FabriquePiece;
import java.util.Locale;
import echiquier.Plateau;
import java.util.Scanner;

public class Main {

    private static boolean finPartie(IJoueur jBlanc, IJoueur jNoir, Plateau p, boolean echecEtMat, String nom, String autreNom) {
      System.out.println(p.affichePlateau(jBlanc,jNoir));

      if (echecEtMat) {
          System.out.println("Le joueur " + nom + " est vaincu par échecs et mat. Le joueur " + autreNom + " a gagné" );
          return true;
      }
      if(p.getEchecEtPat()){
          System.out.println("Situation d'échecs et pat : la partie est nulle");
          return true;
      }
      return false;
    }

    public static void jouer(IJoueur jBlanc, IJoueur jNoir, Plateau p){
        System.out.println(p.affichePlateau(jBlanc,jNoir));

        while (!jBlanc.getEchecEtMat() || jNoir.getEchecEtMat()){
            jBlanc.joue(jNoir,p);
            if (finPartie(jBlanc, jNoir, p, jNoir.getEchecEtMat(), jNoir.getNom(), jBlanc.getNom())) break;

            jNoir.joue(jBlanc,p);
            if (finPartie(jBlanc, jNoir, p, jBlanc.getEchecEtMat(), jBlanc.getNom(), jNoir.getNom())) break;
        }
        System.out.println("fin du test");
    }



    public static void main(String[] args) {
        IJoueur joueurBlanc;
        IJoueur joueurNoir;

        System.out.println("taper : 1 pour joueur VS joueur\n        2 pour joueur VS bot\n        3 pour bot VS bot ");
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        choix = sc.nextInt();

        switch (choix){
            case(1):{
                joueurBlanc = new Joueur("BLANC",true,new FabriquePiece());
                joueurNoir = new Joueur("NOIR",false,new FabriquePiece());
                break;

            }
            case(2):{
                joueurBlanc = new Joueur("BLANC",true,new FabriquePiece());
                joueurNoir = new Bot("NOIR",false,new FabriquePiece());
                break;

            }
            case(3):{
                joueurBlanc = new Bot("BLANC",true,new FabriquePiece());
                joueurNoir = new Bot("NOIR",false,new FabriquePiece());
                break;

            }
            default:{
                joueurBlanc = new Joueur("BLANC",true,new FabriquePiece());
                joueurNoir = new Joueur("NOIR",false,new FabriquePiece());
            }
        }

        Plateau p = new Plateau(joueurBlanc,joueurNoir);

        jouer(joueurBlanc,joueurNoir,p);





    }
}