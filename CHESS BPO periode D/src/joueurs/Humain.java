package joueurs;

import echiquier.IFabriquePiece;
import echiquier.IJoueur;
import echiquier.Plateau;

import java.util.Locale;
import java.util.Scanner;

public class Humain extends Joueur {
    private boolean abandonne;

    public Humain(String nom, boolean blanc, IFabriquePiece fab) {
        super(nom, blanc, fab);
        this.abandonne = false;
    }


    @Override
    public void joue(IJoueur autreJoueur, Plateau p) {
        Scanner sc = new Scanner(System.in);
        String coup;
        while (true) {
            System.out.println("Tour du joueur " + this.getNom());




            if (p.getPropositionNulle()){
                System.out.println("le joueur adverse vous propose la nulle, oui ou non?");
                coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                while (true){
                    if (coup.equals("oui")){
                        System.out.println("vous avez accepte la proposition de la nulle, la partie se termine par un match nul");
                        p.setMAtchNul(true);
                        return;
                    }
                    else if (coup.equals("non")){
                        System.out.println("vous avez refuse la proposition de la nulle");
                        p.setPropositionNulle(false);
                        return;
                    }
                    else{
                        System.out.println("veuillez répondre par oui ou par non");
                        coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                    }
                }
            }
             coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
            if(coup.equals("abandon")){
                this.abandonne = true;
                return;
            }
             if (coup.equals("nulle")) {
                 if (!autreJoueur.estHumain()){
                     System.out.println("vous ne pouvez pas proposer la nulle a un bot, veuillez réécrire votre coup");
                     coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                 }
                 else {
                     System.out.println("tentative de nulle pour le joueur " + getNom());
                     p.setPropositionNulle(true);
                     return;
                 }
             }

            if (p.doitRejouer(coup, this)) {
                System.out.println("Mauvais coup pour le joueur " + this.getNom());
            } else {
                déplacer(coup, autreJoueur, p);
                break;
            }
        }
    }

    @Override
    public boolean estHumain() {
        return true;
    }

    @Override
    public boolean abandonne() {
        return this.abandonne;
    }

}
