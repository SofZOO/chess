package piece;

import echiquier.Case;
import echiquier.Coord;

public class Dame extends Piece{
    public Dame(CouleurPiece coul, int colonne, int ligne) {
        super('D', coul, colonne, ligne);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        boolean validation = true;
        boolean validationbis = true;
        if (!(getLigne() == c.getLigne() && getColonne() !=c.getColonne() || getColonne() == c.getColonne() && getLigne() != c.getLigne()))
            validation = false;

        if (getLigne() != c.getLigne() && getColonne() == c.getColonne()){
            if(getLigne() < c.getLigne()) {
                for (int cmp = c.getLigne() - 1; cmp > getLigne(); cmp--) {
                    if (echiquier[cmp][getColonne()].isEstOccupé()) {
                        System.out.println("piece sur le passage de haut en bas");
                        validation = false;
                    }
                }
            }

            else if(getLigne() > c.getLigne()) {
                for (int cmp = c.getLigne() + 1; cmp < getLigne(); cmp++) {
                    if (echiquier[cmp][getColonne()].isEstOccupé()) {
                        System.out.println("piece sur le passage de bas en haut");
                        validation = false;
                    }
                }
            }
        }

        if (getLigne() == c.getLigne() && getColonne() != c.getColonne()){
            if(getColonne() < c.getColonne()) {
                for (int cmp = c.getColonne() - 1; cmp > getColonne(); cmp--) {
                    if (echiquier[getLigne()][cmp].isEstOccupé()) {
                        System.out.println("piece sur le passage de gauche vers droite");
                        validation = false;
                    }
                }
            }

            if(getColonne() > c.getColonne()) {
                for (int cmp = c.getColonne() + 1; cmp < getColonne(); cmp++) {
                    if (echiquier[getLigne()][cmp].isEstOccupé()) {
                        System.out.println("piece sur le passage de droite vers gauche");
                        validation = false;
                    }
                }
            }
        }
        if(validation) {
            return true;
        }
        int dx;
        int dy;
        if (Math.abs(this.getColonne() - c.getColonne()) != Math.abs(this.getLigne() - c.getLigne()))
            validationbis = false;
        dx = this.getColonne() - c.getColonne() > 0 ? -1 : 1;
        dy = this.getLigne() - c.getLigne() > 0 ?  -1 : 1;
        for(int i = 1 ; i< Math.abs(this.getColonne()-c.getColonne()); i++)
            if (echiquier[this.getLigne() + i * dy][this.getColonne() + i * dx].isEstOccupé())
                validationbis = false;

        return validationbis;
    }
}
