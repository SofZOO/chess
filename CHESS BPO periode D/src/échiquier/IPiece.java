package échiquier;

import piece.CouleurPiece;

public interface IPiece {

    void changeCoord(Coord c);

    boolean peutJouer(Coord c);

    char toChar();

    int getColonne();

    int getLigne();

    CouleurPiece getCouleur();
}
