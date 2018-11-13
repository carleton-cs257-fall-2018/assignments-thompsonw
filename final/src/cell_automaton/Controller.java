/*
 @author Will Thompson"
 Controller for GUI Project, CS257
*/

package cell_automaton;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent> {

    final private double FRAMES_PER_SECOND = 30.0;
    @FXML private Button NextGeneration;
    @FXML private Button Reset;
    @FXML private Button Pause;
    @FXML private int rowCount;
    @FXML private int columnCount;
    @FXML private View view;
    private Model model;
    private boolean paused;
    private Timer timer;



    public Controller()
    {
        this.model = model;
        this.view = view;
        Button NextGeneration = new Button("Next Generation");
        Button Run_Simulation = new Button("Run Simulation");
        Button Reset = new Button("Reset");
        Button Pause = new Button("Pause");
    }

    /*
     *This method builds a timer to animate each successive generation
     */
    private void startTimer()
    {
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run()
                    {
                        model.createNextGeneration();
                        view.update(model);
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /*
     * This method updates the data for the grid based on user input
     */
    private void update()
    {
        this.view.update(this.model);
    }

    @Override
    public void handle(KeyEvent keyEvent)
    {
        this.NextGeneration.setOnAction(this::handleNextGenerationButton);
        this.Pause.setOnAction(this::handlePauseButton);
        this.Reset.setOnAction(this::handleResetButton);
    }


    /*
     * This method will handle the application's behavior when the Pause Button is clicked
     * @param actionevent The event when Pause button is clicked
     */
    public void handlePauseButton (ActionEvent actionevent)
    {
        if (this.paused)
        {
            this.Pause.setText("Pause");
            this.startTimer();
        }
        else
        {
            this.Pause.setText("Continue");
            this.timer.cancel();
        }
        this.paused = !this.paused;
    }

    /*
     * This method will handle the application's behavior when one cell is clicked on and activated
     * @param actionevent The event when a cell is clicked
     */
    public void handleCellSelection (ActionEvent actionevent, int row, int column)
    {
        if (this.model.getCellValue(row, column) == Model.CellValue.EMPTY)
        {
            this.model.setCelltoLive(row, column);
        }
        else
        {
            this.model.setCelltoEmpty(row, column);
        }
    }

    /*
     * This method will handle the application's behavior when the Reset Button is clicked
     * @param actionevent The event when Reset button is clicked
     */
    public void handleResetButton (ActionEvent actionevent)
    {
        this.model.clearGrid();
    }

    /*
     * This method will handle the application's behavior when the Next Generation Button is clicked
     * @param actionevent The event Next Generation button is clicked
     */
    public void handleNextGenerationButton (ActionEvent actionevent)
    {
        this.paused = true;
        this.model.createNextGeneration();
    }


}