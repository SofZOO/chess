package piece;

import appli.Coord;

public class Roi extends Piece {

    public Roi(couleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(Coord c) {
        if (c.getColonne() == getColonne() && c.getLigne() == getLigne() + 1)
            return true;
        if (c.getColonne() == getColonne()+1 && c.getLigne() == getLigne() + 1)
            return true;
        if (c.getColonne() == getColonne()+1 && c.getLigne() == getLigne())
            return true;
        if (c.getColonne() == getColonne() +1 && c.getLigne() == getLigne()-1)
            return true;
        if (c.getColonne() == getColonne() && c.getLigne() == getLigne() -1)
            return true;
        if (c.getColonne() == getColonne() - 1  && c.getLigne() == getLigne() - 1)
            return true;
        if (c.getColonne() == getColonne() -1 && c.getLigne() == getLigne())
            return true;
        if (c.getColonne() == getColonne() -1 && c.getLigne() == getLigne()+1)
            return true;
        return false;
    }
}
