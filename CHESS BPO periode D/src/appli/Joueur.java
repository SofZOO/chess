package appli;

import echiquier.IFabriquePiece;
import echiquier.IPiece;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private boolean echec;
    private boolean estBlanc;
    private final ArrayList<IPiece> pieces;

    public Joueur (String nom, boolean blanc, IFabriquePiece fab){
        this.nom= nom;
        this.estBlanc=blanc;
        this.echec=false;
        this.pieces = fab.fabrique(estBlanc);
    }

    public ArrayList<IPiece> getPieces() {
        return pieces;
    }

    public IPiece leRoi(){
        return pieces.get(0);
    }

    public String getNom() {
        return nom;
    }
}
