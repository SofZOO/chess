package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Fou extends Piece{

    public Fou(CouleurPiece coul, int colonne, int ligne) {
        super('f', coul, colonne, ligne);
    }

    @Override
    public boolean peutJouer(Coord c, Plateau p) {
        int dx;
        int dy;
        if (Math.abs(this.getCoord().getColonne() - c.getColonne()) != Math.abs(this.getCoord().getLigne() - c.getLigne()))
            return false;
        dx = this.getCoord().getColonne() - c.getColonne() > 0 ? -1 : 1;
        dy = this.getCoord().getLigne() - c.getLigne() > 0 ?  -1 : 1;
        for(int i = 1 ; i< Math.abs(this.getCoord().getColonne()-c.getColonne()); i++)
            if (p.laPiece(new Coord(this.getCoord().getLigne() + i * dy,this.getCoord().getColonne() + i * dx)) != null)
                return false;
        return true;
    }
}