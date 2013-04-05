package focus.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import menu.MenuSet;
import focus.LightFocusHandler;
import focus.SubFocus;
import focus.menu.MainPrimary;


public class MainMenu extends SubFocus {
	public static final Font homespun = getFont("resources/homespun.ttf");
	
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

	public MainMenu(LightFocusHandler handler, Component source){
		
		//TODO fix this
		super(true, true, 0, handler, source);
		foci = new MenuSet[1];
		foci[0] = new MainPrimary(this, source);
		priority = new int[1];
		for(int i = 0; i < 1; i++){
			priority[i] = i;
		}
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.white);
		g.fillRect(0, 0, source.getWidth(), source.getHeight());
		super.render(g);
	}
	
	public void handleInfo(int i, int id){
		switch(id){
		//main screen
		case 0: 
			switch(i){
			case 0: //local
				handler.handleInfo(0, 0);
				break;
			case 1: //online
				break;
			case 2: //profile
				break;
			case 3: //options
				break;
			case 4: //about
				break;
			case 5: //quit
				handler.handleInfo(1, 0);
				break;
			}
		}
	}
	
	public void closing(){}

}
