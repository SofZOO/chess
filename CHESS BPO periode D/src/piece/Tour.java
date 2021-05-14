package piece;

import echiquier.Coord;
import echiquier.Plateau;

public class Tour extends Piece {

    public Tour(CouleurPiece couleur, int x, int y) {
        super('t', couleur, x, y);
    }


    @Override
    public boolean peutJouer(Coord c, Plateau p) {

        // Initialisation de 2 variables pour simplifier l'écriture des coordonées de destination
        int lignePiece = getCoord().getLigne();
        int colonnePiece = getCoord().getColonne();

        // Verifie si la on bouge la piece comme une tour, donc si le coup est un coup vertical ou horizontal
        if (!(lignePiece == c.getLigne() && colonnePiece != c.getColonne() || colonnePiece == c.getColonne() && lignePiece != c.getLigne()))
            return false;


        // Vérifie si c'est un coup vertical
        if (lignePiece != c.getLigne() && colonnePiece == c.getColonne()) {

            // Si la coord de base est inférieur à la coord de destination, alors la piece se déplace vers le bas
            if (lignePiece < c.getLigne()) {

                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getLigne() - 1; cmp > lignePiece; cmp--) {
                    if (p.laPiece(new Coord(cmp, colonnePiece)) != null) {
                        return false;
                    }
                }
                // Si la coord de base est supérieur à la coord de destination, alors la piece se deplace vers le haut
            } else if (lignePiece > c.getLigne()) {

                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getLigne() + 1; cmp < lignePiece; cmp++) {
                    if (p.laPiece(new Coord(cmp, colonnePiece)) != null) {
                        return false;
                    }
                }
            }
        }

        // Vérifie si c'est un coup horizontal
        if (lignePiece == c.getLigne() && colonnePiece != c.getColonne()) {

            // Si la coord de base est inférieur à la coord de destination, alors la piece se déplace vers la gauche
            if (colonnePiece < c.getColonne()) {

                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getColonne() - 1; cmp > colonnePiece; cmp--) {
                    if (p.laPiece(new Coord(lignePiece, cmp)) != null) {
                        return false;
                    }
                }
                // Si la coord de base est supérieur à la coord de destination, alors la piece se deplace vers le bas
            } else if (colonnePiece > c.getColonne()) {

                // On regarde pour chaque case de la pièce jusqu'a la destination si elle n'est pas occupé par une pièce pour ne pas la transpercer !
                for (int cmp = c.getColonne() + 1; cmp < colonnePiece; cmp++) {
                    if (p.laPiece(new Coord(lignePiece, cmp)) != null) {
                        return false;
                    }
                }
            }
        }

        // si on arrive ici, alors le coup est valable pour la tour et ne transperce aucune pièce
        return true;
    }
}
