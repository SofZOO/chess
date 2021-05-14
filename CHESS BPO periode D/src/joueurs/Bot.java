package joueurs;

import echiquier.*;

import java.util.ArrayList;
import java.util.Collections;

public class Bot extends Joueur {

    public Bot(boolean blanc, IFabriquePiece fab) {
        super(blanc, fab);
    }

    /**
     * Permet de créer une liste de coups faits au hasard et permet ainsi au bot de sélectionner un coup et le jouer
     *
     * @param autreJoueur le joueur adverse
     * @param p           le plateau de jeu
     */
    @Override
    public void joueUnTour(IJoueur autreJoueur, Plateau p) {
        ArrayList<String> tabCoups = new ArrayList<>();
        // on prend toutes les pièces dans la liste de pièces
        for (IPiece piece : p.getListePieces()) {
            // on compare si les pièces sont de la même couleur que celui qui joue
            if (piece.getCouleur().equals(leRoi().getCouleur())) {
                for (int cmp1 = 0; cmp1 < 8; cmp1++) {
                    for (int cmp2 = 0; cmp2 < 8; cmp2++) {
                        // on prend tous les coups possibles sur le plateau pour les pièces du Bot qui joue
                        if (p.estJouable(piece.getCoord(), new Coord(cmp1, cmp2), this)) {
                            // on vérifie si le coup qu'il veut faire est jouable et on l'ajoute dans la liste de coups
                            tabCoups.add(piece.getCoord() + (new Coord(cmp1, cmp2).toString()));
                        }
                    }
                }
            }
        }
        System.out.println("--------------------------------------");
        for (String s : tabCoups)
            System.out.println(s);
        System.out.println("--------------------------------------");
        // on mélange la liste de coups pour avoir un coup au hasard
        Collections.shuffle(tabCoups);
        System.out.println("le coup du bot est " + tabCoups.get(0));
        // on effectue le coup sélectionné à l'indice 0 car tout est mélangé
        déplacer(tabCoups.get(0), autreJoueur, p);
    }

    /**
     * Permet de savoir que le bot n'est pas un humain
     *
     * @return false car le bot n'est pas humain
     */
    @Override
    public boolean estHumain() {
        return false;
    }

    /**
     * Permet de savoir si le joueur abandonne
     *
     * @return false car le bot n'abandonne jamais
     */
    @Override
    public boolean abandonne() {
        return false;
    }
}


