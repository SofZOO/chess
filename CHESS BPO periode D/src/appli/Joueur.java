package appli;

import echiquier.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Joueur implements IJoueur {
    private final ArrayList<IPiece> pieces;
    private String nom;
    private boolean echecEtMat;
    private boolean estBlanc;

    public Joueur(String nom, boolean blanc, IFabriquePiece fab) {
        this.nom = nom;
        this.estBlanc = blanc;
        this.pieces = fab.fabrique(estBlanc);
        this.echecEtMat = false;
    }

    @Override
    public void joue(IJoueur autreJoueur, Plateau p) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Tour du joueur " + this.nom);
            String coup = sc.nextLine().trim().toLowerCase(Locale.ROOT);
            if (p.doitRejouer(coup, this)) {
                System.out.println("coup de merde pour le joueur " + this.nom);
                continue;
            } else {
                déplacer(coup, autreJoueur, p);
                break;
            }
        }
    }

    @Override
    public void déplacer(String coup, IJoueur autreJoueur, Plateau p) {
        Coord coordIni, coordFin;
        char x = coup.charAt(0), x2 = coup.charAt(2);/*b7b8*/
        int y = p.intoInt(coup, 1), y2 = p.intoInt(coup, 3);
        coordIni = p.getCoord(x, y);
        coordFin = p.getCoord(x2, y2);

        p.placerNouvelleCoord(coordIni, coordFin);

        if (p.echec(autreJoueur, p.getListePieces())) {
            System.out.println("le joueur " + autreJoueur.getNom() + " est echec");
            if (p.chessmat(autreJoueur)) {
                autreJoueur.aPerdu();
            }
        }
        if (p.chesspat(this)) {
            p.setEchecEtPat(true);
            System.out.println("echec et pat");
        }
        if (p.chesspat(autreJoueur)) {
            p.setEchecEtPat(true);
            System.out.println("echec et pat");
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
        System.out.println("ECHEC ET MAT");
        this.echecEtMat = true;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public boolean getEchecEtMat() {
        return this.echecEtMat;
    }

}
