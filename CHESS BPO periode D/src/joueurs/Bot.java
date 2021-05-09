package joueurs;

import echiquier.*;

import java.util.ArrayList;
import java.util.Collections;

public class Bot extends Joueur {

    public Bot(String nom, boolean blanc, IFabriquePiece fab) {
        super(nom, blanc, fab);
    }


    @Override
    public void joue(IJoueur autreJoueur, Plateau p) {

        ArrayList<String> tabCoups = new ArrayList<>();

        for (IPiece piece : p.getListePieces()) {
            if (piece.getCouleur().equals(leRoi().getCouleur())) {
                for (int cmp1 = 0; cmp1 < 8; cmp1++) {
                    for (int cmp2 = 0; cmp2 < 8; cmp2++) {
                        if (p.estJouable(piece.getCoord(), new Coord(cmp1, cmp2), this)) {
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
        Collections.shuffle(tabCoups);
        System.out.println("le coup du bot est " + tabCoups.get(0));

        déplacer(tabCoups.get(0), autreJoueur, p);


    }

    @Override
    public boolean estHumain() {
        return false;
    }
}


