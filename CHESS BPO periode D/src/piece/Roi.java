package piece;

import échiquier.Case;
import échiquier.Coord;

public class Roi extends Piece {

    public Roi(CouleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (c.getX() == getX() && c.getY() == getY() + 1)
            return true;
        if (c.getX() == getX()+1 && c.getY() == getY() + 1)
            return true;
        if (c.getX() == getX()+1 && c.getY() == getY())
            return true;
        if (c.getX() == getX() +1 && c.getY() == getY()-1)
            return true;
        if (c.getX() == getX() && c.getY() == getY()-1)
            return true;
        if (c.getX() == getX() - 1  && c.getY() == getY() - 1)
            return true;
        if (c.getX() == getX() -1 && c.getY() == getY())
            return true;
        if (c.getX() == getX() -1 && c.getY() == getY()+1)
            return true;
        return false;
    }

    @Override
    public boolean craintEchec() {
        return true;
    }
}
