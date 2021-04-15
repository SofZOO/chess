package piece;

import échiquier.Case;
import échiquier.Coord;

public class Tour extends Piece{

    public Tour(CouleurPiece couleur, int x, int y){
        super('t',couleur,x,y);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (!(getX() == c.getX() && getY() !=c.getY() || getY() == c.getY() && getX() != c.getX()))
            return false;
        if (getX() == c.getX() && getY() !=c.getY()){
            if(getY() < c.getY())
                for (int cmp = c.getY()-1; cmp > getY(); cmp--){
                    if (echiquier[getX()][cmp].isEstOccupé())
                        return false;
                }
        }

        return true;
    }
}
