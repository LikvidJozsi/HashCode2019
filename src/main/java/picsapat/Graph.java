package picsapat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Graph {
	private List<List<Integer>> matrix;
	
	public Graph(List<Slide> slides ) {
		int numOfVertexes = slides.size();
		matrix = new ArrayList<List<Integer>>(numOfVertexes);
		for (int i = 0; i < numOfVertexes; i++) {
			List<Integer> row = new ArrayList<Integer>(i+1);
			for (int j = 0; j < i; j++) {
				row.add(calculateTransitionsScore(slides.get(i), slides.get(j)));
			}
			matrix.add(row);
		}
	}
	
	private Integer calculateTransitionsScore(Slide slide1, Slide slide2) {
		Set<String> tagList1 = slide1.getTags();
		Set<String> tagList2 = slide2.getTags();
		return Math.min(TagUtils.intersect(tagList1, tagList2).size(), 
				Math.min(TagUtils.difference(tagList1, tagList2).size(), TagUtils.difference(tagList2, tagList1).size()));
	}
	
	public int getWeight(int i, int j) {
		if(i == j)
			return -1;
		int row = Math.max(i, j);
		int column = Math.min(i, j);
		return matrix.get(row).get(column);
	}
	
	public void print() {
		for (List<Integer> list : matrix) {
			String toPrint = "";
			for (Integer integer : list) {
				toPrint += " " + integer;
			}
			System.out.println(toPrint);
		}
	}
}
