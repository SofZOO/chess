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
        p.déplacer("e1f1",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("f1g1",joueurBlanc,joueurNoir);
        System.out.println(p);

        p.déplacer("g1h1",joueurBlanc,joueurNoir);
        System.out.println(p);
        p.déplacer("e8e7",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("e7e6",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("e6e5",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("e5e4",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("e4e3",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("e3f3",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("f3f2",joueurNoir,joueurBlanc);
        System.out.println(p);

        p.déplacer("a8b8",joueurNoir,joueurBlanc);
        System.out.println(p);
        p.déplacer("b8h8",joueurNoir,joueurBlanc);
        System.out.println(p);

    }
}
