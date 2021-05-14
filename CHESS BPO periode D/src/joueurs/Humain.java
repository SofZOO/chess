package joueurs;

import echiquier.IFabriquePiece;
import echiquier.IJoueur;
import echiquier.Plateau;

import java.util.Locale;
import java.util.Scanner;

public class Humain extends Joueur {
    private boolean abandonne;

    public Humain(boolean blanc, IFabriquePiece fab) {
        super(blanc, fab);
        this.abandonne = false;
    }

    /**
     *
     * @param autreJoueur le joueur adverse
     * @param p le plateau de jeu
     */
    @Override
    public void joueUnTour(IJoueur autreJoueur, Plateau p) {


        Scanner sc = new Scanner(System.in);
        String coup;


        while (true) {
            System.out.println("Tour du joueur " + this.getNom());

            // Si le joueur adverse a proposé la nulle lors du coup précédent
            if (p.getPropositionNulle()) {
                System.out.println("le joueur " + autreJoueur.getNom() + " vous propose la nulle, voulez-vous accepter ? Veuillez répondre par Oui ou par Non.");
                coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                while (true) {

                    // Si l'humain accepte
                    if (coup.equals("oui")) {
                        System.out.println("Le joueur " + this.getNom() + "  a accepte la proposition de la nulle, la partie se termine par un match nul.");
                        p.setMAtchNul(true);
                        return;
                    }

                    // Si l'humain refuse
                    else if (coup.equals("non")) {
                        System.out.println("Le joueur " + this.getNom() + " a refusé la nulle.");
                        p.setPropositionNulle(false);
                        return;
                    }

                    // S'il a fait une erreur de frappe
                    else {
                        System.out.println("Veuillez répondre par oui ou par non");
                        coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                    }
                }
            }

            // Le joueur écrit son action
            coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);

            // Si le joueur abandonne
            if (coup.equals("abandon")) {
                this.abandonne = true;
                return;
            }

            // Si le joueur propose la nulle
            if (coup.equals("nulle")) {

                // Si le joueur propose la nulle a un bot
                if (!autreJoueur.estHumain()) {
                    System.out.println("Vous ne pouvez pas proposer la nulle a un bot, bien tenté. Veuillez réécrire votre coup.");
                    coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
                } else {
                    System.out.println("tentative de nulle pour le joueur " + getNom());
                    p.setPropositionNulle(true);
                    return;
                }
            }

            // Si le coup n'est pas valable pour la méthode "doitRejouer" du plateau p
            if (p.doitRejouer(coup, this)) {
                System.out.println("Mauvais coup pour le joueur " + this.getNom());
            } else {
                déplacer(coup, autreJoueur, p);
                break;
            }
        }
    }

    /**
     * permet de savoir que l'humain est un humain
     *
     * @return true car le bot est humain
     */
    @Override
    public boolean estHumain() {
        return true;
    }

    /**
     * Permet de savoir si le joueur abandonne ou non
     *
     * @return "abandonne"
     */
    @Override
    public boolean abandonne() {
        return this.abandonne;
    }

}
