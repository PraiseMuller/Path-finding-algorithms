/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package musvo;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JLabel;
import misc.*;

public class BFS {
    private final Cell startNode; 
    private final Cell endNode;
    
    private final int width;
    private final int height;
    private int totalWalls;
    
    private final Queue queue;
    
    private boolean pathFound;

    public BFS(Cell startNode, Cell endNode, int w, int h) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.width = w;
        this.height = h;
        this.queue = new LinkedList<Cell>();
        this.pathFound = false;
        this.totalWalls = 0;
    }

    public void solve(Cell[][] graph, JButton[][] maze, JLabel labelPathFound, JLabel labelPathLength, JLabel labelcheckedPercentage, JLabel labelcheckedNumber, boolean diagonalsOn) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (graph[i][j].isWall()) {
                    this.totalWalls++;
                }
            }
        }
                
        //START OF ALGO..
        Cell v = graph[this.startNode.getX()][this.startNode.getY()];
        v.setVisited(true);
        this.queue.offer(v);
        
        int row;
        int col;
        int checkedNodesNum = 0;
        int path_length = 0;
        
        while(!this.queue.isEmpty()){
            v = (Cell) this.queue.poll();
            row = v.getX();
            col = v.getY();
            
            if(v == this.endNode){
                this.pathFound = true;
                System.out.println("DONE!");
                break;
            }
            
            //ALL EDGES OF V
            //UP
            if (isInBounds(row, col + 1) && !graph[row][col + 1].isWall() ) {
                Cell temp = graph[row][col + 1];
                if(!temp.getVisited()){
                    temp.setVisited(true);
                    temp.setPrev(v);
                    this.queue.offer(temp); 
                }
            }
            //DOWN
            if (isInBounds(row, col - 1) && !graph[row][col - 1].isWall() ) {
                Cell temp = graph[row][col - 1];
                if(!temp.getVisited()){
                    temp.setVisited(true);
                    temp.setPrev(v);
                    this.queue.offer(temp);
                }
            }
            //RIGHT
            if (isInBounds(row + 1, col) && !graph[row + 1][col].isWall() ) {
                Cell temp = graph[row + 1][col];
                if(!temp.getVisited()){
                    temp.setVisited(true);
                    temp.setPrev(v);
                    this.queue.offer(temp);
                }
            }
            //LEFT
            if (isInBounds(row - 1, col) && !graph[row - 1][col].isWall() ) {
                Cell temp = graph[row - 1][col];
                if(!temp.getVisited()){
                    temp.setVisited(true);
                    temp.setPrev(v);
                    this.queue.offer(temp);
                }
            }
            
            if(diagonalsOn){
                //UP LEFT
                if (isInBounds(row + 1, col + 1) && !graph[row + 1][col + 1].isWall() ) {
                    Cell temp = graph[row + 1][col + 1];
                    if(!temp.getVisited()){
                        temp.setVisited(true);
                        temp.setPrev(v);
                        this.queue.offer(temp);
                    }
                }
                //UP RIGHT
                if (isInBounds(row - 1, col - 1) && !graph[row - 1][col - 1].isWall() ) {
                    Cell temp = graph[row - 1][col - 1];
                    if(!temp.getVisited()){
                        temp.setVisited(true);
                        temp.setPrev(v);
                        this.queue.offer(temp);
                    }
                }
                //DOWN LEFT
                if (isInBounds(row + 1, col + 1) && !graph[row + 1][col + 1].isWall() ) {
                    Cell temp = graph[row + 1][col + 1];
                    if(!temp.getVisited()){
                        temp.setVisited(true);
                        temp.setPrev(v);
                        this.queue.offer(temp);
                    }
                }
                //DOWN RIGHT
                if (isInBounds(row - 1, col - 1) && !graph[row - 1][col - 1].isWall() ) {
                    Cell temp = graph[row - 1][col - 1];
                    if(!temp.getVisited()){
                        temp.setVisited(true);
                        temp.setPrev(v);
                        this.queue.offer(temp);
                    }
                }
            }
            
            if (graph[row][col] != this.startNode
                    && graph[row][col] != this.endNode
                    && !graph[row][col].isWall()) {
                maze[row][col].setBackground(Colors.visited());
                maze[row][col].setBorder(null);
                checkedNodesNum++;
            }
        } 
        
        //SET LABELS..PATH FOUND OR NAH
        if(this.pathFound){
            while(!v.equals(this.startNode)){  
                if(v.getPrev() != null){
                    v = v.getPrev();
                    path_length++;
                    if(graph[v.getX()][v.getY()] != this.startNode)
                        maze[v.getX()][v.getY()].setBackground(Colors.path()); 
                }
            }
            labelPathFound.setText("TRUE");
            labelPathLength.setText(path_length + " Nodes");
        }
        else{
            labelPathFound.setText("NO PATH FOUND");
            System.out.println("FAILED");
        }
        
        DecimalFormat df = new DecimalFormat("0.00");
        double temp_d = (double)checkedNodesNum / (this.width*this.height-this.totalWalls) * 100;
        
        labelcheckedPercentage.setText(""+ df.format(temp_d)  + " % of the Nodes");
        labelcheckedNumber.setText("Checked " +checkedNodesNum + " / "+ (this.width*this.height-totalWalls) + " Nodes");
        
    }
    
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.height && col >= 0 && col < this.width;
    }
    
    
}
