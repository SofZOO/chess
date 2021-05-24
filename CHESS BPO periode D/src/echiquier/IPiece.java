package echiquier;

import piece.CouleurPiece;

public interface IPiece {

    /**
     * Permet de modifier les coordonnées de la pièce
     *
     * @param c les nouvelle coordonnées de la pièce
     */
    void changeCoord(Coord c);

    /**
     * Permet de savoir si le déplacement de la pièce peut être joué ou non
     *
     * @param c les coordonnées de la pièces
     * @param p le plateau
     * @return False si la pièce ne peut pas jouer
     */
    boolean peutJouer(Coord c, Plateau p);

    /**
     * Renvoie le signe d'une pièce
     *
     * @return le signe de la pièce
     */
    char toChar();

    /**
     * Renvoie les coordonées de la pièce
     *
     * @return les coordonnées de la pièce
     */
    Coord getCoord();

    /**
     * Renvoie la couleur de la pièce
     *
     * @return BLANC ou NOIR en fonction de la couleur de la pièce
     */
    CouleurPiece getCouleur();

    /**
     * Détermine sur une pièce craint ou non l'échec
     *
     * @return True si la pièce craint l'échec, sinon return False
     */
    boolean craintEchec();

    /**
     * Permet de changer l'état de vie de la pièce, si cette méthode est appelée alors la pièce s'est fait manger
     */
    void seFaitManger();

    /**
     * Renvoie la valeur du boolean permettant de savoir si une pièce s'est faite mangée ou non
     *
     * @return renvoie la valeur du boolean estMangé
     */
    boolean isMangé();


}
