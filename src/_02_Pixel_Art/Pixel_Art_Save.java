package _02_Pixel_Art;

import java.io.Serializable;

public class Pixel_Art_Save implements Serializable{
	
	Pixel[][] grid;
	
	public Pixel_Art_Save (Pixel[][] inGrid) {
		this.grid = inGrid;
	}
}
