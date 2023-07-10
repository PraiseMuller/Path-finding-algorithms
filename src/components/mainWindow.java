/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package components;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import misc.*;
import musvo.*;

public class mainWindow extends javax.swing.JFrame {

    private String algorithmName;
    private int cellSize;
    private boolean randomWallsOn;
    private boolean diagonals;

    private JButton[][] buttonsArr;
    private Cell[][] cellsArr;

    private int arrWIDTH;
    private int arrHEIGHT;

    private int btnClicks = 0;

    private Cell startNode;
    private Cell endNode;

    public mainWindow() {
        initComponents();
        findPathButton.setEnabled(false);
    }

    private void initVariables() {
        int x = algorithNameComboBox.getSelectedIndex();
        int y = cellSizeComboBox.getSelectedIndex();
        int z = randomWallsComboBox.getSelectedIndex();
        int zx = diagonalsComboBox.getSelectedIndex();

        this.algorithmName = algorithNameComboBox.getItemAt(x);
        this.cellSize = Integer.parseInt(cellSizeComboBox.getItemAt(y));
        this.randomWallsOn = randomWallsComboBox.getItemAt(z).equals("ON");
        this.diagonals = diagonalsComboBox.getItemAt(zx).equals("ON");

        //DRAW MAZE
        this.arrHEIGHT = 700 / this.cellSize;
        this.arrWIDTH = 1000 / this.cellSize;
        this.buttonsArr = new JButton[arrHEIGHT][arrWIDTH];
        this.cellsArr = new Cell[arrHEIGHT][arrWIDTH];

        for (int i = 0; i < this.arrHEIGHT; i++) {
            for (int j = 0; j < this.arrWIDTH; j++) {
                JButton temp = new JButton();
                temp.setSize(this.cellSize, this.cellSize);
                temp.setLocation(j * this.cellSize, i * this.cellSize);
                temp.setBackground(Colors.white_lighter());
                temp.setRolloverEnabled(false);
                //temp.setBorder(null);

                this.buttonsArr[i][j] = temp;
                
                //init cells arr...NODES
                this.cellsArr[i][j] = new Cell(i, j, this.randomWallsOn);

                mazePanel.add(temp);

                temp.addActionListener((e) -> {
                    this.btnClicks++;
                    buttsClickEvents(e);
                });
            }
        }

        initializeButton.setEnabled(false);
 
        //GENERATE MAZE
        if(this.randomWallsOn){
            GenerateMaze g_m = new GenerateMaze(cellsArr, buttonsArr);
        }         
    }

    //HANDLE SETTING START AND END POSITIONS
    private void buttsClickEvents(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        int x = btn.getX() / this.cellSize;
        int y = btn.getY() / this.cellSize;

        switch (this.btnClicks) {
            case 1 -> {
                btn.setBackground(Colors.startNode());
                btn.setLabel("A");
                this.startNode = cellsArr[y][x];
            }
            case 2 -> {
                btn.setBackground(Colors.endNode());
                btn.setLabel("B");
                this.endNode = cellsArr[y][x];
            }
            default -> {
                btn.setBackground(Colors.wall());
                cellsArr[y][x].setIsWall(true);    //Wall pt
            }
        }
        if(this.startNode != null && this.endNode != null)    
            findPathButton.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        algorithNameComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        randomWallsComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cellSizeComboBox = new javax.swing.JComboBox<>();
        initializeButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelStartNode = new javax.swing.JLabel();
        labelEndNode = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelPathFound = new javax.swing.JLabel();
        findPathButton = new javax.swing.JButton();
        labelPathLength = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        labelcheckedPercentage = new javax.swing.JLabel();
        labelcheckedNumber = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        diagonalsComboBox = new javax.swing.JComboBox<>();
        mazePanel = new javax.swing.JPanel();
        reRunButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Version 1.0.0.1");
        setBackground(misc.Colors.white());
        setResizable(false);

        jPanel1.setBackground(misc.Colors.white());

        jPanel2.setBackground(misc.Colors.dark()
        );

        algorithNameComboBox.setFont(new java.awt.Font("JetBrains Mono", 2, 12)); // NOI18N
        algorithNameComboBox.setForeground(misc.Colors.font());
        algorithNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dijkstra's Algorithm", "A* Algorithm", "DFS Algorithm", "BFS Algorithm", "*ALL " }));
        algorithNameComboBox.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel2.setForeground(misc.Colors.font()
        );
        jLabel2.setText("ALGORITHM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(algorithNameComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(algorithNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel1.setForeground(misc.Colors.font());
        jLabel1.setText("INITIALIZATION");

        jLabel3.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel3.setForeground(misc.Colors.font());
        jLabel3.setText("Random Walls");

        randomWallsComboBox.setFont(new java.awt.Font("JetBrains Mono", 2, 12)); // NOI18N
        randomWallsComboBox.setForeground(misc.Colors.font());
        randomWallsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OFF", "ON", " " }));
        randomWallsComboBox.setSelectedIndex(1);
        randomWallsComboBox.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel4.setForeground(misc.Colors.font());
        jLabel4.setText("Cell Size");

        cellSizeComboBox.setFont(new java.awt.Font("JetBrains Mono", 2, 12)); // NOI18N
        cellSizeComboBox.setForeground(misc.Colors.font());
        cellSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "20", "50", "100" }));
        cellSizeComboBox.setSelectedIndex(1);
        cellSizeComboBox.setFocusable(false);

        initializeButton.setBackground(misc.Colors.white()
        );
        initializeButton.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        initializeButton.setForeground(misc.Colors.font());
        initializeButton.setText("INITIALIZE & LOAD MAP");
        initializeButton.setFocusPainted(false);
        initializeButton.setFocusable(false);
        initializeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initializeButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel5.setForeground(misc.Colors.font());
        jLabel5.setText("EXECUTION");

        jLabel6.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel6.setForeground(misc.Colors.font());
        jLabel6.setText("Start Node:");

        jLabel7.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel7.setForeground(misc.Colors.font());
        jLabel7.setText("End Node:");

        labelStartNode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelStartNode.setForeground(misc.Colors.font());
        labelStartNode.setText("[0, 0]");

        labelEndNode.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelEndNode.setForeground(misc.Colors.font());
        labelEndNode.setText("[0, 0]");

        jLabel10.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel10.setForeground(misc.Colors.font());
        jLabel10.setText("Path Found: ");

        labelPathFound.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelPathFound.setForeground(misc.Colors.font());
        labelPathFound.setText("False");

        findPathButton.setBackground(misc.Colors.white()
        );
        findPathButton.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        findPathButton.setForeground(misc.Colors.font());
        findPathButton.setText("FIND PATH");
        findPathButton.setFocusPainted(false);
        findPathButton.setFocusable(false);
        findPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findPathButtonActionPerformed(evt);
            }
        });

        labelPathLength.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelPathLength.setForeground(misc.Colors.font());
        labelPathLength.setText("0 Nodes");

        jPanel3.setBackground(misc.Colors.dark_lighter());

        jLabel13.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel13.setForeground(misc.Colors.font());
        jLabel13.setText("Notes:");

        jLabel8.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel8.setText("1. Click on the maze to insert a START ");

        jLabel9.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel9.setText("   NODE, A. Marked in YELLOW. Clicking");

        jLabel11.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel11.setText("   again inserts the END NODE B, GREEN.");

        jLabel12.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel12.setText("   Any click after this inserts a WALL ");

        jLabel15.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel15.setText("   NODE, BLUE..");

        jLabel16.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel16.setText("2. If a path is found it is drawn in ");

        jLabel17.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel17.setText("   PURPLE.");

        jLabel18.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel18.setText("3. Visited Node that aren't part of the");

        jLabel19.setFont(new java.awt.Font("JetBrains Mono", 1, 10)); // NOI18N
        jLabel19.setText("   path drawn in the CREAM-ISH color.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel14.setForeground(misc.Colors.font());
        jLabel14.setText("Path Length: ");

        jLabel20.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel20.setForeground(misc.Colors.font());
        jLabel20.setText("Checked:");

        labelcheckedPercentage.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelcheckedPercentage.setForeground(misc.Colors.font());
        labelcheckedPercentage.setText("0 % of the Nodes");

        labelcheckedNumber.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        labelcheckedNumber.setForeground(misc.Colors.font());
        labelcheckedNumber.setText("0 / 0 Nodes");

        jLabel21.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel21.setForeground(misc.Colors.font());
        jLabel21.setText("Diagonals:");

        diagonalsComboBox.setFont(new java.awt.Font("JetBrains Mono", 2, 12)); // NOI18N
        diagonalsComboBox.setForeground(misc.Colors.font());
        diagonalsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ON", "OFF", " " }));
        diagonalsComboBox.setSelectedIndex(1);
        diagonalsComboBox.setToolTipText("Determines whether the path can move diagonally between cells.");
        diagonalsComboBox.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cellSizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(diagonalsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(randomWallsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(initializeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(findPathButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelcheckedPercentage)
                                    .addComponent(labelPathFound)
                                    .addComponent(labelStartNode)
                                    .addComponent(labelEndNode)
                                    .addComponent(labelPathLength)
                                    .addComponent(labelcheckedNumber)))
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cellSizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(diagonalsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(randomWallsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(initializeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelStartNode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelEndNode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelPathFound))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPathLength)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(labelcheckedPercentage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelcheckedNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(findPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mazePanel.setBackground(java.awt.SystemColor.control);
        mazePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "MAZE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("JetBrains Mono", 1, 12), new java.awt.Color(204, 204, 204))); // NOI18N

        javax.swing.GroupLayout mazePanelLayout = new javax.swing.GroupLayout(mazePanel);
        mazePanel.setLayout(mazePanelLayout);
        mazePanelLayout.setHorizontalGroup(
            mazePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mazePanelLayout.setVerticalGroup(
            mazePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 675, Short.MAX_VALUE)
        );

        reRunButton.setBackground(misc.Colors.white()
        );
        reRunButton.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        reRunButton.setForeground(misc.Colors.font());
        reRunButton.setText("RE-RUN");
        reRunButton.setFocusPainted(false);
        reRunButton.setFocusable(false);
        reRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reRunButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mazePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reRunButton, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(mazePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reRunButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void initializeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initializeButtonActionPerformed
        initVariables();
    }//GEN-LAST:event_initializeButtonActionPerformed

    private void findPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findPathButtonActionPerformed

        labelStartNode.setText("[" + this.startNode.getX() + "][" + this.startNode.getY() + "]");
        labelEndNode.setText("[" + this.endNode.getX() + "][" + this.endNode.getY() + "]");
        
        //INCASE START AND END IS DEFAULT
        buttonsArr[this.startNode.getX()][this.startNode.getY()].setBackground(Colors.startNode());
        buttonsArr[this.endNode.getX()][this.endNode.getY()].setBackground(Colors.endNode());
        buttonsArr[this.startNode.getX()][this.startNode.getY()].setLabel("A");
        buttonsArr[this.endNode.getX()][this.endNode.getY()].setLabel("B");
        
        cellsArr[this.startNode.getX()][this.startNode.getY()].setIsWall(false);
        cellsArr[this.endNode.getX()][this.endNode.getY()].setIsWall(false);

        switch (this.algorithmName) {
            case ("Dijkstra's Algorithm") -> {
                Dijkstra dijkstra_t = new Dijkstra(this.startNode, this.endNode, this.arrWIDTH, this.arrHEIGHT);
                dijkstra_t.solve(cellsArr, buttonsArr, labelPathFound, labelPathLength, labelcheckedPercentage, labelcheckedNumber, this.diagonals);
            }
            case ("A* Algorithm") -> {
                A_Star aStar_t = new A_Star(this.startNode,this.endNode, this.arrWIDTH, this.arrHEIGHT);
                aStar_t.solve(cellsArr, buttonsArr, labelPathFound, labelPathLength, labelcheckedPercentage, labelcheckedNumber, this.diagonals);
            }
            case ("DFS Algorithm") -> {
                DFS dfs_t = new DFS(this.startNode,this.endNode, this.arrWIDTH, this.arrHEIGHT);
                dfs_t.solve(cellsArr, buttonsArr, labelPathFound, labelPathLength, labelcheckedPercentage, labelcheckedNumber, this.diagonals);
            }
            case ("BFS Algorithm") -> {
                BFS bfs_t = new BFS(this.startNode,this.endNode, this.arrWIDTH, this.arrHEIGHT);
                bfs_t.solve(cellsArr, buttonsArr, labelPathFound, labelPathLength, labelcheckedPercentage, labelcheckedNumber, this.diagonals);
            }
            default -> {
                break;
            }
        }

    }//GEN-LAST:event_findPathButtonActionPerformed

    private void reRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reRunButtonActionPerformed
        this.setVisible(false);
        
        new mainWindow().setVisible(true);
    }//GEN-LAST:event_reRunButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> algorithNameComboBox;
    private javax.swing.JComboBox<String> cellSizeComboBox;
    private javax.swing.JComboBox<String> diagonalsComboBox;
    private javax.swing.JButton findPathButton;
    private javax.swing.JButton initializeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelEndNode;
    private javax.swing.JLabel labelPathFound;
    private javax.swing.JLabel labelPathLength;
    private javax.swing.JLabel labelStartNode;
    private javax.swing.JLabel labelcheckedNumber;
    private javax.swing.JLabel labelcheckedPercentage;
    private javax.swing.JPanel mazePanel;
    private javax.swing.JComboBox<String> randomWallsComboBox;
    private javax.swing.JButton reRunButton;
    // End of variables declaration//GEN-END:variables
}
