package picsapat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Photo {
	public int id;
	public Orientation orientation;
	public Set<String> tags;
	
	public Photo() {
		this.tags = new HashSet<String>();
	}
	
	public Photo(int numofTags) {
		this.tags = new HashSet<String>(numofTags);
	}
	
	public Photo(Orientation orientation, int numOfTags) {
		this(numOfTags);
		this.orientation = orientation;
	}
}
