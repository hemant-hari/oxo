/*  Board class for Tic-Tac-Toe game. It has an internal array that stores
    the current game state in a board and has functions to place pieces
    as well as check the game state to see if it has reached a terminal
    state. */

class Board {
    private Occupied[][] Barray = new Occupied[3][3];
    private State state;
    private Occupied Piece;

    Board(Occupied Piece0) {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                Barray[i][j] = Occupied.Blank;
            }
        }
        state = State.Running;
        Piece = Piece0;
    }

    // --------- Public 'Library' Methods ---------

    Occupied getCurrentPiece() {
        return Piece;
    }

    Occupied[][] getCurrentBoard() {
        return Barray;
    }

    Occupied getPosition(int x, int y) {
        if (x<0 || y<0 || x>=3 || y>= 3) return Occupied.Fail;

        return Barray[y][x];
    }

    State updateGetState() {
        checkStateHorizontal();
        checkStateVertical();
        checkStateDiagonal();
        checkDraw();
        return state;
    }

    boolean placePiece(int x, int y) {
        Occupied tryPos = getPosition(x, y);
        if (tryPos == Occupied.Fail) return false ;
        if (tryPos == Occupied.Blank) {
            Barray[y][x] = Piece;
            if (Piece == Occupied.O) { Piece = Occupied.X; }
            else if (Piece == Occupied.X) { Piece = Occupied.O; }
            return true;
        }
        return false;
    }

    //Use with Caution! No checking for undoing only last move!!
    void undo(int x, int y) {
        if (Piece != Occupied.Blank){
            Piece = (Piece == Occupied.X) ? Occupied.O : Occupied.X;
        }
        Barray[y][x] = Occupied.Blank;
        if (state != State.Running) {state = State.Running;}
    }

    // --------- Internal Methods ---------

    private void checkStateHorizontal() {
        for (int i=0; i<3; i++) {
            if (Barray[i][0] == Barray[i][1] &&
                Barray[i][1] == Barray[i][2]) {
                if (Barray[i][0] == Occupied.X) { state = State.XWin; }
                else if (Barray[i][0] == Occupied.O) { state = State.OWin; }
            }
        }
    }

    private void checkStateVertical() {
        for (int i=0; i<3; i++) {
            if (Barray[0][i] == Barray[1][i] &&
                Barray[1][i] == Barray[2][i]) {
                if (Barray[0][i] == Occupied.X) { state = State.XWin; }
                else if (Barray[0][i] == Occupied.O) { state = State.OWin; }
            }
        }
    }

    private void checkStateDiagonal() {
        if ((Barray[0][0] == Barray[1][1] &&
            Barray[1][1] == Barray[2][2]) ||
            (Barray[2][0] == Barray[1][1] &&
            Barray[1][1] == Barray[0][2])) {
            if (Barray[1][1] == Occupied.X) { state = State.XWin; }
            else if (Barray[1][1] == Occupied.O) { state = State.OWin; }
        }
    }

    private void checkDraw() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (Barray[i][j] == Occupied.Blank) return;
            }
        }

        if (state == State.Running) state = State.Draw;
    }

    // --------- Run Methods ---------

    public static void main(String[] args) {
        Board game = new Board(Occupied.O);
        game.run();
    }

    private void run() {
        boolean testing = false;
        assert(testing = true);
        if (! testing) throw new Error("Use java -ea Triangle");
        testInit();
        testGet();
        testPlace();
        testWinState();
    }

    // --------- Test Suite ---------

    private void testInit() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                assert(getPosition(j,i) == Occupied.Blank);
            }
        }
    }

    // Test Invalid Board Positions
    private void testGet() {
        assert(getPosition(3, 3) == Occupied.Fail);
        assert(getPosition(-1, -1) == Occupied.Fail);
    }

    // Test cases for piece placement
    private void testPlace() {
        assert(placePiece(0, 0));
        assert(! placePiece(0, 0));
        assert(! placePiece(4, 1));
        assert(! placePiece(2, 3));
    }

    private void testWinState() {
        Barray[0][0] = Barray[0][1] = Barray[0][2] = Occupied.X;
        assert(updateGetState() == State.XWin);
        Barray[0][0] = Barray[1][1] = Barray[2][2] = Occupied.O;
        assert(updateGetState() == State.OWin);
        state = State.Running;
        Barray[1][0] = Barray[2][1] = Barray[0][2] = Occupied.O;
        Barray[1][2] = Barray[2][0] = Barray[2][2] = Occupied.X;
        state = updateGetState();
        assert(state == State.Draw);
        debugDisplay();
    }

    void debugDisplay() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                System.out.print(Barray[i][j]);
            }
            System.out.print('\n');
        }
    }

}
