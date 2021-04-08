package appli;

public class Tour extends Piece{

    public Tour(couleurPiece couleur, int x, int y){
        super('t',couleur,x,y);
    }

    @Override
    public boolean peutJouer(int x, int y) {
        if (getPosX() == x && getPosY() !=y){
            return true;
        }
        if (getPosX() == y && getPosY() !=x){
            return true;
        }
        return false;
    }
}
