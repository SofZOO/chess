package échiquier;

public interface IFabriquePiece {

    public IPiece fabrique(int numero, Coord coord, boolean estBlanc);
}
