package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Tour extends Piece {

    public Tour(CouleurPiece couleur, int x, int y) {
        super('t', couleur, x, y);
    }

    @Override
    public boolean peutJouer(Coord c, Plateau p) {

        int lignePiece = getCoord().getLigne();
        int colonnePiece = getCoord().getColonne();

        if (!(lignePiece == c.getLigne() && colonnePiece != c.getColonne() || colonnePiece == c.getColonne() && lignePiece != c.getLigne()))
            return false;

        if (lignePiece != c.getLigne() && colonnePiece == c.getColonne()) {
            if (lignePiece < c.getLigne()) {
                for (int cmp = c.getLigne() - 1; cmp > lignePiece; cmp--) {
                    if(p.laPiece(new Coord(cmp,colonnePiece)) != null){
                        return false;
                    }
                }
            } else if (lignePiece > c.getLigne()) {
                for (int cmp = c.getLigne() + 1; cmp < lignePiece; cmp++) {
                    if (p.laPiece(new Coord(cmp,colonnePiece)) != null) {
                        return false;
                    }
                }
            }
        }

        if (lignePiece == c.getLigne() && colonnePiece != c.getColonne()) {
            if (colonnePiece < c.getColonne()) {
                for (int cmp = c.getColonne() - 1; cmp > colonnePiece; cmp--) {
                    if (p.laPiece(new Coord(lignePiece,cmp)) != null) {
                        return false;
                    }
                }
            }

            if (colonnePiece > c.getColonne()) {
                for (int cmp = c.getColonne() + 1; cmp < colonnePiece; cmp++) {
                    if (p.laPiece(new Coord(lignePiece,cmp)) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
