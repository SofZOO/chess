package piece;

import échiquier.Case;
import échiquier.Coord;

public class Roi extends Piece {

    public Roi(CouleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (c.getLigne() == getLigne() && c.getColonne() == getColonne() + 1)
            return true;
        if (c.getLigne() == getLigne()+1 && c.getColonne() == getColonne() + 1)
            return true;
        if (c.getLigne() == getLigne()+1 && c.getColonne() == getColonne())
            return true;
        if (c.getLigne() == getLigne() +1 && c.getColonne() == getColonne()-1)
            return true;
        if (c.getLigne() == getLigne() && c.getColonne() == getColonne() -1)
            return true;
        if (c.getLigne() == getLigne() - 1  && c.getColonne() == getColonne() - 1)
            return true;
        if (c.getLigne() == getLigne() -1 && c.getColonne() == getColonne())
            return true;
        if (c.getLigne() == getLigne() -1 && c.getColonne() == getColonne()+1)
            return true;
        return false;
    }

    @Override
    public boolean craintEchec() {
        return true;
    }
}
