/* Tic-Tac-Toe Playing AI made using a minimax algorithm */


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
                if (initBoard.getPosition(j, i) != Occupied.Blank) scores[i][j] = -10000000;
            }
        }
    }

    int[] makeMove() {
        int[] pos = {-1,-1};
        int x = -1, y = -1, max = -100;
        eval();
        for (int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                x = (scores[i][j] > max) ? j : x;
                y = (scores[i][j] > max) ? i : y;
                max = Math.max(max, scores[i][j]);
            }
        }
        pos[0] = x;
        pos[1] = y;
        return pos;
    }

    void eval() {
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (initBoard.getPosition(j, i) != Occupied.Blank) scores[i][j] = -10000000;
            }
        }
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (initBoard.getPosition(j, i) == Occupied.Blank) {
                    initBoard.placePiece(j,i);
                    scores[i][j] = minimax(j,i,false);
                    initBoard.undo(j,i);
                }
            }
        }
    }

    int minimax(int x, int y, boolean maximizingPlayer) {
        State currstate = initBoard.updateGetState();
        if (currstate != State.Running) {
            if (currstate == AIWinState) { return 1; }
            else if (currstate == State.Draw) { return 0; }
            else { return -1; }
        }
        if (maximizingPlayer){
            int value = -1;
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    if (initBoard.getPosition(j, i) == Occupied.Blank){
                        initBoard.placePiece(j,i);
                        value = Math.max(value, minimax(j, i, false));
                        initBoard.undo(j,i);
                    }
                }
            }
            return value;
        }
        else {
            int value = 1;
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    if (initBoard.getPosition(j, i) == Occupied.Blank){
                        initBoard.placePiece(j,i);
                        value = Math.min(value, minimax(j, i, true));
                        initBoard.undo(j,i);
                    }
                }
            }
            return value;
        }
    }

    public static void main(String[] args){
        Ai prog =  new Ai(new Board(Occupied.O), State.OWin);
        prog.run();
    }

    private void run() {
        System.out.println(makeMove()[0] + " " + makeMove()[1]);
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (initBoard.getPosition(j, i) != Occupied.Blank) scores[i][j] = -10000000;
            }
        }
        Display output = new Display(initBoard);
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (initBoard.getPosition(j, i) == Occupied.Blank) {
                    initBoard.placePiece(j,i);
                    scores[i][j] = minimax(j,i,false);
                    initBoard.undo(j,i);
                }
            }
        }

        debugDisplay();
    }

    private void debugDisplay() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                System.out.print(scores[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}
