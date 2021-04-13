package piece;

import échiquier.Coord;

public class Tour extends Piece{

    public Tour(CouleurPiece couleur, int x, int y){
        super('t',couleur,x,y);
    }

    @Override
    public boolean peutJouer(Coord c) {
        return getColonne() == c.getColonne() && getLigne() !=c.getLigne() || getLigne() == c.getLigne() && getColonne() != c.getColonne();
    }

}
