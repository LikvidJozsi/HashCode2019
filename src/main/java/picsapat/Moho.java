package picsapat;

import java.util.ArrayList;
import java.util.List;

public class Moho {
	private List<Slide> slides;
	private Graph graph;
	
	public Moho(List<Slide> slides) {
		this.slides = slides;
		this.graph = new Graph(slides);
	}
	
	
	public List<Slide> getSlideShow(){
		
		//phase 1
		for (int i = 0; i < slides.size(); i++) {
			if(!slides.get(i).hasTwoNeighbours()) {
				Slide bestNeighbour = getBestNeighbour(i);
				slides.get(i).addNeighbour(bestNeighbour);
			}
			
		}
		//phase 2
		Slide end1 = null;
		Slide end2 = null;
		for (Slide slide : slides) {
			if(slide.id == 0) {
				end1 = slide.getEnd1();
				end2 = slide.getEnd2();
				break;
			}
		}
		
		for (Slide slide : slides) {
			if(slide.id != 0) {
				end1.addNeighbour(slide);
				end2 = end1.getEnd2();
				end1 = end1.getEnd1();
			}
		}
		
		List<Slide> path = new ArrayList<>(slides.size());
		return end1.getPath(path);
	}
	
	
	
	private Slide getBestNeighbour(int index) {
		int maxScore = -1;
		Slide maxSlide = null;
		Slide current = slides.get(index);
		for (int i = 0; i < slides.size(); i++) {
			if(!slides.get(i).hasTwoNeighbours() && slides.get(i).inSameTree(current)) {
				int weight = graph.getWeight(index, i);
				if(weight > maxScore) {
					maxSlide = slides.get(i);
					maxScore = weight;
				}
			}
		
		}
		return maxSlide;
	}
}
