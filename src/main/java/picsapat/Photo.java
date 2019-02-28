package picsapat;

import java.util.ArrayList;
import java.util.List;

public class Photo {
	public int id;
	public Orientation orientation;
	public List<String> tags;
	
	public Photo() {
		this.tags = new ArrayList<String>();
	}
	
	public Photo(int numofTags) {
		this.tags = new ArrayList<String>(numofTags);
	}
	
	public Photo(Orientation orientation, int numOfTags) {
		this(numOfTags);
		this.orientation = orientation;
	}
}
