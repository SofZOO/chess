package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Roi extends Piece {

    public Roi(CouleurPiece coul, int x, int y) {
        super('r', coul, x, y);
    }

    @Override
    public boolean peutJouer(Coord c, Plateau plateau) {
        return Math.abs(getCoord().getColonne() - c.getColonne()) <= 1 && Math.abs(getCoord().getLigne() - c.getLigne()) <= 1;
    }

    @Override
    public boolean craintEchec() {
        return true;
    }
}
