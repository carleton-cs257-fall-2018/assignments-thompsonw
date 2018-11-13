/*
 @author Will Thompson"
 Model for GUI Project, CS257
*/
package cell_automaton;

public class Model {


    /*
     *Creates two values for a cell to take on: alive or dead.
     */
    public enum CellValue
    {
        EMPTY, LIVE
    }

    private int generation;
    private boolean clear;
    private CellValue[][] grid;


    /*
     * The exact size of the grid may be specified before the simulation begins
     * @param rowCount Specifies the height of the grid
     * @param columnCount Specifies the width of the grid
     */
    public Model(int rowCount, int columnCount)
    {
        this.grid = new CellValue[rowCount][columnCount];
        this.clear = clear;
        this.generation = 1;
    }


    /*
     * This method scans the entire grid cell by cell, and according to the rules of the "Game of Life" set forth
     * by Conway, survives to the next generation or dies depending on the number of neighbors.
     */
    public void createNextGeneration()
    {
        int rowCount = this.grid.length;
        int columnCount = this.grid[0].length;
        this.generation++;

        for (int row = 0; row < rowCount; row++)
        {
            for (int column = 0; column < columnCount; column++)
            {
                this.grid[row][column] = this.determineFutureCellStatus(row,column);
            }
        }
    }


    /*
     * This is the method that checks one particular cell against the rules of the game and determines whether it lives or dies.
     * @param row Specifies the row the cell is in
     * @param column Specifies the column the cell is in.
     * @returns Returns the future status of the cell, which is either CellValue.LIVE or CellValue.EMPTY.
     */
    private CellValue determineFutureCellStatus (int row, int column)
    {
        int numNeighbors = this.getCellNeighbors(row, column);
        CellValue currentStatus = this.getCellValue(row,column);
        CellValue futureStatus = CellValue.EMPTY;

        if (currentStatus == CellValue.EMPTY)
        {
            if (numNeighbors == 3 )
            {
                futureStatus = CellValue.LIVE;
            }
        }
        if (numNeighbors == 3 || numNeighbors == 2)
        {
            futureStatus = CellValue.LIVE;
        }

        return futureStatus;
    }


    /*
     * Clears the grid of all active cells. Will be called in the event a user wishes to reset the simulation and
     * start over.
     */
    public void clearGrid()
    {
        int rowCount = this.grid.length;
        int columnCount = this.grid[0].length;

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                this.grid[row][column] = CellValue.EMPTY;
            }
        }
    }


    /*
     * The "Game of Life" rules are centered around the neighboring cells of any given cell. This method checks
     * the eight neighbors of a cell and counts the neighboring cells.
     * @param row Row that the cell is is
     * @param column Column that the cell is in
     * @returns Returns the number of active cells surrounding a given cell.
     */
    private int getCellNeighbors (int row, int column)
    {
        int numNeighbors = 0;

        if (getCellValue(row-1, column-1) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        if (getCellValue(row, column-1) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        if (getCellValue(row+1, column-1) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        if (getCellValue(row+1, column) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        if (getCellValue(row+1, column+1) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        if (getCellValue(row, column+1) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        if (getCellValue(row-1, column+1) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        if (getCellValue(row-1, column) != CellValue.EMPTY)
        {
            numNeighbors++;
        }

        return numNeighbors;
    }

    /*
     * Get method for the most recent generation
     * @ returns Returns the integer value of the generation
     */
    public int getGeneration()
    {
        return this.generation;
    }

    /*
     * Get method for the status of a cell
     * @param row Row of the cell
     * @param Column of the cell
     * @returns Returns the value for the cell at the specified coordinate points.
     */
    public CellValue getCellValue(int row, int column)
    {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }

    public void setCelltoLive(int row, int column)
    {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        this.grid[row][column] = CellValue.LIVE;
    }

    public void setCelltoEmpty(int row, int column)
    {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        this.grid[row][column] = CellValue.EMPTY;
    }


}
