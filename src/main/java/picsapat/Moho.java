package picsapat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Moho {
	private List<Slide> slides;
	private Graph graph;
	
	public Moho(List<Slide> slides) {
		this.slides = slides;
		this.graph = new Graph(slides);
	}
	
	
	public List<Slide> getSlideShow(){
		System.out.println("phase 1");
		//phase 1
		
		Collections.sort(slides, new Comparator<Slide>() {

			@Override
			public int compare(Slide o1, Slide o2) {
				return Integer.compare(o2.getTags().size(),o1.getTags().size());
			}
		});
		
		for (int i = 0; i < slides.size(); i++) {
			if(!slides.get(i).hasTwoNeighbours()) {
				Slide bestNeighbour = getBestNeighbour(i);
				
				if(bestNeighbour == null) break;
				
				slides.get(i).addNeighbour(bestNeighbour);
				if(i%1000 == 0)
					System.out.println("slide done" + i);
			}
			
		}
		System.out.println("phase 2");
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
			//System.out.println("asd");
			if(slide.id != 0 && !slide.hasTwoNeighbours()) {
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
			if(!slides.get(i).hasTwoNeighbours() && !slides.get(i).inSameTree(current) && i != index) {				
	
				int weight = graph.getWeight(index, i);
				if(weight > maxScore) {
					maxSlide = slides.get(i);
					maxScore = weight;
				}
				
				if(maxScore >= Math.max((current.getTags().size()/4.2),1))
					break;
			}
		
		}
		return maxSlide;
	}
}
