package tests;
import static org.junit.Assert.*;

import echiquier.Coord;
import echiquier.IJoueur;
import echiquier.IPiece;
import echiquier.Plateau;
import joueurs.Humain;
import org.junit.Test;
import piece.FabriquePiece;

import javax.swing.plaf.ColorUIResource;

public class TestPlateau {

    /**
     * On test ici le nombre de cases que contient notre échiquier.
     */
    @Test
    public void vérificationNombreCases() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false,new FabriquePiece());
        Plateau p = new Plateau(jBlanc,jNoir);
        IPiece[][] echiquier = p.getEchiquier().clone();
        int nbCases =0;
        for (IPiece[] iPieces : echiquier) {
            for (int j = 0; j < iPieces.length; j++) {
                nbCases++;
            }
        }
        assertEquals(64,nbCases);
    }

    @Test
    public void horsDuPlateau(){
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false,new FabriquePiece());
        Plateau p = new Plateau(jBlanc,jNoir);


        // Je fais un dessin pour illustrer la situation
        //
        // 4 |     |     |
        //     ---   ---   ---
        // 3 |     |     |
        //     ---   ---   ---
        // 2 |     |     |
        //     ---   ---   ---
        // 1 |  T  |     |
        //     ---   ---   ---
        //      a     b     c
        // On a la tour en a1 et on va tester ici de changer ses coordonnées
        IPiece pi = p.laPiece(new Coord(7,0));

        assertEquals(pi.getCoord(), new Coord(7, 0));

        // on effectue le coup a1a4
        p.placerNouvelleCoord(pi.getCoord(),new Coord(4,0));

        assertEquals(pi.getCoord(),new Coord(4,0));


    }

    }