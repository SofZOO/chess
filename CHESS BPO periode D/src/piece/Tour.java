package piece;

import echiquier.Case;
import echiquier.Coord;

public class Tour extends Piece {

    public Tour(CouleurPiece couleur, int x, int y) {
        super('t', couleur, x, y);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (!(getLigne() == c.getLigne() && getColonne() != c.getColonne() || getColonne() == c.getColonne() && getLigne() != c.getLigne()))
            return false;

        if (getLigne() != c.getLigne() && getColonne() == c.getColonne()) {
            if (getLigne() < c.getLigne()) {
                for (int cmp = c.getLigne() - 1; cmp > getLigne(); cmp--) {
                    if (echiquier[cmp][getColonne()].isEstOccupé()) {
                        return false;
                    }
                }
            } else if (getLigne() > c.getLigne()) {
                for (int cmp = c.getLigne() + 1; cmp < getLigne(); cmp++) {
                    if (echiquier[cmp][getColonne()].isEstOccupé()) {
                        return false;
                    }
                }
            }
        }

        if (getLigne() == c.getLigne() && getColonne() != c.getColonne()) {
            if (getColonne() < c.getColonne()) {
                for (int cmp = c.getColonne() - 1; cmp > getColonne(); cmp--) {
                    if (echiquier[getLigne()][cmp].isEstOccupé()) {
                        return false;
                    }
                }
            }

            if (getColonne() > c.getColonne()) {
                for (int cmp = c.getColonne() + 1; cmp < getColonne(); cmp++) {
                    if (echiquier[getLigne()][cmp].isEstOccupé()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
