package hu.supercluster.gameoflife.game.rule;

import hu.supercluster.gameoflife.game.cell.Cell;
import hu.supercluster.gameoflife.game.cell.SimpleCell;
import hu.supercluster.gameoflife.game.grid.Grid;

public class ConwaysRule extends AbstractRule<SimpleCell> {
    @Override
    public int apply(Grid<SimpleCell> grid, int x, int y) {
        SimpleCell current = current(grid, x, y);
        int n = current.getNeighborCount();

        if (current(grid, x, y).isAlive()) {
            return staysAlive(n);

        } else {
            return becomesAlive(n);
        }

    }

    private int staysAlive(int n) {
        if (diesOfLoneliness(n) || diesOfOverCrowding(n)) {
            return Cell.STATE_DEAD;

        } else {
            return Cell.STATE_ALIVE;
        }
    }

    private boolean diesOfLoneliness(int n) {
        return n < 2;
    }

    private boolean diesOfOverCrowding(int n) {
        return n > 3;
    }

    private int becomesAlive(int n) {
        if (getsBorn(n)) {
            return Cell.STATE_ALIVE;

        } else {
            return Cell.STATE_DEAD;
        }
    }

    private boolean getsBorn(int n) {
        return n == 3;
    }
}