package picsapat;

import java.util.List;
import java.util.Set;

import javax.imageio.ImageTypeSpecifier;

public abstract class Slide {
	
	public int id = -1;
	
	public static int seq = 0;
	
	public Slide neighbour1 = null;
	public Slide neighbour2 = null;
	
	public abstract Tag[] getTags();
	
	
	public boolean hasTwoNeighbours() {
		return neighbour1 != null && neighbour2 != null;
	}
	
	public void updateId(int newid, Slide from) {

		this.id = newid;
		if(neighbour1 != null && neighbour1 != from)
			 neighbour1.updateId(newid,this);
		if(neighbour2 != null && neighbour2 != from)
			neighbour2.updateId(newid,this);
	}
	
	private void storeNeighbour(Slide slide) {
		if(neighbour1 == null) {
			neighbour1 = slide;
		}else if(neighbour2 == null) {
			neighbour2 = slide;
		}else {
			System.out.println("CSak ketto neighbour lehet");
		}
	}
	
	public void addNeighbour(Slide slide) {
		if(this == slide)
			System.out.println("gratzzz");
		
		if(slide.id != -1 && slide.id == this.id)
			System.out.println("hat ezt elkurtad pityu");
		
		
		if(this.id == -1 && slide.id == -1) {
			int newId = seq++;
			this.id = newId;
			slide.id = newId;
		} else if (slide.id == -1) {
			slide.id = this.id;
		} else if (this.id == -1) {
			this.id = slide.id;
		} else {
			if(this.id < slide.id) {
				slide.updateId(this.id,null);
			}else {
				this.updateId(slide.id,null);
			}
		}
		storeNeighbour(slide);
		slide.storeNeighbour(this);

		
	}
	
	private Slide getEnd(Slide from) {
		if(neighbour1 == null || neighbour2 == null)
			return this;
		if(neighbour1 != from) {
			return neighbour1.getEnd(this);
		}
		if(neighbour2 != from) {
			return neighbour2.getEnd(this);
		}
		return null;
	}
	
	public Slide getEnd1() {
		if(neighbour1 == null)
			return this;
		
		return neighbour1.getEnd(this);
	}
	
	public Slide getEnd2() {
		if(neighbour2 == null)
			return this;
		
		return neighbour2.getEnd(this);
	}
	
	public boolean inSameTree(Slide slide) {
		return slide.id != -1 && this.id == slide.id;
	}
	
	private void getPath(Slide from,List<Slide> path ) {
		path.add(this);
		if(neighbour1 != from && neighbour1 != null)
			neighbour1.getPath(this,path);
		if(neighbour2 != from && neighbour2 != null)
			neighbour2.getPath(this,path);
	}
	
	
	public List<Slide> getPath(List<Slide> path){
		path.add(this);
		if(neighbour1 != null)
			neighbour1.getPath(this,path);
		if(neighbour2 != null)
			neighbour2.getPath(this,path);
		return path;
	}
}
