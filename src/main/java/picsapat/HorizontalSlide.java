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
	public Set<String> getTags() {
		
		return photo.tags ;
	}

}
