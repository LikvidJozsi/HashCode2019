package picsapat;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.omg.CORBA.PUBLIC_MEMBER;

public class VerticalSlide extends Slide{
	public Photo photo1;
	public Photo photo2;
	Tag[] tags;
	
	public VerticalSlide(Photo photo1, Photo photo2) {
		super();
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.tags = TagUtils.union(photo1.tags, photo2.tags);
	}
	
	@Override
	public Tag[] getTags() {
		return tags;
	}
	
	@Override
	public String toString() {
		return Integer.toString(photo1.id) + " " + Integer.toString(photo2.id);
	}
	
}
