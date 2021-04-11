package échiquier;

import piece.Piece;

import java.util.ArrayList;

public class Case {
    private ArrayList<Piece> pieceActuelle;/*grace a l'interface-> se renseigner*/
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
    public void retirerPiece(){
        pieceActuelle.remove(0);
    }
    public Piece getPieceActuelle() {
        return pieceActuelle.get(0);
    }
}
