package piece;

public class Tour extends Piece{

    public Tour(couleurPiece couleur, int x, int y){
        super('t',couleur,x,y);
    }

    @Override
    public boolean peutJouer(int x, int y) {
        if (getPosX() == x && getPosY() !=y){
            return true;
        }
        return getPosX() == y && getPosY() != x;
    }
}
