package échiquier;


public class Case {
    private IPiece pieceActuelle;/*grace a l'interface-> se renseigner*/

    public Case(){
        this.pieceActuelle = null;
    }

    public String toString(){
        if (pieceActuelle == null)
            return " ";
        else
            return Character.toString(pieceActuelle.toChar());
    }

    public boolean isEstOccupé(){
        return (this.pieceActuelle != null);
    }

    public void rajouterPiece(IPiece piece){
        this.pieceActuelle = piece;
    }
    public void retirerPiece(){
        pieceActuelle = null;
    }

    public IPiece getPieceActuelle() {
        return pieceActuelle;
    }

}
