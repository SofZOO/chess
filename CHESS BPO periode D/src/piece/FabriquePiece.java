package piece;

import echiquier.IFabriquePiece;
import echiquier.IPiece;

import java.util.ArrayList;

public class FabriquePiece implements IFabriquePiece {

    /**
     * La fabrique de pièces qui nous permet de créer une liste de pièces avec toutes les pièces que l'on veut
     *
     * @param estBlanc le boolean qui permet de savoir si c'est un joueur BLANC ou NOIR
     * @return la liste de pièces du joueur
     */
    public ArrayList<IPiece> fabrique(boolean estBlanc) {
        ArrayList<IPiece> piecesDepart = new ArrayList<>();
        CouleurPiece couleur;
        couleur = estBlanc ? CouleurPiece.BLANC : CouleurPiece.NOIR;
        if (estBlanc) {
            piecesDepart.add(new Roi(couleur, 7, 4));
            piecesDepart.add(new Tour(couleur, 7, 0));
            piecesDepart.add(new Tour(couleur, 7, 7));
            piecesDepart.add(new Cavalier(couleur, 7, 6));
            piecesDepart.add(new Fou(couleur, 7, 5));
            piecesDepart.add(new Fou(couleur, 7, 2));
            piecesDepart.add(new Cavalier(couleur, 7, 1));

        } else {
            piecesDepart.add(new Roi(couleur, 0, 4));
            piecesDepart.add(new Tour(couleur, 0, 0));
            piecesDepart.add(new Tour(couleur, 0, 7));
            piecesDepart.add(new Cavalier(couleur, 0, 6));
            piecesDepart.add(new Cavalier(couleur, 0, 1));
            piecesDepart.add(new Fou(couleur, 0, 5));
            piecesDepart.add(new Fou(couleur, 0, 2));
        }
        return piecesDepart;
    }

}