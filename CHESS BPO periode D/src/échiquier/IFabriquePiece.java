package échiquier;

public interface IFabriquePiece {

    IPiece fabrique(int numero, Coord coord, boolean estBlanc);
}
