package picsapat;

import java.util.List;
import java.util.Set;

import org.omg.CORBA.PUBLIC_MEMBER;

public class VerticalSlide extends Slide{
	public Photo photo1;
	public Photo photo2;
	Set<String> tags;
	
	public VerticalSlide(Photo photo1, Photo photo2) {
		super();
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.tags = TagUtils.union(photo1.tags, photo2.tags);
	}
	
	@Override
	public Set<String> getTags() {
		return tags;
	}
	
}
