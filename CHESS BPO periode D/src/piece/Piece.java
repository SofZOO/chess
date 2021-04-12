package piece;

import appli.Coord;

public abstract class Piece {
    private final char signe;
    private final couleurPiece couleur;
    Coord coord;
    private int colonne;
    private int ligne;

    public Piece(char sig,couleurPiece coul, int colonne, int ligne){
        this.couleur=coul;
        this.colonne=colonne;
        this.ligne=ligne;
        coord = new Coord(colonne,ligne);
        if (coul.equals(couleurPiece.BLANC))
            this.signe=Character.toUpperCase(sig);
        else this.signe=Character.toLowerCase(sig);

    }

    public void changeCoord(Coord c){
        colonne = c.getColonne();
        ligne = c.getLigne();
    }
    public abstract boolean peutJouer(Coord c);

    public char toChar(){
        return this.signe;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public couleurPiece getCouleur() {
        return couleur;
    }
}
