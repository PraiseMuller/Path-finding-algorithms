/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import java.util.Random;

public class Cell {
    private int x;
    private int y;
    private boolean isWall;
    private final int weight;
    private int tentiveDst;
    private boolean visited;
    private boolean visitedMC;
    private Cell prev;
    
    //for the A* alg
    private int fScore;

    public Cell(int x, int y, boolean isWall) {
        this.x = x;
        this.y = y;
        this.isWall = isWall;
        this.weight = 1; //getRandomWeight();
        this.tentiveDst = Integer.MAX_VALUE;
        this.fScore = Integer.MAX_VALUE;
        this.visited = false;
        this.visitedMC = false;
        this.prev = null;
    }

    public int getfScore() {
        return fScore;
    }

    public void setfScore(int fScore) {
        this.fScore = fScore;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public int getTentiveDst() {
        return this.tentiveDst;
    }

    public void setTentiveDst(int d) {
        this.tentiveDst = d;
    }
    
    public void setVisited(boolean is){
        this.visited = is;
    }
    
    public boolean getVisited(){
        return this.visited;
    }
    
    public void setVisitedMC(boolean is){
        this.visitedMC = is;
    }
    
    public boolean getVisitedMC(){
        return this.visitedMC;
    }
    
    public void setPrev(Cell prev){
        this.prev = prev;
    }
    
    public Cell getPrev(){
        return this.prev;
    }
    
//    private int getRandomWeight(){
//        Random rand = new Random();
//        return rand.nextInt(1, 10);
//    }
}
