package piece;

import échiquier.Coord;
import échiquier.IFabriquePiece;
import échiquier.IPiece;

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
        } else {
            piecesDepart.add(new Roi (couleur,0, 4));
            piecesDepart.add(new Tour(couleur,0,0));
        }

        return piecesDepart;
    }

}
