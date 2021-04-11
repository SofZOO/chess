package piece;

import appli.Coord;

public class Tour extends Piece{

    public Tour(couleurPiece couleur, int x, int y){
        super('t',couleur,x,y);
    }

    @Override
    public boolean peutJouer(Coord c) {
        return getPosX() == c.getX() && getPosY() !=c.getY() || getPosY() == c.getY() && getPosX() != c.getX();
    }

}
