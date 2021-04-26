package échiquier;

import java.util.ArrayList;

public class Case {
    private ArrayList<IPiece> pieceActuelle;/*grace a l'interface-> se renseigner*/

    public Case(){
        this.pieceActuelle = new ArrayList<>();
    }

    public String toString(){
        if (pieceActuelle.size()==0)
            return " ";
        else
            return Character.toString(pieceActuelle.get(0).toChar());
    }

    public boolean estOccupé(){
        return (this.pieceActuelle.size() > 0);
    }

    public void rajouterPiece(IPiece p){
        if (estOccupé()){
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
        if (estOccupé())
            return pieceActuelle.get(0);
        return null;
    }

}
