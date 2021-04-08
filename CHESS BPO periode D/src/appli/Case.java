package appli;

import java.util.ArrayList;

public class Case {
    private ArrayList<Piece> pieceActuelle;
    private boolean estOccupé;


    public Case(){
        this.pieceActuelle = new ArrayList<>();
        estOccupé = false;
    }

    public String toString(){
        if (pieceActuelle.size()==0)
            return " ";
        else
            return Character.toString(pieceActuelle.get(0).toChar());
    }

    public void rajouterPiece(Piece p){
        pieceActuelle.add(p);
    }
}
