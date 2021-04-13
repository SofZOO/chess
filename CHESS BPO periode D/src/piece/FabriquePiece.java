package piece;

import échiquier.Coord;
import échiquier.IFabriquePiece;
import échiquier.IPiece;

public class FabriquePiece implements IFabriquePiece {

    public IPiece fabrique(int numero, Coord coord, boolean estBlanc){
        CouleurPiece couleur;
        if (estBlanc)
            couleur = CouleurPiece.BLANC;
        else couleur = CouleurPiece.NOIR;

        if (numero == 1)
            return new Roi(couleur,coord.getColonne(),coord.getLigne());

        else return new Tour(couleur,coord.getColonne(),coord.getLigne());
    }

}
