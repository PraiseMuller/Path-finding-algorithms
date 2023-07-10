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

public class A_Star {

    private Cell startNode;
    private Cell endNode;

    private LinkedList<Cell> openSet;
    private LinkedList<Cell> closedSet;

    private int height;
    private int width;
    private int totalWalls;

    private boolean pathFound;

    public A_Star(Cell startNode, Cell endNode, int w, int h) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.openSet = new LinkedList<>();
        this.closedSet = new LinkedList<>();
        this.width = w;
        this.height = h;
        this.totalWalls = 0;
        this.pathFound = true;
    }

    public void solve(Cell[][] graph, JButton[][] maze, JLabel labelPathFound, JLabel labelPathLength, JLabel labelcheckedPercentage, JLabel labelcheckedNumber, boolean diagonalsOn) {

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (graph[i][j].isWall()) {
                    this.totalWalls++;
                }
            }
        }

        this.openSet.add(this.startNode);
        this.startNode.setTentiveDst(0);    //G-SCORE
        this.startNode.setfScore(heuristicFn(this.startNode, this.endNode));     //F-SCORE --> gScore[n] + h[n]

        Cell current = null;
        int checkedNodesNum = 0;

        while (!this.openSet.isEmpty()) {
            //NOTE:
            //CURRENT IS THE NODE WITH THE LOWEST F-SCORE
            current = getSmallestFScoreNode(this.openSet);

            if (current == this.endNode) {
                System.out.println("DONE!");
                break;
            }

            this.openSet.remove(current);

            int row = current.getX();    //columns
            int col = current.getY();    //rows
            int alt;                     //tentative g-score

            //FOR EACH NEIGHBOR OF CURRENT
            //UP
            if (isInBounds(row, col + 1) && !graph[row][col + 1].isWall()) {
                Cell temp = graph[row][col + 1];
                alt = current.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(current);
                    temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                    if (!this.openSet.contains(temp)) {
                        this.openSet.add(temp);
                    }
                }
            }
            //DOWN
            if (isInBounds(row, col - 1) && !graph[row][col - 1].isWall()) {
                Cell temp = graph[row][col - 1];
                alt = current.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(current);
                    temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                    if (!this.openSet.contains(temp)) {
                        this.openSet.add(temp);
                    }
                }
            }
            //RIGHT
            if (isInBounds(row + 1, col) && !graph[row + 1][col].isWall()) {
                Cell temp = graph[row + 1][col];
                alt = current.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(current);
                    temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                    if (!this.openSet.contains(temp)) {
                        this.openSet.add(temp);
                    }
                }
            }
            //LEFT
            if (isInBounds(row - 1, col) && !graph[row - 1][col].isWall()) {
                Cell temp = graph[row - 1][col];
                alt = current.getTentiveDst() + temp.getWeight();
                if (alt < temp.getTentiveDst()) {
                    temp.setTentiveDst(alt);
                    temp.setPrev(current);
                    temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                    if (!this.openSet.contains(temp)) {
                        this.openSet.add(temp);
                    }
                }
            }

            if (diagonalsOn) {
                //UP LEFT
                if (isInBounds(row + 1, col - 1) && !graph[row + 1][col - 1].isWall()) {
                    Cell temp = graph[row + 1][col - 1];
                    alt = current.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(current);
                        temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                        if (!this.openSet.contains(temp)) {
                            this.openSet.add(temp);
                        }
                    }
                }
                //UP RIGHT
                if (isInBounds(row + 1, col + 1) && !graph[row + 1][col + 1].isWall()) {
                    Cell temp = graph[row + 1][col + 1];
                    alt = current.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(current);
                        temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                        if (!this.openSet.contains(temp)) {
                            this.openSet.add(temp);
                        }
                    }
                }
                //DOWN LEFT
                if (isInBounds(row - 1, col - 1) && !graph[row - 1][col - 1].isWall()) {
                    Cell temp = graph[row - 1][col - 1];
                    alt = current.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(current);
                        temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                        if (!this.openSet.contains(temp)) {
                            this.openSet.add(temp);
                        }
                    }
                }
                //DOWN RIGHT.. DOWN BAD :-0
                if (isInBounds(row - 1, col + 1) && !graph[row - 1][col + 1].isWall()) {
                    Cell temp = graph[row - 1][col + 1];
                    alt = current.getTentiveDst() + temp.getWeight();
                    if (alt < temp.getTentiveDst()) {
                        temp.setTentiveDst(alt);
                        temp.setPrev(current);
                        temp.setfScore(temp.getTentiveDst() + heuristicFn(temp, this.endNode));
                        if (!this.openSet.contains(temp)) {
                            this.openSet.add(temp);
                        }
                    }
                }
            }

            //OpenSet empty but goal not reached --> FAILURE
            if (this.openSet.isEmpty() && current != this.endNode) {
                System.out.println("FAILED!");
                labelPathFound.setText("NO PATH FOUND");
                this.pathFound = false;
                break;
            }

            //DRAW DIFF COLOR FOR CHECKED NODES..
            //THESE NOT PART OF THE SOLUTION
            if (graph[row][col] != this.startNode
                    && graph[row][col] != this.endNode
                    && !graph[row][col].isWall()) {
                maze[row][col].setBackground(Colors.visited());
                maze[row][col].setBorder(null);
                checkedNodesNum++;
//                maze[row][col].setLabel(""+ graph[row][col].getTentiveDst());
            }
        }

        //////
        /////
        //DRAW PATH
        int path_length = 0;
        Cell u = current;
        if (this.pathFound) {
            while (!u.equals(this.startNode)) {
                if (u.getPrev() != null) {
                    u = u.getPrev();
                    path_length++;
                    if (graph[u.getX()][u.getY()] != this.startNode) {
                        maze[u.getX()][u.getY()].setBackground(Colors.path());
                    }
                }
            }
            labelPathFound.setText("TRUE");
            labelPathLength.setText(path_length + " Nodes");
        }

        DecimalFormat df = new DecimalFormat("0.00");
        double temp_d = (double) checkedNodesNum / (this.width * this.height - this.totalWalls) * 100;
        labelcheckedPercentage.setText("" + df.format(temp_d) + " % of the Nodes");
        labelcheckedNumber.setText("Checked " + checkedNodesNum + " / " + (this.width * this.height - totalWalls) + " Nodes");

    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.height && col >= 0 && col < this.width;
    }

    private Cell getSmallestFScoreNode(LinkedList<Cell> list) {
        Cell currnt_sm = list.getFirst();

        for (Cell cell : list) {
            if (cell.getfScore() < currnt_sm.getfScore()) {
                currnt_sm = cell;
            }
        }
        return currnt_sm;
    }

    private int heuristicFn(Cell a, Cell b) {

        int ax = a.getX(), ay = a.getY();
        int bx = b.getX(), by = b.getY();

        //Manhattan distance
        return Math.abs(ax - bx) + Math.abs(ay - by);
    }
}
