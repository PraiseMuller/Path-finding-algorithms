/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import java.util.LinkedList;
import java.util.Random;
import javax.swing.JButton;

public class GenerateMaze {
    private LinkedList<Cell> unvisited;
    private int width;
    private int height;

    
    //THE ALDOUS-BRODER ALGORITHM FOR MAZE GENERATION.
    //MY SPAGHETTI IMPLEMENTAION.
    public GenerateMaze(Cell[][] graph, JButton[][] board) {
        this.unvisited = new LinkedList<>();
        this.height = graph.length;
        this.width = graph[0].length;

        for (Cell[] rowVar : graph) {
            for (int j = 0; j < graph[0].length; j++) {
                unvisited.add(rowVar[j]);
            }
        }

        Cell current = graph[this.height/2][this.width/2];
        Cell neighbor = null;
        current.setVisitedMC(true);
        this.unvisited.remove(current);

        while (!this.unvisited.isEmpty()) {
            //PICKING A RANDOM NEIGHBOR
            Random rand = new Random();
            int r = rand.nextInt(1, 5);

            switch (r) {
                case 1 -> {
                    //UP
                    if (isInBounds(current.getX() + 1, current.getY())) {
                        neighbor = graph[current.getX() + 1][current.getY()];
                    }
                }
                case 2 -> {
                    //DOWN
                    if (isInBounds(current.getX() - 1, current.getY())) {
                        neighbor = graph[current.getX() - 1][current.getY()];
                    }
                }
                case 3 -> {
                    //LEFT
                    if (isInBounds(current.getX(), current.getY() + 1)) {
                        neighbor = graph[current.getX()][current.getY() + 1];
                    }
                }
                case 4 -> {
                    //RIGHT
                    if (isInBounds(current.getX(), current.getY() - 1)) {
                        neighbor = graph[current.getX()][current.getY() - 1];
                    }
                }
            }

            if (neighbor != null) {
                //CHECK IF NEIGHBOR ISNT VISITED
                if (!neighbor.getVisitedMC()) {
                    //remove wall between current and neighbor
                    graph[current.getX()][current.getY()].setIsWall(false);

                    neighbor.setVisitedMC(true);
                    this.unvisited.remove(neighbor);
                }
                current = neighbor;
            }
        }

        //DRAW AFTER FINISHING
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {               
                //fillEdgeWalls
                if(i == 0 || i == this.height - 1 || j == 0 || j == this.width - 1)
                    graph[i][j].setIsWall(true);
                
                if(graph[i][j].isWall())
                    board[i][j].setBackground(Colors.wall());
                
            }
        }       
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < this.height && y >= 0 && y < this.width;
    }

}
