package appli;

import echiquier.IFabriquePiece;
import echiquier.IPiece;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private boolean echecEtMat;
    private boolean estBlanc;
    private final ArrayList<IPiece> pieces;

    public Joueur (String nom, boolean blanc, IFabriquePiece fab){
        this.nom= nom;
        this.estBlanc=blanc;
        this.pieces = fab.fabrique(estBlanc);
        this.echecEtMat = false;
    }

    public ArrayList<IPiece> getPieces() {
        return pieces;
    }

    public IPiece leRoi(){
        return pieces.get(0);
    }

    public void aPerdu(){
        System.out.println("ECHEC ET MAT");
        this.echecEtMat = true;
    }

    public String getNom() {
        return nom;
    }

    public boolean getEchecEtMat(){
        return this.echecEtMat;
    }
}
