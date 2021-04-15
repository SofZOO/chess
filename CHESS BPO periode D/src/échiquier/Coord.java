package échiquier;

public class Coord {
    private int X;
    private int Y;

    public Coord(int X, int Y){
        placer(X,Y);
    }

    public void placer(int X , int ligne){
        this.X = X;
        this.Y = Y;
    }
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
}
