package picsapat;


import java.util.Arrays;
import java.util.TreeSet;

public class Photo {
	public int id;
	public Orientation orientation;
	public Tag[] tags;
	
	public Photo() {
	}
	
	public Photo(int numofTags) {
		this.tags = new Tag[numofTags];
	}
	
	public Photo(Orientation orientation, int numOfTags) {
		this(numOfTags);
		this.orientation = orientation;
	}
	
	
	public void sort() {
		Arrays.sort(tags);
	}
}
