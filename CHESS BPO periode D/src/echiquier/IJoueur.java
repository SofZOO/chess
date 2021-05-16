package echiquier;

import java.util.ArrayList;

public interface IJoueur {

    ArrayList<IPiece> getPieces();

    void déplacer(String coup, IJoueur autreJoueur, Plateau p);

    void joueUnTour(IJoueur autreJoueur, Plateau p);

    IPiece leRoi();

    void aPerdu  ();

    String getNom();

    boolean isChessMat();

    boolean estHumain();

    boolean abandonne();

}
