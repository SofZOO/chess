package piece;

import appli.Coord;

public class Roi extends Piece {

    public Roi(couleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(Coord c) {
        if (c.getX() == getPosX() && c.getY() == getPosY() + 1)
            return true;
        if (c.getX() == getPosX()+1 && c.getY() == getPosY() + 1)
            return true;
        if (c.getX() == getPosX()+1 && c.getY() == getPosY())
            return true;
        if (c.getX() == getPosX() +1 && c.getY() == getPosY()-1)
            return true;
        if (c.getX() == getPosX() && c.getY() == getPosY() -1)
            return true;
        if (c.getX() == getPosX() - 1  && c.getY() == getPosY() - 1)
            return true;
        if (c.getX() == getPosX() -1 && c.getY() == getPosY())
            return true;
        if (c.getX() == getPosX() -1 && c.getY() == getPosY()+1)
            return true;
        return false;
    }
}
