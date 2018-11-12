package cell_automaton;
/*
 *@author Will Thompson
 * View for the GUI Project, CS257
 */

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View {
        public final static double CELL_WIDTH = 10.0;
        @FXML private int rowCount;
        @FXML private int columnCount;
        private Rectangle[][] gridView;

        public View(){
        }

        /*
         * This method will use the new data taken from update() and will construct a new grid
         */
        private void initializeGrid()
        {
                if (this.rowCount > 0 && this.columnCount > 0)
                this.gridView = new Rectangle[this.rowCount][this.columnCount];
                for (int row = 0; row < this.rowCount; row++)
                {
                        for (int column = 0; column < this.columnCount; column++)
                        {
                                Rectangle rectangle = new Rectangle();
                                rectangle.setX((double)column * CELL_WIDTH);
                                rectangle.setY((double)row * CELL_WIDTH);
                                rectangle.setWidth(CELL_WIDTH);
                                rectangle.setHeight(CELL_WIDTH);
                                this.gridView[row][column] = rectangle;
                        }
                }
        }

        /*
         * This method takes the data the user sees based on new data pulled from the model
         * @param model The GUI's model object is passed as a parameter
         */
        public void update(Model model)
        {
                for (int row = 0; row < this.rowCount; row++) {
                        for (int column = 0; column < this.columnCount; column++) {
                                Model.CellValue cellValue = model.getCellValue(row, column);
                                if (cellValue == Model.CellValue.EMPTY)
                                {
                                        this.gridView[row][column].setFill(Color.BLACK);
                                }
                                else
                                {
                                        this.gridView[row][column].setFill(Color.WHITE);
                                }

                        }
                }
        }
}

