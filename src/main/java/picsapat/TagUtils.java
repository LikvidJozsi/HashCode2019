package picsapat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagUtils {
	public static Set<String> union(Set<String> photo1, Set<String> photo2){
		Set<String> union = new HashSet<String>(Math.max(photo1.size(), photo2.size()));
		for (String string : photo1) {
			if(!union.contains(string)) {
				union.add(string);
			}
		}
		for (String string : photo2) {
			if(!union.contains(string)) {
				union.add(string);
			}
		}
		return union;
	}
	
	public static Set<String> intersect(Set<String> photo1, Set<String> photo2){
		Set<String> intersect = new HashSet<String>(Math.max(photo1.size(), photo2.size()));
		for (String string : photo1) {
			if(photo2.contains(string)) {
				intersect.add(string);
			}
		}
		return intersect;
	}
	
	public static Set<String> difference(Set<String> photo1, Set<String> photo2){
		Set<String> difference = new HashSet<String>();
		for (String string : photo1) {
			if(!photo2.contains(string)) {
				difference.add(string);
			}
		}
		return difference;
		
	}
}
