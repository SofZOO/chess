package echiquier;

public class Coord {
    private int ligne;
    private int colonne;

    public Coord(int ligne, int colonne) {
        placer(ligne, colonne);
    }

    public void placer(int li, int co) {
        this.ligne = li;
        this.colonne = co;
    }

    public boolean compare(Coord coord) {
        return ligne == coord.ligne && colonne == coord.colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public String toString() {
        return (char) ('a' + this.colonne) + Integer.toString(8 -
                this.ligne);
    }
}
