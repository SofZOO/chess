package tests;

import echiquier.Coord;
import echiquier.IJoueur;
import echiquier.Plateau;
import joueurs.Humain;
import org.junit.Test;
import piece.FabriquePiece;

import static org.junit.Assert.*;

public class TestPiece {

    @Test
    public void testTour() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc,jNoir);

        jBlanc.déplacer("c1a3",jNoir,p);

        // Je deplace le fou de c1 à a3 pour executer mon test
        //
        // 4 |     |     |
        //     ---   ---   ---
        // 3 |  F  |     |
        //     ---   ---   ---
        // 2 |     |     |
        //     ---   ---   ---
        // 1 |  T  |  C  |
        //     ---   ---   ---
        //      a     b     c

        // on tente de deplacer la tour en c1, ce qui n'est pas possible car le cavalier C bloque la route
        assertFalse(p.laPiece(new Coord(7,0)).peutJouer(new Coord(7,2),p));

        // on tente de deplacer la tour en a4, ce qui n'est pas possible car le fou F bloque la route
        assertFalse(p.laPiece(new Coord(7,0)).peutJouer(new Coord(4,0),p));

        // on tente de deplacer la tour en a2, c'est un coup possible car aucune piece sur le chemin
        assertTrue(p.laPiece(new Coord(7,0)).peutJouer(new Coord(6,0),p));

        // on tente de deplacer la tour en c3, c'est un coup impossible pour une tour
        assertFalse(p.laPiece(new Coord(7,0)).peutJouer(new Coord(5,2),p));
    }

    @Test
    public void testFou() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc,jNoir);

        jBlanc.déplacer("b1d2",jNoir,p);



        // Je deplace le cavalier de b1 à d2 pour executer mon test
        //
        // 4 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 3 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 2 |     |     |     |  C  |     |
        //     ---   ---   ---   ---   ---
        // 1 |  T  |     |  F  |     |     |
        //     ---   ---   ---   ---   ---
        //      a     b     c     d     e


        //on tente de deplacer le fou en e3, ce qui n'est pas possible car le cavalier C bloque la route
        assertFalse(p.laPiece(new Coord(7,2)).peutJouer(new Coord(5,4),p));

        //on tente de deplacer le fou en a3, c'est un coup possible car aucune piece sur le chemin
        assertTrue(p.laPiece(new Coord(7,2)).peutJouer(new Coord(5,0),p));

        //on tente de deplacer le fou en c3, ce qui n'est pas possible car il est impossible pour un fou de se deplacer en vertical
        assertFalse(p.laPiece(new Coord(7,2)).peutJouer(new Coord(5,2),p));
    }

    @Test
    public void testCavalier() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc,jNoir);

        jBlanc.déplacer("c1a3",jNoir,p);


        //
        // 4 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 3 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 2 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 1 |  T  |  C  |  F  |     |     |
        //     ---   ---   ---   ---   ---
        //      a     b     c     d     e

        // on tente de deplacer le cavalier en b3, ce qui n'est pas possible car il est impossible
        // pour un cavalier de se deplacer en vertical
        assertFalse(p.laPiece(new Coord(7,1)).peutJouer(new Coord(5,1),p));

        // on tente de deplacer le cavalier en a3, c'est un coup possible
        assertTrue(p.laPiece(new Coord(7,1)).peutJouer(new Coord(5,0),p));

        // on tente de deplacer le cavalier en a3, c'est un coup possible
        assertTrue(p.laPiece(new Coord(7,1)).peutJouer(new Coord(6,3),p));

    }

    @Test
    public void testRoi() {
        IJoueur jBlanc = new Humain(true, new FabriquePiece());
        IJoueur jNoir = new Humain(false, new FabriquePiece());
        Plateau p = new Plateau(jBlanc,jNoir);

        jBlanc.déplacer("c1a3",jNoir,p);


        //
        // 4 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 3 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 2 |     |     |     |     |     |
        //     ---   ---   ---   ---   ---
        // 1 |  F  |     |  R  |  F  |  T  |
        //     ---   ---   ---   ---   ---
        //      c     d     e     f     g

        // on tente de deplacer le roi en e3, ce qui n'est pas possible pour un roi
        assertFalse(p.laPiece(new Coord(7,4)).peutJouer(new Coord(5,4),p));

        // on tente de deplacer le roi en e2, c'est un coup possible
        assertTrue(p.laPiece(new Coord(7,4)).peutJouer(new Coord(6,4),p));

        // on tente de deplacer le roi en d1, c'est un coup possible
        assertTrue(p.laPiece(new Coord(7,4)).peutJouer(new Coord(7,3),p));

    }
}
