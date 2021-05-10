package piece;

import echiquier.IFabriquePiece;
import echiquier.IPiece;

import java.util.ArrayList;

public class FabriquePiece implements IFabriquePiece {

    public ArrayList<IPiece> fabrique(boolean estBlanc) {
        ArrayList<IPiece> piecesDepart = new ArrayList<>();
        CouleurPiece couleur;
        couleur = estBlanc ? CouleurPiece.BLANC : CouleurPiece.NOIR;
        if (estBlanc) {
            piecesDepart.add(new Roi(couleur, 2, 4));
            piecesDepart.add(new Tour(couleur, 1, 1));

        } else {
            piecesDepart.add(new Roi(couleur, 0, 4));

        }

        return piecesDepart;
    }

}