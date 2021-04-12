package appli;

public class Coord {
    private int colonne;
    private int ligne;

    public Coord(int colonne, int ligne){
        placer(colonne,ligne);
    }

    public void placer(int colonne , int ligne){
        this.colonne = colonne;
        this.ligne = ligne;
    }
    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }
}
