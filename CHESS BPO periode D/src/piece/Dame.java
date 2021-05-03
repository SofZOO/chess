package piece;

import echiquier.Case;
import echiquier.Coord;

public class Dame extends Piece{
    public Dame(CouleurPiece coul, int colonne, int ligne) {
        super('D', coul, colonne, ligne);
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier) {
        boolean joueCommeTour = false;
        boolean joueCommeFou = false;

        if (getLigne() == c.getLigne()
                && getColonne() !=c.getColonne()
                || getColonne() == c.getColonne()
                && getLigne() != c.getLigne())
            joueCommeTour = true;

        if (Math.abs(this.getColonne() - c.getColonne()) == Math.abs(this.getLigne() - c.getLigne()))
            joueCommeFou = true;

        if (joueCommeTour){
            if (getLigne() != c.getLigne() && getColonne() == c.getColonne()){
                if(getLigne() < c.getLigne()) {
                    for (int cmp = c.getLigne() - 1; cmp > getLigne(); cmp--) {
                        if (echiquier[cmp][getColonne()].isEstOccupé()) {
                            System.out.println("piece sur le passage de haut en bas");
                            return false;
                        }
                    }
                }

                else if(getLigne() > c.getLigne()) {
                    for (int cmp = c.getLigne() + 1; cmp < getLigne(); cmp++) {
                        if (echiquier[cmp][getColonne()].isEstOccupé()) {
                            System.out.println("piece sur le passage de bas en haut");
                            return false;
                        }
                    }
                }
            }

            if (getLigne() == c.getLigne() && getColonne() != c.getColonne()){
                if(getColonne() < c.getColonne()) {
                    for (int cmp = c.getColonne() - 1; cmp > getColonne(); cmp--) {
                        if (echiquier[getLigne()][cmp].isEstOccupé()) {
                            System.out.println("piece sur le passage de gauche vers droite");
                            return false;
                        }
                    }
                }

                else if(getColonne() > c.getColonne()) {
                    for (int cmp = c.getColonne() + 1; cmp < getColonne(); cmp++) {
                        if (echiquier[getLigne()][cmp].isEstOccupé()) {
                            System.out.println("piece sur le passage de droite vers gauche");
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        if (joueCommeFou){
            int dx = this.getColonne() - c.getColonne() > 0 ? -1 : 1;
            int dy = this.getLigne() - c.getLigne() > 0 ?  -1 : 1;
            for(int i = 1 ; i< Math.abs(this.getColonne()-c.getColonne()); i++)
                if (echiquier[this.getLigne() + i * dy][this.getColonne() + i * dx].isEstOccupé())
                    return false;

            return true;
        }


        return false;
    }
}
