package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Cavalier extends Piece{

    /**
     * Constructeur d'un cavalier
     * @param coul la couleur de la pièce
     * @param colonne la colonne de départ de la pièce
     * @param ligne la ligne de départ de la pièce
     */
    public Cavalier( CouleurPiece coul, int colonne, int ligne) {
        super('C', coul, colonne, ligne);
    }

    /**
     * Permet d'imaginer les déplacements du cavalier
     * @param c les coordonnées de la pièce
     * @param p le plateau
     * @return false si elle ne peut pas jouer
     */
    @Override
    public boolean peutJouer(Coord c, Plateau p) {
        int dx = Math.abs(this.getCoord().getColonne() - c.getColonne());
        int dy = Math.abs(this.getCoord().getLigne() - c.getLigne());

        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}