package picsapat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Photo> photos= getInput(new File("a_example.txt"));
		
	}
	
	
	public static List<Photo> getInput(File source){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			int numOfPhotos = Integer.parseInt(br.readLine());
			List<Photo> photos = new ArrayList<Photo>(numOfPhotos);
			for (int i = 0; i < numOfPhotos; i++) {
				String[] line = br.readLine().split(" ");
				String orientation = line[0];
				int numOfTags = Integer.parseInt(line[1]);
				
				Photo photo = new Photo(numOfTags);
				if(orientation.equals("H"))
					photo.orientation = Orientation.HORIZONTAL;
				else if (orientation.equals("V"))
					photo.orientation = Orientation.VERTICAL;
				else {
					throw new IOException("valami bűzlik");
				}
				
				for (int j = 0; j < numOfTags; j++) {
					photo.tags.add(line[2+j]);
				}
				photos.add(photo);
			}
			return photos;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
