Will Thompson
Phase 2 - Project Overview



                                        Project Overview:

      Currently, the program runs well. The buttons work without any crashes or exceptions being thrown, cells become highlighted 
when clicked on, and the simulation runs smoothly without any noticable bugs. The generation label updates properly to display the 
current interation of the simulation. However, the simulation speed does not currently update when the program is paused. Additionally, I decided to use the space on the bottom to display the generation of the program instead of building a new panel on the side of the screen.




                                        Project Description:

	This project will build a graphical user interface for displaying the Game of Life. 
There will be an interactive window displaying a grid. The user may click on a cell to activate it, and once the user presses 
the button, Next Generation, the game will update and display the next generation of cells based on the rules set forth by Conway. 

	The user also has the option to click the Botton, Run Simulation, which will automate the process and update the grid on a 
timer, which will result in an animated display of each
successive generation. Once the user is finished, there is an option to pause the animation, or reset the grid and start over. 
 




                             Why this project is conducive to a MVC pattern:

	The behavior of this GUI can be easily separated into a model, a view, and controller. The core information and data needed to run the simulation can stored in the Model with classes such as:

-createNextGeneration'
-determineCellStatus()--> this method determines whether a cell survives to the next generation
-getCellValue
-getCellNeighbors
-clearGrid

These fundemental classes code in the necessary information the simulation needs to run properly. When paired with a controller and a view, 
this information can be sent to the user and updated.

	The View of this GUI will contain only an initialize() and update() method. Together, they read in the updated information from the Model, 
and build the display using that data. The actual display of the GUI will be a small window with a grid dislaying each cell. White cells will be 
empty and black cells will be live cells. Four buttons at the bottom will allow the user to run the simulation, pause the simulation, reset the 
simulation, and go forward one generation. Additionally, there will be a small chart to the side displaying relevant information about the simulation, 
such as the current generation and the number of live cells.

	The behavior of the simulation, the need for the program to respond to user input, and 
the need to display the simulation's data naturally lends itself to the MVC pattern because each of these three criteria can be handled well by a model, a controller, and a view repsectively.  



  



 








