package piece;


import echiquier.Coord;
import echiquier.Plateau;

public class Dame extends Piece{
    public Dame(CouleurPiece coul, int colonne, int ligne) {
        super('D', coul, colonne, ligne);
    }

    @Override
    public boolean peutJouer(Coord c, Plateau p) {
        boolean joueCommeTour = false;
        boolean joueCommeFou = false;

        if (this.getCoord().getLigne() == c.getLigne()
                && this.getCoord().getColonne() !=c.getColonne()
                ||this.getCoord().getColonne() == c.getColonne()
                && this.getCoord().getLigne()!= c.getLigne())
            joueCommeTour = true;

        if (Math.abs(this.getCoord().getColonne()- c.getColonne()) == Math.abs(this.getCoord().getLigne() - c.getLigne()))
            joueCommeFou = true;

        if (joueCommeTour){
            if (this.getCoord().getLigne() != c.getLigne() && getCoord().getColonne() == c.getColonne()){
                if(this.getCoord().getLigne() < c.getLigne()) {
                    for (int cmp = c.getLigne() - 1; cmp > this.getCoord().getLigne(); cmp--) {
                        if (p.laPiece(new Coord(cmp,this.getCoord().getColonne()))!=null) {
                            /*System.out.println("piece sur le passage de haut en bas");*/
                            return false;
                        }
                    }
                }

                else if(getCoord().getLigne() > c.getLigne()) {
                    for (int cmp = c.getLigne() + 1; cmp < getCoord().getLigne(); cmp++) {
                        if (p.laPiece(new Coord(cmp,this.getCoord().getColonne()))!=null) {
                            /*System.out.println("piece sur le passage de bas en haut");*/
                            return false;
                        }
                    }
                }
            }

            if (getCoord().getLigne() == c.getLigne() && getCoord().getColonne() != c.getColonne()){
                if(getCoord().getColonne() < c.getColonne()) {
                    for (int cmp = c.getColonne() - 1; cmp > getCoord().getColonne(); cmp--) {
                        if (p.laPiece(new Coord(this.getCoord().getLigne(),cmp))!=null) {
                            /*System.out.println("piece sur le passage de gauche vers droite");*/
                            return false;
                        }
                    }
                }

                else if(getCoord().getColonne() > c.getColonne()) {
                    for (int cmp = c.getColonne() + 1; cmp < getCoord().getColonne(); cmp++) {
                        if (p.laPiece(new Coord(this.getCoord().getLigne(),cmp))!=null) {
                            /*System.out.println("piece sur le passage de droite vers gauche");*/
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        if (joueCommeFou){
            int dx = this.getCoord().getColonne() - c.getColonne() > 0 ? -1 : 1;
            int dy = this.getCoord().getLigne() - c.getLigne() > 0 ?  -1 : 1;
            for(int i = 1 ; i< Math.abs(this.getCoord().getColonne())-c.getColonne(); i++)
                if (p.laPiece(new Coord(this.getCoord().getLigne() + i * dy,this.getCoord().getColonne() + i * dx))!=null)
                    return false;
            return true;
        }


        return false;

    }
}