/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package musvo;

import java.text.DecimalFormat;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import misc.*;

public class Dijkstra {

    private final Cell startNode;
    private final Cell endNode;

    private final LinkedList<Cell> queue;
    private final int width;
    private final int height;
    private int totalWalls;
    
    private boolean pathFound;
    
    public Dijkstra(Cell startNode, Cell endNode, int w, int h) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.width = w;
        this.height = h;
        this.totalWalls = 0;
        this.queue = new LinkedList<>();
        this.pathFound = true;
    }

    public void solve(Cell[][] graph, JButton[][] maze, JLabel labelPathFound, JLabel labelPathLength, JLabel labelcheckedPercentage, JLabel labelcheckedNumber, boolean diagonalsOn) {
        //WHERE I DIE   
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                queue.add(graph[i][j]);
                if(graph[i][j].isWall())
                    this.totalWalls++;
            }
        }
        this.startNode.setTentiveDst(0);

        Cell u = null;
        int checkedNodesNum = 0;
        while (!this.queue.isEmpty()) {
            u = getSmallestNode(this.queue);
            this.queue.remove(u);
            
            
            if(u.getTentiveDst() == Integer.MAX_VALUE){
                pathFound = false;
                labelPathFound.setText("NO PATH FOUND");
                System.out.println("FAILED");
                break;
            }
            

            int row = u.getX();    //columns
            int col = u.getY();    //rows
            int alt;

            //FOR EACH NEIGHBOR OF U IN QUEUE
            //UP
            if (isInBounds(row, col + 1) && this.queue.contains(graph[row][col + 1])  && !graph[row][col + 1].isWall() ) {
                Cell temp = graph[row][col + 1];
                alt = u.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(u);
                }
            }
            //DOWN
            if (isInBounds(row, col - 1) && this.queue.contains(graph[row][col - 1]) && !graph[row][col - 1].isWall() ) {
                Cell temp = graph[row][col - 1];
                alt = u.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(u);
                }
            }
            //RIGHT
            if (isInBounds(row + 1, col) && this.queue.contains(graph[row + 1][col]) && !graph[row + 1][col].isWall() ) {
                Cell temp = graph[row + 1][col];
                alt = u.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(u);
                }
            }
            //LEFT
            if (isInBounds(row - 1, col) && this.queue.contains(graph[row - 1][col]) && !graph[row - 1][col].isWall() ) {
                Cell temp = graph[row - 1][col];
                alt = u.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(u);
                }
            }
            
            if(diagonalsOn){
                //UP LEFT
                if (isInBounds(row + 1, col + 1) && this.queue.contains(graph[row + 1][col + 1])  && !graph[row + 1][col + 1].isWall() ) {
                    Cell temp = graph[row + 1][col + 1];
                    alt = u.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(u);
                    }
                }
                //UP RIGHT
                if (isInBounds(row - 1, col - 1) && this.queue.contains(graph[row - 1][col - 1]) && !graph[row - 1][col - 1].isWall() ) {
                    Cell temp = graph[row - 1][col - 1];
                    alt = u.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(u);
                    }
                }
                //DOWN LEFT
                if (isInBounds(row + 1, col + 1) && this.queue.contains(graph[row + 1][col + 1]) && !graph[row + 1][col + 1].isWall() ) {
                    Cell temp = graph[row + 1][col + 1];
                    alt = u.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(u);
                    }
                }
                //DOWN RIGHT
                if (isInBounds(row - 1, col - 1) && this.queue.contains(graph[row - 1][col - 1]) && !graph[row - 1][col - 1].isWall() ) {
                    Cell temp = graph[row - 1][col - 1];
                    alt = u.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(u);
                    }
                }
            }

            if (graph[row][col] != this.startNode
                    && graph[row][col] != this.endNode
                    && !graph[row][col].isWall()) {
                maze[row][col].setBackground(Colors.visited());
                maze[row][col].setBorder(null);
                checkedNodesNum++;
//                maze[row][col].setLabel(""+ graph[row][col].getTentiveDst());
            }
            
            if (u == this.endNode) {
                System.out.println("DONE!");
                break;
            }
        }

        //DRAW PATH
        int path_length = 0;
        
        if(this.pathFound){
            while(!u.equals(this.startNode)){  
                if(u.getPrev() != null){
                    u = u.getPrev();
                    path_length++;
                    if(graph[u.getX()][u.getY()] != this.startNode)
                        maze[u.getX()][u.getY()].setBackground(Colors.path()); 
                }
            }
            labelPathFound.setText("TRUE");
            labelPathLength.setText(path_length + " Nodes");
        }
        
        DecimalFormat df = new DecimalFormat("0.00");
        double temp_d = (double)checkedNodesNum / (this.width*this.height-this.totalWalls) * 100;
        
        labelcheckedPercentage.setText(""+ df.format(temp_d)  + " % of the Nodes");
        labelcheckedNumber.setText("Checked " +checkedNodesNum + " / "+ (this.width*this.height-totalWalls) + " Nodes");
        
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.height && col >= 0 && col < this.width;
    }

    private Cell getSmallestNode(LinkedList<Cell> list) {
        Cell currnt_sm = list.getFirst();

        for (Cell cell : list) {
            if (cell.getTentiveDst() < currnt_sm.getTentiveDst()) {
                currnt_sm = cell;
            }
        }
        return currnt_sm;
    }
}
