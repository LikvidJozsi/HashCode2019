package picsapat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagUtils {
	public static Set<String> union(Photo photo1, Photo photo2){
		Set<String> union = new HashSet<String>(Math.max(photo1.tags.size(), photo2.tags.size()));
		for (String string : photo1.tags) {
			if(!union.contains(string)) {
				union.add(string);
			}
		}
		for (String string : photo2.tags) {
			if(!union.contains(string)) {
				union.add(string);
			}
		}
		return union;
	}
}
