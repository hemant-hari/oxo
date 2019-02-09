/* Print out the classification of a triangle, given three integer lengths
given on the command line. With no arguments, run the unit tests. */

import java.io.*;

class Oxo {

    public static void main(String[] args) {
        Oxo program = new Oxo();
        program.run(args);
    }

    // Deal with the command line arguments
    void run(String[] args) {
        boolean testing = false;
        assert(testing = true);
        if (args.length == 0 && testing) test();
        else if (args.length == 1) ;
        else usage();
    }

    // Give a usage message and shut down.
    void usage() {
        System.err.println("Use:");
        System.err.println("  java -ea Oxo");
        System.err.println("  java Oxo A1");
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
