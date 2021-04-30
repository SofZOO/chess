package piece;

import échiquier.Case;
import échiquier.Coord;

public class Fou extends Piece{

    public Fou(CouleurPiece coul, int colonne, int ligne) {
        super('f', coul, colonne, ligne);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        if (Math.abs(this.getColonne() - c.getColonne()) != Math.abs(this.getLigne() - c.getLigne()))
            return false;
        int dx = this.getColonne() - c.getColonne() > 0 ? -1 : 1;
        int dy = this.getLigne() - c.getLigne() > 0 ?  -1 : 1;
        for(int i = 1 ; i< Math.abs(this.getColonne()-c.getColonne()); i++)
            if (!echiquier[this.getLigne() + i * dy][this.getColonne() + i * dx].isEstOccupé())
                return false;
        return true;
    }
}
