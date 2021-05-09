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
                System.out.println("le joueur "+autreJoueur.getNom()+" vous propose la nulle, voulez-vous accepter ? Veuillez répondre par Oui ou par Non.");
                coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                while (true){
                    if (coup.equals("oui")){
                        System.out.println("Le joueur "+ this.getNom() +"  a accepte la proposition de la nulle, la partie se termine par un match nul.");
                        p.setMAtchNul(true);
                        return;
                    }
                    else if (coup.equals("non")){
                        System.out.println("Le joueur " + this.getNom() +" a refusé la nulle.");
                        p.setPropositionNulle(false);
                        return;
                    }
                    else{
                        System.out.println("Veuillez répondre par oui ou par non");
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
                     System.out.println("Vous ne pouvez pas proposer la nulle a un bot, bien tenté. Veuillez réécrire votre coup.");
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
