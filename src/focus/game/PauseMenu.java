package focus.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import menu.MenuSet;

import focus.LightFocusHandler;
import focus.SubFocus;
import focus.pausemenu.PausePrimary;


public class PauseMenu extends SubFocus {
	public static final Font homespun = getFont("resources/homespun.ttf");
	private static final Color trans = new Color(100,100,100,100);
	
	private static Font getFont(String s){
		InputStream stream;
		try{
			stream = new FileInputStream(new File(s));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		try {
			return Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(Font.PLAIN, 30f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PauseMenu(LightFocusHandler handler, Component source){
		super(true, false, 2, handler, source);
		foci = new MenuSet[1];
		foci[0] = new PausePrimary(this, source);
		priority = new int[1];
		for(int i = 0; i < priority.length; i++){
			priority[i] = i;
		}
	}
	
	public void render(Graphics2D g){
		g.setColor(trans);
		g.fillRect(0, 0, source.getWidth(), source.getHeight());
		super.render(g);
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_P){
			handler.handleInfo(0, id);
		}
	}
	
	public void handleInfo(int i, int id){
		switch(id){
		//main screen
		case 0:
			switch(i){ //goes by button
			case 0: //Resume
				handler.handleInfo(1, 2); //1 == id of game, 2 == id of pausemenu
				break;
			case 1: //Settings
				break;
			case 2:
				handler.handleInfo(0, 2);
				break;
			}
		}
	}

	public void closing() {}

}
