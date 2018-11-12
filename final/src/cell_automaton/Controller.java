
package cell_automaton;
/*
 @author Will Thompson"
 Controller for GUI Project, CS257
*/

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
]import javafx.application.Platform;
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
    @FXML private Button Run_Simulation;
    @FXML private Button Reset;
    @FXML private Button Pause;
    @FXML private int rowCount;
    @FXML private int columnCount;
    @FXML private View view;

    private Model model;
    private boolean paused;
    private Timer timer;



    public Controller(){
    }

    /*
     *This method builds a timer to animate each successive generation
     */
    private void startTimer()
    {

    }

    /*
     * This method updates the data for the grid based on user input
     */
    private void update()
    {

    }

    /*
     * This method will handle the application's behavior when the Pause Button is clicked
     * @param actionevent The event when Pause button is clicked
     */
    public void handlePauseButton (ActionEvent actionevent)
    {

    }

    /*
     * This method will handle the application's behavior when the Play Button is clicked
     * @param actionevent The event when Play button is clicked
     */
    public void handlePlayButton (ActionEvent actionevent)
    {

    }

    /*
     * This method will handle the application's behavior when one cell is clicked on and activated
     * @param actionevent The event when a cell is clicked
     */
    public void handleCellSelection (ActionEvent actionevent)
    {

    }

    /*
     * This method will handle the application's behavior when the Reset Button is clicked
     * @param actionevent The event when Reset button is clicked
     */
    public void handleResetButton (ActionEvent actionevent)
    {

    }

    /*
     * This method will handle the application's behavior when the Next Generation Button is clicked
     * @param actionevent The event Next Generation button is clicked
     */
    public void handleNextGenerationButton (ActionEvent actionevent)
    {

    }
}