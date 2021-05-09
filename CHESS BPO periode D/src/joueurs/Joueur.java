package joueurs;

import echiquier.*;

import java.util.ArrayList;


public abstract class Joueur implements IJoueur {
    private final String nom;
    private final ArrayList<IPiece> pieces;
    private boolean echecEtMat;

    public Joueur(String nom, boolean blanc, IFabriquePiece fab) {
        this.nom = nom;
        this.pieces = fab.fabrique(blanc);
        this.echecEtMat = false;
    }

    @Override
    public abstract void joue(IJoueur autreJoueur, Plateau p);

    @Override
    public void déplacer(String coup, IJoueur autreJoueur, Plateau p) {
        Coord coordIni, coordFin;
        char x = coup.charAt(0), x2 = coup.charAt(2);/*b7b8*/
        int y = Plateau.intoInt(coup, 1), y2 = Plateau.intoInt(coup, 3);
        coordIni = p.getCoord(x, y);
        coordFin = p.getCoord(x2, y2);

        p.placerNouvelleCoord(coordIni, coordFin);

        if (p.echec(autreJoueur, p.getListePieces())) {
            System.out.println("le joueur " + autreJoueur.getNom() + " est en position d'échec");
            if (p.chessmat(autreJoueur)) {
                autreJoueur.aPerdu();
            }
        }
    }

    @Override
    public ArrayList<IPiece> getPieces() {
        return pieces;
    }

    @Override
    public IPiece leRoi() {
        return pieces.get(0);
    }/*reste bizarre car n'est pas logique, on return l'indice 0 puisqu'on insère le roi en premier*/

    @Override
    public void aPerdu() {
        this.echecEtMat = true;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public boolean getEchecEtMat() {
        return this.echecEtMat;
    }

}
