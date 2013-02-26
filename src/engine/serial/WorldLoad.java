package engine.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import engine.entity.Entity;

public class WorldLoad {
	
	public static ArrayList<Entity> loadWorld(String uri) throws IOException{
		ArrayList<Entity> arr = new ArrayList<Entity>();
		File f = new File(uri);
		if(!f.exists()) throw new IOException("Failed to locate file");
		Object temp;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
		try {
			temp = in.readObject();
		} catch (ClassNotFoundException e) {
			in.close();
			throw new IOException("Corrupt data file");
		}
		if(!(temp instanceof Integer)){
			in.close();
			throw new IOException("Corrupt data file");
		}
		Integer length = (Integer)temp;
		for(int i = 0; i < length; i++){
			try {
				temp = in.readObject();
			} catch (ClassNotFoundException e) {
				in.close();
				throw new IOException("Corrupt data file");
			}
			if(!(temp instanceof Entity)){
				in.close();
				throw new IOException("Corrupt data file");
			}
			arr.add((Entity)temp);
		}
		in.close();
		return arr; //TODO finish the loading
	}
}
