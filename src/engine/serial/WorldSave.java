package engine.serial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import engine.entity.Entity;

public class WorldSave {
	
	public static void save(ArrayList<Entity> data, String uri) throws IOException{
		File f = new File(uri);
		if(!f.createNewFile()){
			f.delete();
			f.createNewFile();
		}
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		Integer length = data.size();
		out.writeObject(length);
		for(int i = 0; i < data.size(); i++){
			out.writeObject(data.get(i));
		}
		out.flush();
		out.close();
	}
	//TODO add stuff
}
