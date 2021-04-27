package piece;

import échiquier.Case;
import échiquier.Coord;

public class Roi extends Piece {

    public Roi(CouleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (Math.abs(this.getColonne()-c.getColonne())>1 || Math.abs(this.getLigne()-c.getLigne())>1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean craintEchec() {
        return true;
    }
}
