package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Tour extends Piece {

    /**
     * Le constructeur d'une Tour
     * @param couleur la couleur de la Tour
     * @param x la coordonnée en x
     * @param y la coordonnée en y
     */
    public Tour(CouleurPiece couleur, int x, int y) {
        super('t', couleur, x, y);
    }

    /**
     *
     * @param c la coordonnée de destination de la pièce
     * @param p le plateau de jeu ou les pièces se déplacent
     * @return True si on peut jouer le coup qui se fait sur la Tour
     */
    @Override
    public boolean peutJouer(Coord c, Plateau p) {
        // Initialisation de 2 variables pour simplifier l'écriture des coordonées de destination
        int lignePiece = getCoord().getLigne();
        int colonnePiece = getCoord().getColonne();

        // Verifie si la on bouge la piece comme une tour, donc si le coup est un coup vertical ou horizontal
        if (!(lignePiece == c.getLigne() && colonnePiece != c.getColonne() || colonnePiece == c.getColonne() && lignePiece != c.getLigne()))
            return false;

        if (coupVertical(c, p, lignePiece, colonnePiece))
            return false;

        return !coupHorizontal(c, p, lignePiece, colonnePiece);

    }

    /**
     * Permet de vérifier si la tour peut jouer à l'horizontale
     * @param c les coordonnées
     * @param p le plateau
     * @param lignePiece la coordonnée de la ligne
     * @param colonnePiece la coordonnée de la colonne
     * @return True si elle peut jouer le coup à l'horizontal
     */
    private boolean coupHorizontal(Coord c, Plateau p, int lignePiece, int colonnePiece) {
        // Vérifie si c'est un coup horizontal
        if (lignePiece == c.getLigne() && colonnePiece != c.getColonne()) {
            // Si la coord de base est inférieur à la coord de destination, alors la piece se déplace vers la gauche
            if (colonnePiece < c.getColonne()) {
                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getColonne() - 1; cmp > colonnePiece; cmp--) {
                    if (p.laPiece(new Coord(lignePiece, cmp)) != null) {
                        return true;
                    }
                }
                // Si la coord de base est supérieur à la coord de destination, alors la piece se deplace vers le bas
            } else if (colonnePiece > c.getColonne()) {
                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getColonne() + 1; cmp < colonnePiece; cmp++) {
                    if (p.laPiece(new Coord(lignePiece, cmp)) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * Permet de vérifier si la tour peut jouer à l'horizontale
     * @param c les coordonnées
     * @param p le plateau
     * @param lignePiece la coordonnée de la ligne
     * @param colonnePiece la coordonnée de la colonne
     * @return True si elle peut jouer le coup à la verticale
     */
    private boolean coupVertical(Coord c, Plateau p, int lignePiece, int colonnePiece) {
        // Vérifie si c'est un coup vertical
        if (lignePiece != c.getLigne() && colonnePiece == c.getColonne()) {
            // Si la coord de base est inférieur à la coord de destination, alors la piece se déplace vers le bas
            if (lignePiece < c.getLigne()) {
                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getLigne() - 1; cmp > lignePiece; cmp--) {
                    if (p.laPiece(new Coord(cmp, colonnePiece)) != null) {
                        return true;
                    }
                }
                // Si la coord de base est supérieur à la coord de destination, alors la piece se deplace vers le haut
            } else if (lignePiece > c.getLigne()) {

                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getLigne() + 1; cmp < lignePiece; cmp++) {
                    if (p.laPiece(new Coord(cmp, colonnePiece)) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
