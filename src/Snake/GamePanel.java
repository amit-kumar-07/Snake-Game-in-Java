package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.util.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{ //extend gamePanel to Jpanel and import JPanel
	// to use implement actionlistner because system overwrite in my class 
	
	private int[] snakexlength = new int[750]; //to store snake body position
	private int[] snakeylength = new int[750]; 
	
	private int lengthofSnake = 3;
	
	private int[] xPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,}; // 29
	private int[] yPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575}; //21
	
	private Random random = new Random();
	private int enemyX, enemyY;
	
	private boolean right = true;//to check snake present direction
	private boolean left = false;
	private boolean up = false;
	private boolean down = false; // snake direction is right, only one is true
	
	private int moves = 0;
	
	private int score =0; 
	
	private boolean gameOver= false;
	
	private ImageIcon snaketitle= new ImageIcon(getClass().getResource("snaketitle.jpg"));//to acces image
	private ImageIcon leftmouth= new ImageIcon(getClass().getResource("leftmouth.png"));
	private ImageIcon rightmouth= new ImageIcon(getClass().getResource("rightmouth.png"));
	private ImageIcon upmouth= new ImageIcon(getClass().getResource("upmouth.png"));
	private ImageIcon downmouth= new ImageIcon(getClass().getResource("downmouth.png"));
	private ImageIcon snakeimage= new ImageIcon(getClass().getResource("snakeimage.png"));
	private ImageIcon enemy= new ImageIcon(getClass().getResource("enemy.png"));
	
	private Timer timer; // create timer class
	private int delay = 100; // which shos delay 100 ms;
	
	GamePanel(){ // make Constructor
		addKeyListener(this); // this means in this class
		setFocusable(true); // for work on jpanel
		setFocusTraversalKeysEnabled(true); //true
		timer = new Timer(delay, this);
		timer.start(); // every 100 mili sec this call action listner method
		
		newEnemy(); //to set enemy position we create new enemy method, whenever we call enemy we call this method
	}
	
	private void newEnemy() { // to set random position of enemy

		enemyX = xPos[random.nextInt(28)];
		enemyY = yPos[random.nextInt(20)];
		
		for(int i =lengthofSnake-1; i>=0; i--) {
			if(snakexlength[i] == enemyX && snakeylength[i] == enemyY) { //position of snake part is equal to enemy position part then we call again newenemy function
				newEnemy();
			}
		}
	}

	public void paint(Graphics g) { // to draw component , overwrite
			super.paint(g);
			g.setColor(Color.white); // for border color 
			g.drawRect(24, 10, 852, 57); // for title boarder
			g.drawRect(24, 74, 851, 540); // set game board boarder //576
			
			snaketitle.paintIcon(getFocusCycleRootAncestor(), g, 25,11); // to set image
			g.setColor(Color.black);
			g.fillRect(25, 75, 845, 535);
			
			if(moves==0) { // to assign the position of snake
				snakexlength[0] = 100;
				snakexlength[1] = 75;
				snakexlength[2] = 50;
				
				snakeylength[0] = 100; // assign x and y position of snake when snake is at rest or initial
				snakeylength[1] = 100;
				snakeylength[2] = 100;
				//moves++; // if we apply moves++ here game run after run run program
				// if we remove from there and apply at key method, then in starting is stop and we press key it runs
			}
			
			if(left) {
				leftmouth.paintIcon(getFocusCycleRootAncestor(), g, snakexlength[0], snakeylength[0]);
			}
			if(right) {
				rightmouth.paintIcon(getFocusCycleRootAncestor(), g, snakexlength[0], snakeylength[0]);
			}
			if(up) {
				upmouth.paintIcon(getFocusCycleRootAncestor(), g, snakexlength[0], snakeylength[0]);
			}
			if(down) {
				downmouth.paintIcon(getFocusCycleRootAncestor(), g, snakexlength[0], snakeylength[0]);
			} // this loop for print snake body , only mouth
			
			for(int i =1; i<lengthofSnake; i++) {
				snakeimage.paintIcon(getFocusCycleRootAncestor(), g, snakexlength[i], snakeylength[i]);
			} // to print body of snake
			
			enemy.paintIcon(getFocusCycleRootAncestor(), g, enemyX, enemyY);
			
			if(gameOver) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial",Font.PLAIN,20));
				g.drawString("Press SPACE to Restart", 320,350);
				
			}
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 14));
			g.drawString("Score : "+score, 750,30);
			g.drawString("Length : " +lengthofSnake, 750, 50);
			
			
			g.dispose(); //after performing action event dispose every time
			
			
			
			
		}
    
	public void actionPerformed(ActionEvent e) {
		
		for(int i = lengthofSnake-1; i>0; i--) { // for all body follow head
			snakexlength[i] = snakexlength[i-1];
			snakeylength[i] = snakeylength[i-1];
		}
		
		if(left) {
			snakexlength[0] = snakexlength[0]-25;
		}
		if(right) {
			snakexlength[0] = snakexlength[0]+25;
		}
		if(up) {
			snakeylength[0] = snakeylength[0]-25;
		}
		if(down) {
			snakeylength[0] = snakeylength[0]+25;
		}
		
		if(snakexlength[0] > 850) // condition for, if snake enter right side then he come from left side
			snakexlength[0] = 25;
		if(snakexlength[0]<25)
			snakexlength[0] = 850;
		
		if(snakeylength[0] > 598) // condition for, if snake enter up side then he come from down side
			snakeylength[0] = 75;
		if(snakeylength[0]<75)
			snakeylength[0] = 598;
		
		collidesWithEnemy();
		collidesWithBody();

		repaint(); // again repaint this so snake head move
	}

	private void collidesWithBody() {

		for(int i =lengthofSnake-1; i>0; i--) {
			if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) // collides condition
			{
				timer.stop();
				gameOver= true;
			}
			
		}
	}

	private void collidesWithEnemy() { // when enemy position and head position same then increse length of snake and set new random position of enemy

		if(snakexlength[0] == enemyX && snakeylength[0] == enemyY) {
			newEnemy();
			lengthofSnake++;
			score++;
		}
	}

	public void keyTyped(KeyEvent e) {
	
	}

	public void keyReleased(KeyEvent e) {

		
	}
	
	public void keyPressed(KeyEvent e) {// for giving command to key 
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			restart();
		}
	
		if(e.getKeyCode()== KeyEvent.VK_LEFT && (! right)) { 
			left = true;
			right = false;
			up = false;
			down = false;
			moves++;
		} // we apply and check right condition so snake doesnt change direction suddenly
		if(e.getKeyCode()== KeyEvent.VK_RIGHT && (! left)) {
			left = false;
			right = true;
			up = false;
			down = false;
			moves++;
		}
		if(e.getKeyCode()== KeyEvent.VK_UP && (! down)) {
			left = false;
			right = false;
			up = true;
			down = false;
			moves++;
		}
		if(e.getKeyCode()== KeyEvent.VK_DOWN && (! up)) {
			left = false;
			right = false;
			up = false;
			down = true;
			moves++;
		}
	}

	private void restart() {

		gameOver=false;
		moves=0;
		score=0;
		lengthofSnake=3;
		left=false;
		right=true;
		up=false;
		down=false;
		timer.start();
		repaint();
	}


}

//Amit Kumar