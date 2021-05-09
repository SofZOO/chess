package piece;

import echiquier.Coord;
import echiquier.IPiece;
import echiquier.Plateau;


public abstract class Piece implements IPiece {
    private final char signe;
    private final CouleurPiece couleur;
    private final Coord coord;
    private boolean estMangé;

    public Piece(char sig, CouleurPiece coul, int colonne, int ligne) {
        this.couleur = coul;
        coord = new Coord(colonne, ligne);
        this.signe = (coul.equals(CouleurPiece.BLANC) ? Character.toUpperCase(sig):Character.toLowerCase(sig));
        this.estMangé = false;
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

    public void estMangé() {
        this.estMangé = true;
    }

    public boolean getEstMangé() {
        return this.estMangé;
    }

}
