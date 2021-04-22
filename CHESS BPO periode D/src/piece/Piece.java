package piece;

import échiquier.Case;
import échiquier.Coord;
import échiquier.IPiece;

public class Piece implements IPiece {
    private final char signe;
    private final CouleurPiece couleur;
    private Coord coord;

    public Piece(char sig, CouleurPiece coul, int colonne, int ligne){
        this.couleur=coul;
        coord = new Coord(colonne,ligne);
        if (coul.equals(CouleurPiece.BLANC))
            this.signe=Character.toUpperCase(sig);
        else this.signe=Character.toLowerCase(sig);
    }

    @Override
    public void changeCoord(Coord c){
        this.coord.setLigne(c.getLigne());
        this.coord.setColonne(c.getColonne());
    }

    @Override
    public boolean peutJouer(Coord c, Case[][] echiquier){return true;}

    @Override
    public char toChar(){
        return this.signe;
    }

    @Override
    public int getLigne() {
        return coord.getLigne();
    }

    @Override
    public int getColonne() {
        return coord.getColonne();
    }

    @Override
    public CouleurPiece getCouleur() {
        return couleur;
    }

    @Override
    public boolean craintEchec(){
        return false;
    }

    @Override
    public boolean compareCouleur(IPiece p) {
        return this.couleur.equals(p.getCouleur());
    }
}
