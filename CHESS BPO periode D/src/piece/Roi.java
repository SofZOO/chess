package piece;

public class Roi extends Piece {

    public Roi(couleurPiece coul, int x, int y){
        super('r',coul,x,y);
    }

    @Override
    public boolean peutJouer(int x, int y) {
        if (x == getPosX() && y == getPosY() + 1)
            return true;
        if (x == getPosX()+1 && y == getPosY() + 1)
            return true;
        if (x == getPosX()+1 && y == getPosY())
            return true;
        if (x == getPosX() +1 && y == getPosY()-1)
            return true;
        if (x == getPosX() && y == getPosY() -1)
            return true;
        if (x == getPosX() - 1  && y == getPosY() - 1)
            return true;
        if (x == getPosX() -1 && y == getPosY())
            return true;
        if (x == getPosX() -1 && y == getPosY()+1)
            return true;
        return false;
    }
}
