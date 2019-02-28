package picsapat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
//		algo("a_example.txt", "out_a.txt");
//		algo("b_lovely_landscapes.txt", "out_b.txt");
		algo("c_memorable_moments.txt", "out_c.txt");
//		algo("d_pet_pictures.txt", "out_d.txt");
//		algo("e_shiny_selfies.txt", "out_e.txt");
		
		//algo("myexample.txt", "out_myex.txt");
	}
	
	public static void algo(String inputFileName, String outputFileName) {
		List<Photo> photos= getInput(new File(inputFileName));

		List<Photo> horizontalPhotos = filterOrientation(photos, Orientation.HORIZONTAL);
		List<Photo> verticalPhotos = filterOrientation(photos, Orientation.VERTICAL);
		List<Slide> horizontalSlides = convertHorizontalToSlides(horizontalPhotos);
		List<Slide> matchedVerticals = matchVerticalsToSlides(verticalPhotos);
		List<Slide> unorderedSlides = createUnorderedSlides(horizontalSlides, matchedVerticals);
				
		Moho m = new Moho(unorderedSlides);
		
		List<Slide> finalSlides = m.getSlideShow();
		
		output(outputFileName, finalSlides);
		
	}


	private static List<Slide> createUnorderedSlides(List<Slide> horizontalSlides, List<Slide> matchedVerticals) {
		List<Slide> slides = new ArrayList<Slide>();
		
		slides.addAll(horizontalSlides);
		slides.addAll(matchedVerticals);
		
		return slides;
	}

	private static List<Slide> convertHorizontalToSlides(List<Photo> horizontalPhotos) {
		List<Slide> slides = new ArrayList<Slide>();
		horizontalPhotos.forEach( (Photo p) -> slides.add(new HorizontalSlide(p)) );
		return slides;
	}


	private static List<Slide> matchVerticalsToSlides(List<Photo> verticalPhotos) {
		List<Slide> verticalSlides = new ArrayList<Slide>();
		
		List<Photo> initialLinked = new LinkedList<Photo>(verticalPhotos);
		
		
		while(initialLinked.size() >= 2) {
			int bestIndex = 1;
			int min = TagUtils.intersect(initialLinked.get(0).tags, initialLinked.get(1).tags).size();
			
			for(int j = 2; j < initialLinked.size(); j++) {
				int intersect = TagUtils.intersect(initialLinked.get(0).tags, initialLinked.get(j).tags).size();

				if(intersect < min) {
					bestIndex = j;
					min = intersect;
				}
				
				if(min == 0) break;
			}
			
			verticalSlides.add(new VerticalSlide(initialLinked.get(0), initialLinked.get(bestIndex)));

			initialLinked.remove(bestIndex);
			initialLinked.remove(0);
		}
		
		//return convertHorizontalToSlides(verticalPhotos);
		return verticalSlides;
	}


	private static List<Photo> filterOrientation(List<Photo> photos, Orientation o) {
		List<Photo> filtered = new ArrayList<Photo>();
		
		for(Photo p : photos) {
			if(p.orientation == o)
				filtered.add(p);
		}
		
		return filtered;
	}
	
	
	public static List<Photo> getInput(File source){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			int numOfPhotos = Integer.parseInt(br.readLine());
			List<Photo> photos = new ArrayList<Photo>(numOfPhotos);
			for (int i = 0; i < numOfPhotos; i++) {
				String[] line = br.readLine().split(" ");
				String orientation = line[0];
				int numOfTags = Integer.parseInt(line[1]);
				
				Photo photo = new Photo(numOfTags);
				if(orientation.equals("H"))
					photo.orientation = Orientation.HORIZONTAL;
				else if (orientation.equals("V"))
					photo.orientation = Orientation.VERTICAL;
				else {
					throw new IOException("valami bűzlik");
				}
				
				for (int j = 0; j < numOfTags; j++) {
					photo.tags.add(line[2+j]);
				}
				photo.id = i;
				photos.add(photo);
			}
			return photos;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void output(String target, List<Slide> slides) {
		try(PrintWriter pw = new PrintWriter(new File(target))) {
			
			pw.println(slides.size());
			slides.forEach( (Slide s) -> pw.println(s.toString()));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
