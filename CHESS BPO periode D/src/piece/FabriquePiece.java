package piece;

import echiquier.IFabriquePiece;
import echiquier.IPiece;

import java.util.ArrayList;

public class FabriquePiece implements IFabriquePiece {

    public ArrayList<IPiece> fabrique(boolean estBlanc){
        ArrayList<IPiece> piecesDepart = new ArrayList<>();

        CouleurPiece couleur;
        if (estBlanc)
            couleur = CouleurPiece.BLANC;
        else couleur = CouleurPiece.NOIR;

        if (estBlanc) {
            piecesDepart.add(new Roi (couleur,7, 4));
            piecesDepart.add(new Tour(couleur,7,0));
            piecesDepart.add(new Fou(couleur,7,2));
            piecesDepart.add(new Dame(couleur,7,3));

        } else {
            piecesDepart.add(new Roi (couleur,0, 4));
            piecesDepart.add(new Tour(couleur,0,0));
            piecesDepart.add(new Fou(couleur,0,2));
            piecesDepart.add(new Dame(couleur,0,3));
        }
        return piecesDepart;
    }

}