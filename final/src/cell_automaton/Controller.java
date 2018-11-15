/*
 @author Will Thompson"
 Controller for GUI Project, CS257
*/

package cell_automaton;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    final private double FRAMES_PER_SECOND = 30.0;
    @FXML private Button nextGenerationButton;
    @FXML private Button resetButton;
    @FXML private Button pauseButton;
    @FXML private View view;
    private Model model;
    private boolean paused;
    private Timer timer;


    public Controller()
    {
        this.model = model;
        this.view = view;
    }

    public void initialize()
    {
        this.model = new Model(this.view.getRowCount(), this.view.getColumnCount());
        this.nextGenerationButton.setOnAction(this::handleNextGenerationButton);
        this.pauseButton.setOnAction(this::handlePauseButton);
        this.resetButton.setOnAction(this::handleResetButton);
        this.update();
    }

    private void update()
    {
        this.view.update(this.model);
    }

    public double getBoardWidth() {
        return view.CELL_WIDTH * this.view.getColumnCount();
    }

    public double getBoardHeight() {
        return view.CELL_WIDTH * this.view.getRowCount();
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
     * This method will handle the application's behavior when the Pause Button is clicked
     * @param actionevent The event when Pause button is clicked
     */
    public void handlePauseButton (ActionEvent actionevent)
    {
        if (this.paused)
        {
            this.pauseButton.setText("Pause");
            this.startTimer();
        }
        else
        {
            this.pauseButton.setText("Continue");
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