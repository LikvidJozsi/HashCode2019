package picsapat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Graph {
	private List<List<Integer>> matrix;
	private List<Slide> slides;
	
	public Graph(List<Slide> slides ) {
		int numOfVertexes = slides.size();
		this.slides = slides;
		//matrix = new ArrayList<List<Integer>>(numOfVertexes);
		/*for (int i = 0; i < numOfVertexes; i++) {
			List<Integer> row = new ArrayList<Integer>(i+1);
			for (int j = 0; j < i; j++) {
				row.add(calculateTransitionsScore(slides.get(i), slides.get(j)));
			}
			System.out.println("sor kész:" + i);
			matrix.add(row);
		}*/
	}
	
	
	public int getWeight(int i, int j) {
		if(i == j)
			return -1;
		return TagUtils.getScore(slides.get(i).getTags(), slides.get(j).getTags());
		/*int row = Math.max(i, j);
		int column = Math.min(i, j);
		return matrix.get(row).get(column);*/
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
