package picsapat;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.print.attribute.standard.MediaSize.Other;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

public class Tag implements Comparable<Tag>{
	private static Map<String, Integer> mapping = new TreeMap<String, Integer>();
	private static int seq = 0;
	
	public int code;
	
	public Tag(String tag) {
		Integer codeInteger = mapping.get(tag);
		if(codeInteger == null) {
			codeInteger = new Integer(seq++);
			mapping.put(tag, codeInteger);
		}
		this.code = codeInteger;
	}
	
	@Override
	public boolean equals(Object obj) {
		Tag other = (Tag) obj;
		return code  == other.code;
	}

	@Override
	public int compareTo(Tag o) {
		return Integer.compare(code, o.code);
	}
	
	@Override
	public int hashCode() {
		return code;
	}
	
}
