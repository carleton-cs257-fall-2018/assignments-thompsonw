/*
 *@author Will Thompson
 * View for the GUI Project, CS257
 */
package cell_automaton;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class View extends Group{

        public final static double CELL_WIDTH = 20.0;
        @FXML private int rowCount;
        @FXML private int columnCount;
        public Rectangle[][] gridView;

        public View(){
        }

        public int getRowCount()
        {
            return this.rowCount;
        }

        public void setRowCount(int rowCount) {
            this.rowCount = rowCount;
            this.initializeGrid();
        }

        public int getColumnCount()
        {
            return this.columnCount;
        }

        public void setColumnCount(int columnCount)
        {
            this.columnCount = columnCount;
            this.initializeGrid();
        }

        private void initializeGrid()
        {
            if (this.rowCount > 0 && this.columnCount > 0)
            {
                this.gridView = new Rectangle[this.rowCount][this.columnCount];
                for (int row = 0; row < this.rowCount; row++)
                {
                    for (int column = 0; column < this.columnCount; column++)
                    {
                        Rectangle rectangle = new Rectangle();
                        rectangle.setX((double) column * CELL_WIDTH);
                        rectangle.setY((double) row * CELL_WIDTH);
                        rectangle.setWidth(CELL_WIDTH);
                        rectangle.setHeight(CELL_WIDTH);
                        rectangle.setStroke(Color.BLACK);
                        this.gridView[row][column] = rectangle;
                        this.getChildren().add(this.gridView[row][column]);
                    }
                }
            }
        }

        public void update(Model model)
        {
            for (int row = 0; row < this.rowCount; row++)
            {
                for (int column = 0; column < this.columnCount; column++)
                {
                    Model.CellValue cellValue = model.getCellValue(row, column);
                    if (cellValue == Model.CellValue.EMPTY)
                    {
                        this.gridView[row][column].setFill(Color.WHITE);
                    }
                    if (cellValue == Model.CellValue.LIVE)
                    {
                        this.gridView[row][column].setFill(Color.GOLD);
                    }
                }
            }
        }
}

