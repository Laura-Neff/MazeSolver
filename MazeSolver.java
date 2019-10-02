package MazeSolver;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;

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

    int N = scanner.nextInt();
    scanner.nextLine();
    maze = new char[N][N];
    endX = N-1; endY = N-1;
    int i = 0;
    while(i < N && scanner.hasNext()) {
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

  // TODO: Solve the maze stored in the 2D-array "maze" object using a Stack.
  // If your algorithm finds a valid path out of the maze, print a success
  // message: "Maze is solvable." Otherwise, print: "Maze is NOT solvable."
  // Mark the valid positions you visited during your maze walk with an 'X' character.
  public void solveMaze() {
    // Your code here...
  }

  public static void main(String[] args) throws IOException {
    // If no argument is provided, show error message
    if(args.length < 1) {
      System.err.println("[ERROR] Usage: java PathFinder maze_file");
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
