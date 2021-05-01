package echiquier;

import piece.CouleurPiece;

public interface IPiece {

    void changeCoord(Coord c);

    boolean peutJouer(Coord c, Case[][] echiquier);

    char toChar();

    int getLigne();

    int getColonne();

    Coord getCoord();

    CouleurPiece getCouleur();

    boolean craintEchec();

    boolean compareCouleur(IPiece p);
}
