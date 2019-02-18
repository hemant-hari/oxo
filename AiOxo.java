/* Print out the classification of a triangle, given three integer lengths
given on the command line. With no arguments, run the unit tests. */

import java.util.*;

class AiOxo {

    public static void main(String[] args) {
        AiOxo program = new AiOxo();
        program.run(args);
    }

    // Deal with the command line arguments
    void run(String[] args) {
        boolean testing = false;
        assert(testing = true);
        if (args.length == 0 && testing) test() ;
        else if (args.length == 1) init(args) ;
        else usage();
    }

    // Give a usage message and shut down.
    void usage() {
        System.err.println("Use:");
        System.err.println("  java -ea Oxo");
        System.err.println("  java Oxo FIRSTPIECE // where firstpiece can be X or O");
        System.exit(1);
    }

    // Convert a string into an int, with -1 for illegal input.
    int[] convert(String s) {
        int[] pos = {-1,-1};
        if (s.length() != 2) { return pos; }
        try { pos[1] = Integer.parseInt(s.substring(1)) - 1 ; }
        catch (Exception err) { return pos; }
        if ( Character.isLetter(s.charAt(0)) ) {
            pos[0] = Character.toUpperCase(s.charAt(0)) - 'A';
        }
        return pos;
    }

    void init(String[] args) {
        if (args[0].equals("X")) { play(Occupied.X); }
        else if (args[0].equals("O")) { play(Occupied.O); }
    }

    void play(Occupied Piece) {
        Scanner reader = new Scanner(System.in);
        Board gameBoard = new Board(Piece);
        String input = new String();
        Display output = new Display(gameBoard);
        Ai comp = new Ai(gameBoard, getAi(Piece));
        int[] pos;
        while (gameBoard.updateGetState() == State.Running) {
            if (gameBoard.getCurrentPiece() == Piece){
                pos = comp.makeMove();
                System.out.println(pos[0] + "" + pos[1]);
                gameBoard.placePiece(pos[0], pos[1]);
            }
            else{
                System.out.print("Input position to place piece ");
                System.out.print(gameBoard.getCurrentPiece() + "  ");
                pos = convert(reader.nextLine());
                if (gameBoard.placePiece(pos[0], pos[1]) == false) {
                    System.out.println("Invalid location specified, try again") ;
                }
            }
            output.updateDisplay();
        }

        System.out.println(gameBoard.updateGetState());
    }

    State getAi(Occupied Piece) {
        return (Piece == Occupied.O) ? State.OWin : State.XWin;
    }

    // ---------- Testing ----------

    // Run the tests
    private void test() {
        testConvert();
    }

    private void testConvert() {
        int[] pos;
        pos = convert("A1");
        assert(pos[0] == 0);
        pos = convert("D4");
        assert(pos[0] == 3);
        pos = convert("A1SDA");
        assert(pos[0] == -1);
        pos = convert("b2");
        assert(pos[0] == 1);
    }
}
