/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package musvo;

import java.text.DecimalFormat;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JLabel;
import misc.*;

public class DFS {

    private final Cell startNode;
    private final Cell endNode;
    
    private final int height;
    private final int width;
    private int totalWalls;
    
    private Stack<Cell> stack;
    
    private boolean pathFound;

    public DFS(Cell startNode, Cell endNode, int w, int h) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.height = h;
        this.width = w;
        this.totalWalls = 0;
        this.stack = new Stack<>();
        this.pathFound = false;
    }

    public void solve(Cell[][] graph, JButton[][] maze, JLabel labelPathFound, JLabel labelPathLength, JLabel labelcheckedPercentage, JLabel labelcheckedNumber, boolean diagonalsOn) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (graph[i][j].isWall()) {
                    this.totalWalls++;
                }
            }
        }
        
        //BEGINNING OF ALGORITHM
        //DFS
        int row;
        int col;
        int checkedNodesNum = 0;
        int path_length = 0;
        Cell v = this.startNode;
        this.stack.push(v);
        
        while(!this.stack.isEmpty()){
            v = this.stack.pop();
            row = v.getX();
            col = v.getY(); 
            
            if(v == this.endNode){
                this.pathFound = true;
                System.out.println("DONE!");
                break;
            }
            
            if(!v.getVisited()){
                v.setVisited(true);       
                //FOR ALL EDGES OF V..
                //UP
                if (isInBounds(row, col + 1) && !graph[row][col + 1].isWall() ) {
                    Cell temp = graph[row][col + 1];
                    temp.setPrev(v);
                    this.stack.push(temp);
                }
                //DOWN
                if (isInBounds(row, col - 1) && !graph[row][col - 1].isWall() ) {
                    Cell temp = graph[row][col - 1];
                    temp.setPrev(v);
                    this.stack.push(temp);
                }
                //RIGHT
                if (isInBounds(row + 1, col) && !graph[row + 1][col].isWall() ) {
                    Cell temp = graph[row + 1][col];
                    temp.setPrev(v);
                    this.stack.push(temp);
                }
                //LEFT
                if (isInBounds(row - 1, col) && !graph[row - 1][col].isWall() ) {
                    Cell temp = graph[row - 1][col];
                    temp.setPrev(v);
                    this.stack.push(temp);
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
            Cell c = this.endNode;
//            while(c != null){  
//                path_length++;
//                if(graph[c.getX()][c.getY()] != this.startNode)
//                    maze[c.getX()][c.getY()].setBackground(Colors.path()); 
//                
//                System.out.println("PrevX: "+ c.getX() + "     PrevY: "+ c.getY());
//                c = c.getPrev();
//            }
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
