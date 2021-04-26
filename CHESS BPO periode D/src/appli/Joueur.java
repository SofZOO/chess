package appli;

import échiquier.IFabriquePiece;
import échiquier.IPiece;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private boolean echec;
    private boolean estBlanc;
    private ArrayList<IPiece> pieces;

    public Joueur (boolean blanc, IFabriquePiece fab){
        this.nom=nom;
        this.estBlanc=blanc;
        this.echec=false;
        this.pieces = fab.fabrique(estBlanc);
    }

    public ArrayList<IPiece> getPieces() {
        return pieces;
    }
}
