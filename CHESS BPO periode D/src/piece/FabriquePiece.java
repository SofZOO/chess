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
            piecesDepart.add(new Roi(couleur, 2, 4));
            piecesDepart.add(new Tour(couleur, 1, 1));
        } else {
            piecesDepart.add(new Roi(couleur, 0, 4));
        }
        return piecesDepart;
    }

}