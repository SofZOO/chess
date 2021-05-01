package appli;

import piece.FabriquePiece;
import java.util.Locale;
import echiquier.Plateau;
import java.util.Scanner;

public class Main {
    public static void jouer(Joueur jBlanc, Joueur jNoir, Plateau p){
        System.out.println(p.affichePlateau(jBlanc,jNoir));
        Scanner sc = new Scanner(System.in);
        String coup;
        while (!jBlanc.getEchecEtMat() || jNoir.getEchecEtMat()){

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
            if (finPartie(jBlanc, jNoir, p, jNoir.getEchecEtMat(), jNoir.getNom(), jBlanc.getNom())) break;

            while(true){// todo : boucle de jeu du joueur NOIR
                System.out.println("Tour des Noirs");
                coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                if(p.doitRejouer(coup,jNoir)){
                    System.out.println("coup de merde pour le joueur " + jNoir.getNom());
                    continue;
                }
                else{
                    p.déplacer(coup,jNoir,jBlanc);
                    break;
                }
            }
            if (finPartie(jBlanc, jNoir, p, jBlanc.getEchecEtMat(), jBlanc.getNom(), jNoir.getNom())) break;
        }
        System.out.println("fin du test");
    }

    private static boolean finPartie(Joueur jBlanc, Joueur jNoir, Plateau p, boolean echecEtMat, String nom, String autreNom) {
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

    public static void main(String[] args) {
        Joueur joueurBlanc = new Joueur("BLANC",true,new FabriquePiece());
        Joueur joueurNoir = new Joueur("NOIR",false,new FabriquePiece());
        Plateau p = new Plateau(joueurBlanc,joueurNoir);

        jouer(joueurBlanc,joueurNoir,p);



    }
}
