package tests;


import appli.Appli;
import echiquier.IFabriquePiece;
import echiquier.IPiece;
import org.junit.Test;
import piece.CouleurPiece;
import piece.FabriquePiece;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class TestFabriquePièce {



    @Test
    public void vérificationMéthodeFabrique(){

        IFabriquePiece fab = new FabriquePiece();
        ArrayList<IPiece> listeDePieces = fab.fabrique(true);

        assertEquals(listeDePieces.size(),7);

        for (IPiece piece : listeDePieces)
            assertEquals(piece.getCouleur(), CouleurPiece.BLANC);

        listeDePieces = fab.fabrique(false);

        assertEquals(listeDePieces.size(),7);

        for (IPiece piece : listeDePieces)
            assertEquals(piece.getCouleur(), CouleurPiece.NOIR);
    }

    @Test
    public void vérificationMéthodeFabriqueFinale(){

        Appli.setChoixPartieFinale(true);

        IFabriquePiece fab = new FabriquePiece();
        ArrayList<IPiece> listeDePieces = fab.fabrique(true);

        assertEquals(listeDePieces.size(),2);

        for (IPiece piece : listeDePieces)
            assertEquals(piece.getCouleur(), CouleurPiece.BLANC);

        listeDePieces = fab.fabrique(false);

        assertEquals(listeDePieces.size(),1);

        for (IPiece piece : listeDePieces)
            assertEquals(piece.getCouleur(), CouleurPiece.NOIR);
    }
}
