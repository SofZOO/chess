package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Roi extends Piece {

    /**
     * Le constructeur du roi qui extends Pièce
     *
     * @param coul la couleur de la pièce
     * @param x    la colonne ou le roi est placé
     * @param y    la ligne ou le roi est placé
     */
    public Roi(CouleurPiece coul, int x, int y) {
        super('r', coul, x, y);
    }

    @Override
    public boolean peutJouer(Coord c, Plateau plateau) {
        // on limite sa destination à une case autour de lui en diagnonale, verticale ou horizontale
        return Math.abs(getCoord().getColonne() - c.getColonne()) <= 1 && Math.abs(getCoord().getLigne() - c.getLigne()) <= 1;
    }

    @Override
    public boolean craintEchec() {
        // le roi est la seule pièce qui craint l'échec parmi toutes les pièces
        return true;
    }
}
