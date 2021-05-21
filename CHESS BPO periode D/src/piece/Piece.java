package piece;

import echiquier.Coord;
import echiquier.IPiece;


public abstract class Piece implements IPiece {
    private final char signe; // le signe de la pièce
    private final CouleurPiece couleur; // la couleur de la pièce (BLANC ou NOIR
    private final Coord coord; // les coordonnées de la pièce
    private boolean estMangé; // on a besoin de savoir si une pièce est mangée ou non sur le plateau

    /**
     * Le constructeur d'une pièce
     *
     * @param sig     le signe de la pièce
     * @param coul    la couleur de la pièce
     * @param colonne colonne ou la pièce est placée
     * @param ligne   la ligne ou la pièce est placée
     */
    protected Piece(char sig, CouleurPiece coul, int colonne, int ligne) {
        this.couleur = coul;
        coord = new Coord(colonne, ligne);
        // permet de mettre en majuscule si la couleur de la pièce est BLANC, sinon en minuscule
        this.signe = coul.equals(CouleurPiece.BLANC) ? Character.toUpperCase(sig) : Character.toLowerCase(sig);
        this.estMangé = false;
    }


    /**
     * Permet de modifier les coordonnées de la pièce
     *
     * @param c les nouvelle coordonnées de la pièce
     */
    @Override
    public void changeCoord(Coord c) {
        this.coord.setLigne(c.getLigne());
        this.coord.setColonne(c.getColonne());
    }

    /**
     * Renvoie le signe d'une pièce
     *
     * @return le signe de la pièce
     */
    @Override
    public char toChar() {
        return this.signe;
    }

    /**
     * Renvoie les coordonées de la pièce
     *
     * @return les coordonnées de la pièce
     */
    @Override
    public Coord getCoord() {
        return this.coord;
    }

    /**
     * Renvoie la couleur de la pièce
     *
     * @return BLANC ou NOIR en fonction de la couleur de la pièce
     */
    @Override
    public CouleurPiece getCouleur() {
        return couleur;
    }

    /**
     * Détermine sur une pièce craint ou non l'échec
     *
     * @return True si la pièce craint l'échec, sinon return False
     */
    @Override
    public boolean craintEchec() {
        return false;
    }

    /**
     * Permet de changer l'état de vie de la pièce, si cette méthode est appelée alors la pièce s'est fait manger
     */
    public void seFaitManger() {
        this.estMangé = true;
    }

    /**
     * Renvoie la valeur du boolean permettant de savoir si une pièce s'est faite mangée ou non
     *
     * @return renvoie la valeur du boolean estMangé
     */
    public boolean isMangé() {
        return this.estMangé;
    }

}
