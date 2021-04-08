package piece;

public abstract class Piece {
    private final char signe;
    private final couleurPiece couleur;

    private int posX;
    private int posY;

    public Piece(char sig,couleurPiece coul, int X, int Y){
        this.couleur=coul;
        this.posX=X;
        this.posY=Y;
        if (coul.equals(couleurPiece.BLANC))
            this.signe=Character.toUpperCase(sig);
        else this.signe=Character.toLowerCase(sig);

    }

    public void changeCoord(int x, int y){
        posX = x;
        posY=y;
    }
    public abstract boolean peutJouer(int x, int y);

    public char toChar(){
        return this.signe;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}
