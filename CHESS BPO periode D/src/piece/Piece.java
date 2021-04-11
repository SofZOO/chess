package piece;

import appli.Coord;

public abstract class Piece {
    private final char signe;
    private final couleurPiece couleur;
    Coord coord;
    private int posX;
    private int posY;

    public Piece(char sig,couleurPiece coul, int X, int Y){
        this.couleur=coul;
        this.posX=X;
        this.posY=Y;
        coord = new Coord(X,Y);
        if (coul.equals(couleurPiece.BLANC))
            this.signe=Character.toUpperCase(sig);
        else this.signe=Character.toLowerCase(sig);

    }

    public void changeCoord(Coord c){
        posX = c.getX();
        posY = c.getY();
    }
    public abstract boolean peutJouer(Coord c);

    public char toChar(){
        return this.signe;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public couleurPiece getCouleur() {
        return couleur;
    }
}
