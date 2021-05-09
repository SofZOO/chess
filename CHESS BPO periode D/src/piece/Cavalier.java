package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Cavalier extends Piece{

    public Cavalier( CouleurPiece coul, int colonne, int ligne) {
        super('C', coul, colonne, ligne);
    }

    @Override
    public boolean peutJouer(Coord c, Plateau p) {
        int dx = Math.abs(this.getCoord().getColonne()) - c.getColonne();
        int dy = Math.abs(this.getCoord().getLigne()) - c.getLigne();
        if((dx== 2 && dy == 1 )|| (dx == 1 && dy == 2)){
            return true;
        }
        return false;
    }
}