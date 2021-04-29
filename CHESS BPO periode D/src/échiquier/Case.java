package échiquier;

import java.util.ArrayList;

public class Case {
    private ArrayList<IPiece> pieceActuelle;/*grace a l'interface-> se renseigner*/

    public Case(){
        this.pieceActuelle = new ArrayList<>();
    }

    public String toString(){
        if (pieceActuelle.size()==0 ||pieceActuelle.get(0) == null)
            return " ";
        else
            return Character.toString(pieceActuelle.get(0).toChar());
    }

    public boolean isEstOccupé(){
        return (this.pieceActuelle.size() > 0);
    }

    public void rajouterPiece(IPiece p){
        if (isEstOccupé()){
            pieceActuelle.add(p);
            pieceActuelle.remove(0);
        }
        else
            pieceActuelle.add(p);
    }
    public void retirerPiece(){
        pieceActuelle.remove(0);
    }

    public IPiece getPieceActuelle() {
        if (isEstOccupé())
            return pieceActuelle.get(0);
        return null;
    }

}
