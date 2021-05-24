package echiquier;

import java.util.ArrayList;

public interface IFabriquePiece {

    /**
     * Permet de créer et fabriquer des pièces
     *
     * @param estBlanc le boolean qui dit si la couleur de la pièce est BLANC ou NOIR
     * @return la liste de pièces créées
     */
    ArrayList<IPiece> fabrique(boolean estBlanc);


}
