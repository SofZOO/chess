package piece;

public class Tour extends Piece{

    public Tour(couleurPiece couleur, int x, int y){
        super('t',couleur,x,y);
    }

    @Override
    public boolean peutJouer(int x, int y) {
        return getPosX() == x && getPosY() !=y || getPosY() == y && getPosX() != x;
    }
}
