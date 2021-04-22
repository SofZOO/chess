package piece;

import échiquier.Case;
import échiquier.Coord;

public class Roi extends Piece {

    public Roi(CouleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (c.getLigne() == getColonne() && c.getColonne() == getLigne() + 1)
            return true;
        if (c.getLigne() == getColonne()+1 && c.getColonne() == getLigne() + 1)
            return true;
        if (c.getLigne() == getColonne()+1 && c.getColonne() == getLigne())
            return true;
        if (c.getLigne() == getColonne() +1 && c.getColonne() == getLigne()-1)
            return true;
        if (c.getLigne() == getColonne() && c.getColonne() == getLigne() -1)
            return true;
        if (c.getLigne() == getColonne() - 1  && c.getColonne() == getLigne() - 1)
            return true;
        if (c.getLigne() == getColonne() -1 && c.getColonne() == getLigne())
            return true;
        if (c.getLigne() == getColonne() -1 && c.getColonne() == getLigne()+1)
            return true;
        return false;
    }
}
