package piece;

import echiquier.Case;
import echiquier.Coord;

public class Cavalier extends Piece{

    public Cavalier( CouleurPiece coul, int colonne, int ligne) {
        super('C', coul, colonne, ligne);
    }
    
    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        int dx = Math.abs(this.getColonne() - c.getColonne());
        int dy = Math.abs(this.getLigne() - c.getLigne());
        if((dx== 2 && dy == 1 )|| (dx == 1 && dy == 2)){
            return true;
        }
        return false;
    }
}
