package echiquier;

public class Coord {
    private int ligne; // la ligne ou la pièce est placée
    private int colonne; // la colonne ou la pièce est placée

    /**
     * Le constructeur d'une coordonnée qui permet de placer une pièce à des coordonnées données
     *
     * @param ligne   la ligne d'arrivée
     * @param colonne la colonne d'arrivée
     */
    public Coord(int ligne, int colonne) {
        placer(ligne, colonne);
    }

    /**
     * permet de placer une pièce à un des coordonnées
     *
     * @param li la ligne de destination
     * @param co la colonne de destination
     */
    private void placer(int li, int co) {
        this.ligne = li;
        this.colonne = co;
    }

    /**
     * permet de comparer si des coordonnées sont les mêmes
     *
     * @param coord les coordonnées d'une pièce
     * @return true si ce sont les mêmes, sinon return false
     */
    public boolean compare(Coord coord) {
        return ligne == coord.ligne && colonne == coord.colonne;
    }

    /**
     * Permet d'avoir la ligne d'une pièce
     *
     * @return la coordonnée de la ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Permet de modifier la valeur d'une ligne
     *
     * @param ligne la ligne d'une pièce
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * Permet d'avoir la colonne d'une pièce
     *
     * @return la coordonnée de la colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Permet de modifier la valeur d'une colonne
     *
     * @param colonne la colonne d'une pièce
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * Permet de renvoyer les coordonnées d'une pièce
     *
     * @return les coordonnées d'une pièce au format de l'échiquier (a6) par exemple
     */
    public String toString() {
        return (char) ('a' + this.colonne) + Integer.toString(8 -
                this.ligne);
    }
}
