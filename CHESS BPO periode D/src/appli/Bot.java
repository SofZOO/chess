package appli;

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
            if (piece.compareCouleur(leRoi())) {
                for (int cmp1 = 0; cmp1 < 8; cmp1++) {
                    for (int cmp2 = 0; cmp2 < 8; cmp2++) {
                        if (piece.getCoord().compare(new Coord(cmp1, cmp2))) {
                            continue;
                        } else if (p.estJouable(piece.getCoord(), new Coord(cmp1, cmp2), this)) {
                            tabCoups.add(p.getCoord(piece.getCoord()) + p.getCoord(new Coord(cmp1, cmp2)));
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
}


