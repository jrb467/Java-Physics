//dE6gx7XP4vK2



package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame{
	private static volatile boolean alive = false; //boolean to tell if the game is running
	public static final long serialVersionUID = 1L; //serial version
	private static final int DELAYS_PER_YIELD = 5;
	private int ups = 40;  //desired fps
	private int period = 1000000000/ups;  //time to sleep in order to reach desired fps
	private static final int MAX_FRAME_SKIPS = 5;  //max frame skips

	private JPanel contentPane;
	private BufferedImage buffer = null; //buffer for painting to screen
	private Graphics2D graphics; //graphics for the buffer
	
	private GameManager manager;
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				new GameFrame();
			}
		});
	}
	
	public GameFrame(){
		super();//TODO add title
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setLocation(100,100);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		manager = new GameManager(this);
		addWindowListener(manager);
		contentPane.addMouseListener(manager);
		addKeyListener(manager);
		setVisible(true);
		Thread main = new Thread(){
			public void run(){
				begin();
			}
		};
		main.start();
	}
	
	/* This is the standard loop I use for game logic and rendering.  You don't really need to
	 * understand it, just realize that it's designed to prevent lag and prevent cpu lockup. At 
	 * the top of this class you can change the fps to effect the number of updates/renders per second
	 * 
	 */
	public void begin(){
		alive = true;
		long before, after, timeDiff, sleepTime;  //used in maintaining desired fps/ups
		long overSleepTime = 0L; //amount of time machine overslept
		int delays = 0; //counts number of delays(when machine didn't sleep)
		long extraTime = 0L; //how much time the machine overused for updating/rendering
		int skips = 0; //current number of skips
		sleepTime = (long)1000L/ups;
		
		while(alive){
			before = System.nanoTime();
			manager.update(contentPane.getMousePosition());
			if(prepareGraphics())
				manager.render(graphics);
			paintGame();
			after = System.nanoTime();
			timeDiff = after-before;
			sleepTime = (long)(period - timeDiff) - overSleepTime; //calculate desired time to sleep
			if(sleepTime>0){
				try{
					Thread.sleep(sleepTime/1000000L);  //sleep
				}catch (InterruptedException e){}
				overSleepTime = (long)(System.nanoTime()-after)-sleepTime; //calculate how long the machine overslept
			}else{
				extraTime -= sleepTime;
				overSleepTime = 0L;
				//allow other essential threads to execute
				if(++delays >= DELAYS_PER_YIELD){
					Thread.yield();
					delays = 0;
				}
			}
			//update without rendering if the whole process takes too long
			while((extraTime>=period) && (skips<=MAX_FRAME_SKIPS)){
				manager.update(contentPane.getMousePosition());
				extraTime -= period;
				skips++;
			}
			skips = 0;
		}
		System.exit(0);
	}
	
	private boolean prepareGraphics(){
		if(buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()){
			if(getWidth() > 0 && getHeight() > 0){
				buffer = (BufferedImage)contentPane.createImage(getWidth(), getHeight());
				graphics = (Graphics2D)buffer.getGraphics();
				return true;
			}
			return false;
		}else{
			graphics = (Graphics2D)buffer.getGraphics();
			return true;
		}
	}
	
	/* Method overview:
	 * 		Having rendered to the buffer image, now it must be drawn to the actual JPanel.
	 * 		In doing this, it gets the JPanel's(this) graphics and, if the JPanel and buffer
	 * 		image aren't null, draws the image to the JPanel.
	 * 
	 */
	public void paintGame(){
		//paints buffer to screen
		Graphics g;
		try{
			g = contentPane.getGraphics();
			//if both can be painted
			if((g != null) && (buffer != null)){
				g.drawImage(buffer, 0, 0, null);
			}else{}
			g.dispose();
		}catch (Exception e){}
	}
	
	public void destroy(){
		alive = false;
		dispose();
		System.exit(0);
	}
}
