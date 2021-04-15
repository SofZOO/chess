package échiquier;

import piece.CouleurPiece;
import piece.Piece;

public interface IPiece {

    void changeCoord(Coord c);

    boolean peutJouer(Coord c, Case[][] echiquier);

    char toChar();

    int getX();

    int getY();

    CouleurPiece getCouleur();

    boolean craintEchec();

    boolean compareCouleur(IPiece p);
}
