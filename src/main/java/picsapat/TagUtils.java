package picsapat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.ldap.UnsolicitedNotification;

public class TagUtils {
	public static Tag[] union(Tag[] photo1, Tag[] photo2){
		List<Tag> union = new ArrayList<Tag>(Math.max(photo1.length, photo2.length));
		for (Tag tag : photo1) {
			if(!union.contains(tag)) {
				union.add(tag);
			}
		}
		for (Tag tag : photo2) {
			if(!union.contains(tag)) {
				union.add(tag);
			}
		}
		Tag[] unionArray = new Tag[union.size()];
		return union.toArray(unionArray);
	}
	
	public static List<Tag> intersect(Tag[] photo1, Tag[] photo2){
		ArrayList<Tag> intersect = new ArrayList<Tag>();
		List<Tag> photo2List = Arrays.asList(photo2);
		for (Tag tag : photo1) {
			if(photo2List.contains(tag)) {
				intersect.add(tag);
			}
		}
		return intersect;
	}
	
	private static int getIntersectSize(Tag[] photo1, Tag[] photo2) {
		int intersectSize = 0;
		
		int i = 0;
		int j = 0;
		
		while(i < photo1.length) {
			Tag left = photo1[i];
			while(left.code < photo2[j].code) {
				j++;
				if( j == photo2.length)
					return intersectSize;
			}
			
			if(left.code == photo2[j].code) {
				intersectSize++;
			}
			i++;
		}
		return intersectSize;
	}
	
	public static int  getScore(Tag[] photo1, Tag[] photo2) {
		
		int intersectSize = getIntersectSize(photo1, photo2);
		return Math.min(intersectSize,Math.min(photo1.length-intersectSize,photo2.length-intersectSize));
	}
	
	public static Set<Tag> difference(Set<Tag> photo1, Set<Tag> photo2){
		Set<Tag> difference = new HashSet<Tag>();
		for (Tag tag : photo1) {
			if(!photo2.contains(tag)) {
				difference.add(tag);
			}
		}
		return difference;
		
	}
}
