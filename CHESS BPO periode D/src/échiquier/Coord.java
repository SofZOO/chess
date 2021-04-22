package échiquier;

public class Coord {
    private int ligne;
    private int colonne;

    public Coord(int ligne, int colonne){
        placer(ligne,colonne);
    }

    public void placer(int li , int co){
        this.ligne = li;
        this.colonne = co;
    }
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }
}
