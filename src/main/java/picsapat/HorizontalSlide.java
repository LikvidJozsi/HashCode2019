package picsapat;

import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.RequestingUserName;

public class HorizontalSlide extends Slide{
	
	public Photo photo ;

	public HorizontalSlide(Photo photo) {
		this.photo = photo;
	}
	
	
	@Override
	public Tag[] getTags() {
		
		return photo.tags ;
	}
	
	@Override
	public String toString() {
		return Integer.toString(photo.id);
	}

}
