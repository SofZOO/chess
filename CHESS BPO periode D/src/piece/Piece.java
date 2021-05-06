package piece;

import echiquier.Coord;
import echiquier.IPiece;
import echiquier.Plateau;


public abstract class Piece implements IPiece {
    private final char signe;
    private final CouleurPiece couleur;
    private final Coord coord;

    public Piece(char sig, CouleurPiece coul, int colonne, int ligne) {
        this.couleur = coul;
        coord = new Coord(colonne, ligne);
        if (coul.equals(CouleurPiece.BLANC))
            this.signe = Character.toUpperCase(sig);
        else this.signe = Character.toLowerCase(sig);
    }

    public abstract boolean peutJouer(Coord c, Plateau p);

    @Override
    public void changeCoord(Coord c) {
        this.coord.setLigne(c.getLigne());
        this.coord.setColonne(c.getColonne());
    }

    @Override
    public char toChar() {
        return this.signe;
    }

    @Override
    public Coord getCoord() {
        return this.coord;
    }
    @Override
    public CouleurPiece getCouleur() {
        return couleur;
    }

    @Override
    public boolean craintEchec() {
        return false;
    }

}
