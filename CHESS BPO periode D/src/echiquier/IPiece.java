package echiquier;

import piece.CouleurPiece;

public interface IPiece {

    void changeCoord(Coord c);

    boolean peutJouer(Coord c, Plateau p);

    char toChar();

    Coord getCoord();

    CouleurPiece getCouleur();

    boolean craintEchec();

    void estMangé();

    boolean isMangé();


}
