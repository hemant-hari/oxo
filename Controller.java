/* Print out the classification of a triangle, given three integer lengths
given on the command line. With no arguments, run the unit tests. */

class Controller {

    public static void main(String[] args) {
        Controller program = new Controller();
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
        System.err.println("  java -ea Controller");
        System.err.println("  java Controller A1");
        System.exit(1);
    }

    // Convert a string into an int, with -1 for illegal input.
    int[] convert(String s) {
        int[] pos = {-1,-1};
        if (s.length() != 2) { return pos; }
        try { pos[1] = Integer.parseInt(s.substring(1)) ; }
        catch (Exception err) { return pos; }
        pos[0] = 1;
        return pos;
    }

    // ---------- Testing ----------

    // Run the tests
    void test() {

    }

}
