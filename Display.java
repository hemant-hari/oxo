class Display {
    private Occupied[][] displayArray = new Occupied[3][3];
    Board gBoard;

    Display(Board gBoard0) {
        gBoard = gBoard0;
        displayArray = gBoard.getCurrentBoard();
    }

    void updateDisplay() {
        displayArray = gBoard.getCurrentBoard();
        System.out.println("   ABC");
        for (int i=0; i<3; i++) {
            System.out.print((i+1) + "  ");
            for (int j=0; j<3; j++) {
                printPos(i, j);
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    private void printPos(int i, int j) {
        Occupied posVal = displayArray[i][j];
        if (posVal == Occupied.Blank){ System.out.print("."); }
        else { System.out.print(posVal); }
    }
}
