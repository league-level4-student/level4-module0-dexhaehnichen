package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		/*int rCellX = randGen.nextInt();
		int rCellY = randGen.nextInt();*/
		
		Cell rCell = maze.cells[randGen.nextInt()][randGen.nextInt()];
		
		//5. call selectNextPath method with the randomly selected cell
		
		selectNextPath(rCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.hasBeenVisited();
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unvisitedNeighbors.size() != 0) {
			//C1. select one at random.
			Cell randomCell = unvisitedNeighbors.get(randGen.nextInt(unvisitedNeighbors.size()));
			//C2. push it to the stack
			uncheckedCells.push(randomCell);
			//C3. remove the wall between the two cells
			removeWalls(currentCell,randomCell);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = randomCell;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}	
		//D. if all neighbors are visited
		else {	
			//D1. if the stack is not empty
			if(uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell
				
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}	
		}	
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c2.getX() == c1.getX()+1) {
			c1.setEastWall(false);
		}
		if(c2.getX() == c1.getX()-1) {
			c1.setWestWall(false);
		}
		if(c2.getY() == c1.getY()+1) {
			c1.setSouthWall(false);
		}
		if(c2.getY() == c1.getY()-1) {
			c1.setNorthWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
	ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		if(c.getX() == 0 && c.getY() == 0) {
			if(maze.cells[c.getX()+1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
			if(maze.cells[c.getX()+1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()+1]);
			}
			if(maze.cells[c.getX()][c.getY()+1].hasBeenVisited() == false) {
				 unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);
			}
		}
		//upper right
		if(c.getX() == width && c.getY() == 0) {
			if(maze.cells[c.getX()-1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
			if(maze.cells[c.getX()-1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()+1]);
			}
			if(maze.cells[c.getX()][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);
			}
		}
		//lower left
		if(c.getX() == 0 && c.getY() == height) {
			if(maze.cells[c.getX()][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
			if(maze.cells[c.getX()+1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()-1]);
			}
			if(maze.cells[c.getX()+1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
		}
		//lower right
		if(c.getX() == width && c.getY() == height) {
			if(maze.cells[c.getX()-1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
			if(maze.cells[c.getX()-1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()-1]);
			}
			if(maze.cells[c.getX()][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
		}	
	/*  _____ _______ _________
		|353| |35553| |3555553|
		|585| |58885| |5888885|
		|353| |58885| |5888885|
		----- |58885| |5888885|
			  |35553| |5888885|
			  ------- |5888885|
					  |3555553|
					  ---------
	*/
	
	//edges 
		//top
		if(c.getX() > 0 && c.getX() < width && c.getY() == 0) {
			if(maze.cells[c.getX()-1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
			if(maze.cells[c.getX()-1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()+1]);
			}
			if(maze.cells[c.getX()][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);				
			}										
			if(maze.cells[c.getX()+1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
			if(maze.cells[c.getX()+1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()+1]);
			}
		}
		//bottom
		if(c.getX() > 0 && c.getX() < width && c.getY() == height) {
			if(maze.cells[c.getX()-1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
			if(maze.cells[c.getX()-1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()-1]);
			}
			if(maze.cells[c.getX()][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
			if(maze.cells[c.getX()+1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
			if(maze.cells[c.getX()+1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()-1]);
			}
		}
		//left
		if(c.getY() > 0 && c.getY() < height && c.getX() == 0) {
			if(maze.cells[c.getX()][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);
			}
			if(maze.cells[c.getX()+1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()+1]);
			}
			if(maze.cells[c.getX()+1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
			if(maze.cells[c.getX()+1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()-1]);
			}
			if(maze.cells[c.getX()][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
		}
		//right
		if(c.getY() > 0 && c.getY() < height && c.getX() == width) {
			if(maze.cells[c.getX()][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);
			}
			if(maze.cells[c.getX()-1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()+1]);
			}
			if(maze.cells[c.getX()-1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
			if(maze.cells[c.getX()-1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()-1]);
			}
			if(maze.cells[c.getX()][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
		}
	/*  _____ _______ _________
		|353| |35553| |3555553|
		|585| |58885| |5888885|
		|353| |58885| |5888885|
		----- |58885| |5888885|
			  |35553| |5888885|
			  ------- |5888885|
					  |3555553|
					  ---------
	*/
		//middle
		if(c.getX() > 0 && c.getX() < width && c.getY() > 0 && c.getY() < height) {
			if(maze.cells[c.getX()-1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()-1]);
			}
			if(maze.cells[c.getX()][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()-1]);
			}
			if(maze.cells[c.getX()+1][c.getY()-1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()-1]);
			}
			//
			if(maze.cells[c.getX()-1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()]);
			}
			if(maze.cells[c.getX()+1][c.getY()].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()]);
			}
			//
			if(maze.cells[c.getX()-1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()-1][c.getY()+1]);
			}
			if(maze.cells[c.getX()][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()][c.getY()+1]);
			}
			if(maze.cells[c.getX()+1][c.getY()+1].hasBeenVisited() == false) {
				unvisitedNeighbors.add(maze.cells[c.getX()+1][c.getY()+1]);
			}
		}
		return unvisitedNeighbors;
	}	
}
