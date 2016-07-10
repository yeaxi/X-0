package ua.groupvertex.playground;

public class SimplePlayGround implements Playground {
    private Board board;
    private Seed currentPlayer = Seed.CROSS;

    public void start() {
        board = new Board();
        currentPlayer = Seed.CROSS;
    }

    public State doStep(int i, int j) {
        if (isFinished()) {
            return getWinner();
        }
        if (isMoveAllowed(i, j)) {
            updateState(i, j);
            if (isFinished()) {
                return getWinner();
            }

        } else {
            return State.INVALID_INPUT_DATA;
        }

        currentPlayer = (currentPlayer == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS);
        return State.PLAY;
    }

    private boolean isFinished() {
        return board.hasWon(Seed.CROSS) || (board.hasWon(Seed.NOUGHT) || board.isDraw());
    }

    private State getWinner() {
        if (board.isDraw()) {
            return State.DRAW;
        } else if (currentPlayer == Seed.CROSS) {
            return State.CROSS_WON;

        } else return State.NOUGHT_WON;
    }

    private boolean isMoveAllowed(int i, int j) {
        if (!(i <= 2 && i >= 0 && j <= 2 && j >= 0)) {
            return false;
        }
        if (board.cells[i][j].content != Seed.EMPTY) {
            return false;
        }
        return true;
    }

    private void updateState(int i, int j) {
        board.cells[i][j].content = currentPlayer;
        board.currentRow = i;
        board.currentCol = j;
    }

}
