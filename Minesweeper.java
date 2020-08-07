import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;
import javax.swing.Timer;
 
 
public class Minesweeper implements ActionListener,MouseListener
{
    ImageIcon mine, flag, one, two, three, four, five, six, seven, eight, block, empty, smiley, smileyLose, smileyWin; //original
    
    ImageIcon snitch, broom, HP1, HP2, HP3, HP4, HP5, HP6, HP7, HP8; //harry potter theme
    
    static JMenuBar mb = new JMenuBar(); 
    static JMenu menu = new JMenu("Game"); 
    
    //static JMenuBar hpmb = new JMenuBar(); ; 
    static JMenu hpMenu = new JMenu("Theme"); 
    static JMenu controlsMenu = new JMenu("Controls"); 
    
    static JRadioButtonMenuItem m1, m2, m3;
    static JLabel timer;
   
    static JRadioButtonMenuItem original, harryPotter;
    static ButtonGroup diffGroup, themeGroup;
    static JFrame frame = new JFrame("Menu demo"); 
    static JPanel gridPanel = new JPanel(), buttonPanel = new JPanel(), mainPanel = new JPanel();
    int numRows = 9;
    int numCols = 9;
    int numMines=10;
    JToggleButton buttons[][];
    //JToggleButton randomMines[][];
    int l = 0;
    static int timeCount = 0;
    boolean firstClick=true, stillPlaying=true;
    JButton resetButton;
    int counter;
    static Timer timerMaker;
    
    
    public Minesweeper()
    {
        mb.add(menu); 
        menu();
        
        //frame.setLayout(new BorderLayout());      
        
        mb.add(hpMenu);
        mb.add(controlsMenu);
        
        
        
        //frame.add(mb,BorderLayout.EAST);
        smiley = new ImageIcon("smiley.png");
        smiley = new ImageIcon(smiley.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
 
        smileyWin = new ImageIcon("winSmiley.png");
        smileyWin = new ImageIcon(smileyWin.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
 
        smileyLose = new ImageIcon("loseSmiley.png");
        smileyLose = new ImageIcon(smileyLose.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
 
        mine = new ImageIcon("mine.png");
        mine = new ImageIcon(mine.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        flag = new ImageIcon("flagged.png");
        flag = new ImageIcon(flag.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        one = new ImageIcon("one.png");
        one = new ImageIcon(one.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        two = new ImageIcon("two.png");
        two = new ImageIcon(two.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        three = new ImageIcon("three.png");
        three = new ImageIcon(three.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        four = new ImageIcon("four.png");
        four = new ImageIcon(four.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        five = new ImageIcon("five.png");
        five = new ImageIcon(five.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        six = new ImageIcon("six.png");
        six = new ImageIcon(six.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        seven = new ImageIcon("seven.png");
        seven = new ImageIcon(seven.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        eight = new ImageIcon("eight.png");
        eight = new ImageIcon(eight.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        block = new ImageIcon("block.png");
        block = new ImageIcon(block.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        empty = new ImageIcon("empty.png");
        empty = new ImageIcon(empty.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        //harry potter theme
        snitch = new ImageIcon("HarryPotterSnitch.png");
        snitch = new ImageIcon(snitch.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        broom = new ImageIcon("HarryPotterBroomStick.png");
        broom = new ImageIcon(broom.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP1 = new ImageIcon("HarryPotter1.png");
        HP1 = new ImageIcon(HP1.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP2 = new ImageIcon("HarryPotter2.jpg");
        HP2 = new ImageIcon(HP2.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP3 = new ImageIcon("HarryPotter3.jpg");
        HP3 = new ImageIcon(HP3.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP4 = new ImageIcon("HarryPotter4.jpg");
        HP4 = new ImageIcon(HP4.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP5 = new ImageIcon("HarryPotter5.jpg");
        HP5 = new ImageIcon(HP5.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP6 = new ImageIcon("HarryPotter6.jpg");
        HP6 = new ImageIcon(HP6.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP7 = new ImageIcon("HarryPotter7.jpg");
        HP7 = new ImageIcon(HP7.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        HP8 = new ImageIcon("HarryPotter8.jpg");
        HP8 = new ImageIcon(HP8.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        
        makeButtons();
        resetButton = new JButton(smiley);
        buttonPanel.setSize(frame.getWidth(), 50);
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 5;
        mainPanel.add(mb,constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(buttonPanel,constraints); 
        timer = new JLabel("0");
        ActionListener makingTheTimer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timeCount++;
                if(timeCount < 10){
                    timer.setText("00"+timeCount);
                }else if(timeCount < 100){
                    timer.setText("0"+timeCount);
                }else
                    timer.setText(""+timeCount);
                //System.out.println(timeCount);
  
            }
        };
        timerMaker = new Timer(1000, makingTheTimer);
        
        buttonPanel.add(timer);
 
        frame.add(mainPanel,BorderLayout.NORTH);
 
        frame.setVisible(true);     
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       //color changing for buttons: UIManager.put("buttons.select", Color.RED);
    }
 
    public void menu() {
        m1 = new JRadioButtonMenuItem("Beginner"); 
        m2 = new JRadioButtonMenuItem("Intermediate"); 
        m3 = new JRadioButtonMenuItem("Expert"); 
        diffGroup = new ButtonGroup();
        themeGroup = new ButtonGroup();
        diffGroup.add(m1);
        diffGroup.add(m2);
        diffGroup.add(m3);
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        menu.add(m1); 
        menu.add(m2); 
        menu.add(m3); 
        m1.setSelected(true);
        
        original = new JRadioButtonMenuItem("Original"); 
        harryPotter = new JRadioButtonMenuItem("Harry Potter");
        //theme2 = new JRadioButtonMenuItem("Another Theme");
        themeGroup.add(original);
        themeGroup.add(harryPotter);
       // themeGroup.add(theme2);
        original.addActionListener(this);
        harryPotter.addActionListener(this);
       // theme2.addActionListener(this);
        hpMenu.add(original); 
        hpMenu.add(harryPotter);
        //hpMenu.add(theme2);
        original.setSelected(true);
        
        controlsMenu.add("Right click a square to flag it. Left click a square to reveal it");
        
        
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if(m1.isSelected()) 
        {
            numRows = 9;
            numCols = 9;
            numMines=10;
            timerMaker.stop();
            timeCount = 0;
            timer.setText("0");
            firstClick = true;
            stillPlaying = true;
            makeButtons();
 
        }
        if(m2.isSelected()) 
        {
            numRows = 16;
            numCols = 16;
            numMines=40;
            timerMaker.restart();
            timeCount = 0;
            timer.setText("0");
            firstClick = true;
            stillPlaying = true;
            makeButtons();
 
        }
        if(m3.isSelected()) {
            numRows = 16;
            numCols = 30;
            numMines=99;
            timerMaker.restart();
            timeCount = 0;
            timer.setText("0");
            firstClick = true;
            stillPlaying = true;
            makeButtons();
 
        }
        if(e.getSource().equals(resetButton)){
            firstClick = true;
            stillPlaying = true;
            timerMaker.restart();
            timeCount = 0;
            timer.setText("0");
            System.out.println("SDF");
            resetButton.setIcon(smiley);
            makeButtons();
        }
    }
    
    
    public void makeButtons()
    {
        if(gridPanel!=null)
            frame.remove(gridPanel);
        
        buttons = new JToggleButton[numRows][numCols];
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(numRows, numCols));
 
 
        
        for (int i = 0; i < numRows; i++) 
        {
            for (int j = 0; j < numCols; j++) 
            {
                buttons[i][j] = new JToggleButton();
                buttons[i][j].addMouseListener(this);
                buttons[i][j].putClientProperty("x", i);
                buttons[i][j].putClientProperty("y", j);
                buttons[i][j].setIcon(block);
                buttons[i][j].setBorder(BorderFactory.createEmptyBorder());
                //buttons[i][j].setIcon(block);
                gridPanel.add(buttons[i][j]);
                
                //l++;
                
            }
        }
        frame.add(gridPanel,BorderLayout.CENTER);
        frame.setSize(32*numCols, 38*numRows); 
        frame.revalidate();
        
      //color changing for buttons: SwingUtilities.updateComponentTreeUI(buttons[numRows][numCols]);
            
    }      
    public void dropMines(int currRow,int currCol)
    {
        
            int minesUsed = numMines;
 
            ArrayList<Point> usedPoints = new ArrayList<Point>(Arrays.asList(new Point(currRow, currCol), new Point(currRow-1, currCol), new Point(currRow+1, currCol), new Point(currRow, currCol-1), new Point(currRow-1, currCol-1), new Point(currRow+1, currCol-1), new Point(currRow, currCol+1), new Point(currRow-1, currCol+1), new Point(currRow+1, currCol+1)));
            
            int randomI = (int) (Math.random()*numRows);
            int randomJ = (int) (Math.random()*numCols);
            if(original.isSelected()){
                //System.out.println("WOO");
            }
            
              //find a non mine place
            
            while(minesUsed > 0){
                do{
                    randomI = (int) (Math.random()*numRows);
                    randomJ = (int) (Math.random()*numCols);
                }while(usedPoints.contains(new Point(randomI, randomJ)));
                buttons[randomI][randomJ].putClientProperty("x", randomI);
                buttons[randomI][randomJ].putClientProperty("y", randomJ);
                buttons[randomI][randomJ].putClientProperty("state", "bomb");
                buttons[randomI][randomJ].putClientProperty("expanded", "false");
 
                usedPoints.add(new Point(randomI, randomJ));
                minesUsed--;
            }
 
            
            
           //this is where we will count number of mines surrounding for each buttons
           counter = 0;
            
            for (int i = 0; i < numRows; i++) 
            {
                for (int j = 0; j < numCols; j++) 
                {
                    if(buttons[i][j].getClientProperty("state") == null)
                    {
                        counter = checkForMines(i,j);
                        if(counter == 0){
                            buttons[i][j].putClientProperty("state", "empty");
                            buttons[i][j].putClientProperty("expanded", "false");
 
                        }if(counter == 1){  // original 1
                            buttons[i][j].putClientProperty("state", "1");
                            buttons[i][j].putClientProperty("expanded", "false");
 
                        }
                        if(counter == 2){  // original 2
                            buttons[i][j].putClientProperty("state", "2");
                            buttons[i][j].putClientProperty("expanded", "false");
 
 
                        }if(counter == 3){  // original 3
                            buttons[i][j].putClientProperty("state", "3");
                            buttons[i][j].putClientProperty("expanded", "false");
 
 
                        }if(counter == 4){  // original 4
                            buttons[i][j].putClientProperty("state", "4");
                            buttons[i][j].putClientProperty("expanded", "false");
 
 
                        }if(counter == 5){  // original 5
                            buttons[i][j].putClientProperty("state", "5");
                            buttons[i][j].putClientProperty("expanded", "false");
 
 
                        }if(counter == 6){  // original 6
                            buttons[i][j].putClientProperty("state", "6");
                            buttons[i][j].putClientProperty("expanded", "false");
 
 
                        }if(counter == 7){  // original 7
                            buttons[i][j].putClientProperty("state", "7");
                            buttons[i][j].putClientProperty("expanded", "false");
 
 
                        }if(counter == 8){  // original 8 
                            buttons[i][j].putClientProperty("state", "8");
                            buttons[i][j].putClientProperty("expanded", "false");
                        }
                    }
                }
            }
 
            // for(int x = 0; x < buttons.length; x++){
            //  for(int j = 0; j < buttons[x].length; j++){
            //      System.out.print(buttons[x][j].getClientProperty("state")+"\t\t");
            //  }
            //  System.out.println();
            // }
            
            
    }
    
    public void expandingThing(int row, int col){
        try {
            if(!buttons[row][col].getClientProperty("state").equals("bomb") && buttons[row][col].getClientProperty("expanded").equals("false")){
                buttons[row][col].putClientProperty("expanded", "true");
                reveal(row, col);
                if(buttons[row][col].getClientProperty("state").equals("empty"))
                    expand(row, col);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
    public void expand(int row, int col)
    {
        reveal(row, col);
        expandingThing(row, col+1);
        expandingThing(row, col-1);
        expandingThing(row+1, col);
        expandingThing(row+1, col+1);
        expandingThing(row+1, col-1);
        expandingThing(row-1, col);
        expandingThing(row-1, col+1);
        expandingThing(row-1, col-1);
    }
    public void reveal(int row, int col){
        buttons[row][col].setBorder(BorderFactory.createEtchedBorder(Color.LIGHT_GRAY, Color.BLACK));
        
        switch((String)buttons[row][col].getClientProperty("state")){
            case "empty":
                buttons[row][col].setIcon(empty);
                break;
            case "bomb":
                if(original.isSelected())
                    buttons[row][col].setIcon(mine);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(snitch);
                break;
            case "1":
                if(original.isSelected())
                    buttons[row][col].setIcon(one);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP1);
                break;
            case "2":
                if(original.isSelected())
                    buttons[row][col].setIcon(two);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP2);
                break;
            case "3":
                if(original.isSelected())
                    buttons[row][col].setIcon(three);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP3);
                break;
            case "4":
                if(original.isSelected())
                    buttons[row][col].setIcon(four);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP4);
                break;
            case "5":
                if(original.isSelected())
                    buttons[row][col].setIcon(five);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP5);
                break;
            case "6":
                if(original.isSelected())
                    buttons[row][col].setIcon(six);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP6);
                break;
            case "7":
                if(original.isSelected())
                    buttons[row][col].setIcon(seven);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP7);
                break;
            case "8":
                if(original.isSelected())
                    buttons[row][col].setIcon(eight);
                if(harryPotter.isSelected());
                    buttons[row][col].setIcon(HP8);
                break;
        }
    }
    
    public void endGame(){
        //System.out.println("playigng");
        stillPlaying = false;
        for(int x = 0; x < buttons.length; x++){
            for(int y = 0; y < buttons[x].length; y++){
                if(buttons[x][y].getClientProperty("state").equals("bomb"))
                    reveal(x, y);
            }
        }
        timerMaker.stop();
        resetButton.setIcon(smileyLose);
    }
    
    public int checkForMines(int i, int j)
    {
        int counterNow = 0;
 
        try {
            if(buttons[i+1][j].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i+1][j-1].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i+1][j+1].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i][j].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i][j-1].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i][j+1].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i-1][j].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i-1][j-1].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        try {
            if(buttons[i-1][j+1].getClientProperty("state").equals("bomb")) //below
            {
                counterNow +=1;
            }
        } catch (Exception e) {
        }
        return counterNow;
    }
    
        public void mouseReleased(MouseEvent e) {
            if(stillPlaying){
                if(e.getButton()==MouseEvent.BUTTON1)
                {
                    if(((JToggleButton)e.getComponent()).isSelected()){
                        JToggleButton currentButton = (JToggleButton)e.getComponent();
                        if(firstClick){
                            timerMaker.start();
                            dropMines((int)currentButton.getClientProperty("x"),(int)currentButton.getClientProperty("y"));
                            firstClick=false;
                            if(currentButton.getClientProperty("state").equals("empty"))
                                expand((int)currentButton.getClientProperty("x"),(int)currentButton.getClientProperty("y"));
                            }
                            if(currentButton.getClientProperty("state").equals("empty")){
                                expand((int)currentButton.getClientProperty("x"),(int)currentButton.getClientProperty("y"));
                            }else if((currentButton.getClientProperty("state").equals("bomb"))){
                                endGame();
                            }else{
                                reveal((int)currentButton.getClientProperty("x"),(int)currentButton.getClientProperty("y"));
                            }
                    }
                    
                }
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    if(!((JToggleButton)e.getComponent()).isSelected())
                    {
                        if(((JToggleButton)e.getComponent()).getIcon()!=flag){
                            if(original.isSelected())
                                ((JToggleButton)e.getComponent()).setIcon(flag);
                            if(harryPotter.isSelected())
                                ((JToggleButton)e.getComponent()).setIcon(broom);
                        }else{
                            ((JToggleButton)e.getComponent()).setIcon(block);
                        }
                            
                    }
                }
            }
            checkForWin();
    }
    public void checkForWin(){
        int counting = 0;
        for(int x = 0; x < buttons.length; x++){
            for(int y = 0; y < buttons[x].length; y++){
                if(buttons[x][y].getClientProperty("state").equals("bomb") && buttons[x][y].getIcon() == flag)
                counting++;
            }
        }
        if(counting == numMines){
            for(int x = 0; x < buttons.length; x++){
                for(int y = 0; y < buttons[x].length; y++){
                    if(buttons[x][y].getIcon() == block){
                        reveal(x, y);
                    }
                }
                timerMaker.stop();
            }
            stillPlaying = false;
            resetButton.setIcon(smileyWin);
        }
    }
    
    //****************************************************************************************************************
    public static void main(String[] args) 
    { 
        Minesweeper app=new Minesweeper();
    }       
    
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
 
    
}
 
 
