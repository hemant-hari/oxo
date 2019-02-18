class Ai {
    private Board initBoard;
    private int[][] scores = new int[3][3];
    private int recLevel;
    private State AIWinState;

    Ai(Board Board0, State AIWinState0){
        initBoard = Board0;
        AIWinState = AIWinState0;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (initBoard.getPosition(j, i) != Occupied.Blank) scores[i][j] = -1000000000;
            }
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Ai prog =  new Ai(new Board(Occupied.O), State.OWin);
        prog.run();
    }

    private void run() {
        initBoard.placePiece(0,0);
        initBoard.placePiece(0,1);
        initBoard.placePiece(0,2);
        initBoard.placePiece(1,1);
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (initBoard.getPosition(j, i) == Occupied.Blank) {scores[i][j] = minimax(j,i,true);}
            }
        }

        debugDisplay();
    }

    int minimax(int x, int y, boolean maximizingPlayer) {
        State currstate = initBoard.updateGetState();
        Display output = new Display(initBoard);
        output.updateDisplay();
        if (currstate != State.Running) {
            System.out.println("Terminal!" + currstate);
            if (currstate == AIWinState) { return 10000; }
            else if (currstate == State.Draw) { return 0; }
            else { return -10000; }
        }
        if (maximizingPlayer){
            int value = -10000;
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    if (initBoard.placePiece(j,i)){
                        value = Math.max(value, minimax(j, i, false));
                        initBoard.undo(j,i);
                    }
                }
            }
            return value;
        }
        else {
            int value = 10000;
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    if (initBoard.placePiece(j,i)) {
                        value = Math.min(value, minimax(j, i, true));
                        initBoard.undo(j,i);
                    }
                }
            }
            return value;
        }
    }

    void debugDisplay() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                System.out.print(scores[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}
