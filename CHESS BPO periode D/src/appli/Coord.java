package appli;

public class Coord {
    private int X;
    private int Y;

    public Coord(int x, int y){
        placer(x,y);
    }

    public void placer(int x , int y){
        this.X = x;
        this.Y = y;
    }
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
