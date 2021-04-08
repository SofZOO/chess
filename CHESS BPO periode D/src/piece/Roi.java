package piece;

public class Roi extends Piece {

    public Roi(couleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(int x, int y) {
        return false;
    }
}
