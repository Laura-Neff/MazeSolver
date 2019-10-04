/*
THIS CODE WAS OUR OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Laura Neff
 */

import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Stack;

public class MazeSolver {

  static char[][] maze;
  static int startX, startY;  // indices for starting the maze search
  static int endX, endY; // indices for ending the maze search

  // Constructor that creates the maze
  public MazeSolver(String fileName) throws IOException {
    startX = 0;
    startY = 0;
    readMaze(fileName); // initialize maze
  }

  // Helper method for reading the maze content from a file
  public static void readMaze(String filename) throws IOException {
    Scanner scanner;
    try{
      scanner = new Scanner(new FileInputStream(filename));
    }
    catch(IOException ex){
      System.err.println("[ERROR] Invalid filename: " + filename);
      return;
    }

    int N = scanner.nextInt(); //first line of maze are its dimensions
    scanner.nextLine();
    maze = new char[N][N]; //reads the maze in as a char array
    endX = N-1; endY = N-1; //defines when we've reached the end of the maze
    int i = 0;
    while(i < N && scanner.hasNext()) { //read the maze
      String line =  scanner.nextLine();
      String [] tokens = line.split("\\s+");
      int j = 0;
      for (; j< tokens.length; j++){
        maze[i][j] = tokens[j].charAt(0);
      }
      if(j != N){
        System.err.println("[ERROR] Invalid line: " + i + " has wrong # columns: " + j);
        return;
      }
      i++;
    }
    if(i != N){
      System.err.println("[ERROR] Invalid file: has wrong number of rows: " + i);
      return;
    }
  }

  // Helper method for printing the maze in a matrix format
  public void printMaze() {
     for (int i=0; i < maze.length; i++) {
         for (int j=0; j < maze.length; j++) {
           System.out.print(maze[i][j]);
           System.out.print(' ');
          }
          System.out.println();
     }
  }

  public static void moveIfValid(Stack<MazePosition> a, int i, int j){
    if(maze[i][j]=='0'){
      a.push(new MazePosition(i,j));
    }
    return;
  }

  // TODO: Solve the maze stored in the 2D-array "maze" object using a Stack.
  // If your algorithm finds a valid path out of the maze, print a success
  // message: "Maze is solvable." Otherwise, print: "Maze is NOT solvable."
  // Mark the valid positions you visited during your maze walk with an 'X' character.
  public void solveMaze() {
    // Your code here...
    Stack<MazePosition> a = new Stack<>();
    int i = 0;
    int j = 0;
    MazePosition initial = new MazePosition(i,j);
    a.push(initial); //add entrance position to the search

    while(!a.empty()){ //while the stack is not empty, do:
      initial = a.peek(); //locate next search position
      i = initial.getX();
      j = initial.getY();
      a.pop(); //remove the next position from the search list
      maze[i][j] = 'X'; //mark current position as visited (no backtracking)
      if((i==endX) && (j==endY)){ //if it is the exit position then
        System.out.println("Maze is solvable"); //an escape path was found
        return;
      } else {
        if(i>0) { //check left
          moveIfValid(a,i-1,j);
        }
        if(i<endX){ //check right
          moveIfValid(a,i+1,j);
        }
        if(j>0) { //check up
          moveIfValid(a, i, j-1);
        }
        if(j<endY){ //check down
          moveIfValid(a,i,j+1);
        }
      }
    }
    System.out.println("Maze is NOT solvable"); //if we exited out of the while loop because the stack was empty
    return;
  }

  public static void main(String[] args) throws IOException {
    // If no argument is provided, show error message
    if(args.length < 1) {
      System.err.println("[ERROR] Usage: java MazeSolver maze_file");
      System.exit(-1);
    }
    // File name is provided properly as the first argument
    String fileName =  args[0];

    MazeSolver ms = new MazeSolver(fileName);
    System.out.println("[Before Traversal] Maze:");
    ms.printMaze();
    System.out.println();

    // Test solver
    ms.solveMaze();
    System.out.println();
    System.out.println("[After Traversal] Maze:");
    ms.printMaze();
  }

}
