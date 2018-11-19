/*
 @author Will Thompson"
 Model for GUI Project, CS257
 Contains the necessary data and functionality to run a Game of Life simulation.
*/
package cell_automaton;

public class Model {

    public enum CellValue
    {
        EMPTY, LIVE
    }
    private int generation;
    private CellValue[][] grid;

    public Model(int rowCount, int columnCount)
    {
        assert rowCount > 0 && columnCount > 0;
        this.grid = new CellValue[rowCount][columnCount];
        this.generation = 0;
        this.clearGrid();
    }

    public void createNextGeneration()
    {
        int rowCount = this.grid.length;
        int columnCount = this.grid[0].length;
        this.generation++;

        // Changing a cell status directly in this.grid is dangerous because a cells' neighbors suddenly loose or gain an neighbor that should not exist.
        // A new, separate grid must be updated to what the next generation should be, and the old grid is set equal to the new grid.
        CellValue[][] nextGenerationGrid = new CellValue[rowCount][columnCount];

        for (int row = 0; row < rowCount; row++)
        {
            for (int column = 0; column < columnCount; column++)
            {
                if (isValidCell(row, column))
                {
                    // Update the temporary grid cells
                    nextGenerationGrid[row][column] = this.determineFutureCellStatus(row, column);
                }
            }
        }
        this.grid = nextGenerationGrid;
    }

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
        if (currentStatus == CellValue.LIVE)
        {
            if ((numNeighbors == 3) || (numNeighbors == 2))
            {
                futureStatus = CellValue.LIVE;
            }
        }
        return futureStatus;
    }

    private int getCellNeighbors (int row, int column)
    {
        int numNeighbors = 0;

        if (this.isValidCell(row-1, column-1))
        {
            if (this.getCellValue(row - 1, column - 1) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        if (this.isValidCell(row, column-1))
        {
            if (this.getCellValue(row , column - 1) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        if (this.isValidCell(row+1, column-1))
        {
            if (this.getCellValue(row+1 , column - 1) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        if (this.isValidCell(row+1, column))
        {
            if (this.getCellValue(row +1 , column) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        if (this.isValidCell(row+1, column+1))
        {
            if (this.getCellValue(row+1 , column+1) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        if (this.isValidCell(row, column+1))
        {
            if (this.getCellValue(row , column+1) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        if (this.isValidCell(row-1, column+1))
        {
            if (this.getCellValue(row-1 , column+1) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        if (this.isValidCell(row-1, column))
        {
            if (this.getCellValue(row-1 , column) == CellValue.LIVE)
            {
                numNeighbors++;
            }
        }
        return numNeighbors;
    }

    public int getGeneration()
    {
        return this.generation;
    }


    public void clearGrid()
    {
        int rowCount = this.grid.length;
        int columnCount = this.grid[0].length;

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                this.grid[row][column] = CellValue.EMPTY;
            }
        }
        this.generation = 0;
    }

    public CellValue getCellValue(int row, int column)
    {
        return this.grid[row][column];
    }

    public void setCelltoLive(int row, int column)
    {
        if (this.isValidCell(row, column))
        {
            this.grid[row][column] = CellValue.LIVE;
        }
    }

    public void setCelltoEmpty(int row, int column)
    {
        if (this.isValidCell(row, column))
        {
            this.grid[row][column] = CellValue.EMPTY;
        }
    }

    /*
     * @param row Row coordinate for a cell
     * @param column Column coordinate for a cell.
     * @returns True or False if the specified coordinates of a cell produced a valid cell.
     */
    public boolean isValidCell(int row, int column)
    {
        int rowCount = this.grid.length;
        int columnCount = this.grid[0].length;

        if ((row >= 0)&&(row < rowCount))
        {
            if ((column >= 0) && (column < columnCount))
            {
                return true;
            }
        }
        return false;
    }
}