/*
 @author Will Thompson"
 Controller for GUI Project, CS257
 This file takes user input, such as clicking, and communicates with the model to update the simulation data.
*/

package cell_automaton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;


public class Controller implements EventHandler<MouseEvent>{

    private double framePerSecond;
    @FXML private Button nextGenerationButton;
    @FXML private Button resetButton;
    @FXML private Button pauseButton;
    @FXML private Button fasterButton;
    @FXML private Button slowerButton;
    @FXML private Label generationLabel;
    @FXML private View view;
    private Model model;
    private boolean paused;
    private Timer timer;


    public Controller()
    {
        this.paused = true;
        this.framePerSecond = 15.0;
    }

    public void initialize()
    {
        this.model = new Model(this.view.getRowCount(), this.view.getColumnCount());
        this.nextGenerationButton.setOnAction(this::handleNextGenerationButton);
        this.pauseButton.setOnAction(this::handlePauseButton);
        this.resetButton.setOnAction(this::handleResetButton);
        this.fasterButton.setOnAction(this::handleFasterButton);
        this.slowerButton.setOnAction(this::handleSlowerButton);
        this.update();
    }

    private void update()
    {
        this.generationLabel.setText("Generation: " + model.getGeneration());
        this.view.update(this.model);
    }

    private void startTimer()
    {
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run()
                    {
                        model.createNextGeneration();
                        update();
                    }
                });
            }
        };
        long frameTimeInMilliseconds = (long)(1000.0 / this.framePerSecond);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    public void handlePauseButton (ActionEvent actionevent)
    {
        if (this.paused)
        {
            this.pauseButton.setText("Pause");
            this.startTimer();
        }
        else
        {
            this.pauseButton.setText(" Run ");
            this.timer.cancel();
        }
        this.paused = !this.paused;
        this.update();
    }

    public void handleResetButton (ActionEvent actionevent)
    {
        if (this.timer != null)
        {
            this.timer.cancel();
            this.paused = true;
            this.pauseButton.setText(" Run ");
        }
        this.model.clearGrid();
        this.update();
    }

    public void handleNextGenerationButton (ActionEvent actionevent)
    {
        if (this.timer != null)
        {
            this.timer.cancel();
            this.paused = true;
            this.pauseButton.setText(" Run ");
        }
        this.model.createNextGeneration();
        this.update();
    }

    public void handleFasterButton (ActionEvent actionevent)
    {
        if ((this.timer != null) && (!this.paused))
        {
            this.timer.cancel();
            this.framePerSecond = this.framePerSecond + 5.0;
            this.startTimer();
        }

    }

    public void handleSlowerButton (ActionEvent actionevent)
    {
        if ((this.timer != null) && (!this.paused))
        {
            this.timer.cancel();

            if (this.framePerSecond != 0.0)
            {
                this.framePerSecond = this.framePerSecond - 5.0;
            }
            this.startTimer();
        }
    }

    @Override
    public void handle(MouseEvent mouseEvent)
    {
        double mouseClickX = mouseEvent.getX() - 10.4;
        double mouseClickY = mouseEvent.getY() - 19.2;
        int rowClicked = (int)(mouseClickY / this.view.CELL_WIDTH);
        int columnClicked = (int)(mouseClickX / this.view.CELL_WIDTH);

        if (this.model.isValidCell(rowClicked, columnClicked))
        {
            if (this.model.getCellValue(rowClicked, columnClicked) == Model.CellValue.EMPTY)
            {
                this.model.setCelltoLive(rowClicked, columnClicked);
            }
            else if (this.model.getCellValue(rowClicked, columnClicked) == Model.CellValue.LIVE)
            {
                this.model.setCelltoEmpty(rowClicked, columnClicked);
            }
            this.update();
            mouseEvent.consume();
        }
    }

    public double getBoardWidth()
    {
        return view.CELL_WIDTH * this.view.getColumnCount();
    }

    public double getBoardHeight()
    {
        return view.CELL_WIDTH * this.view.getRowCount();
    }
}