package byu.drautb.ldshymnbook;

/**
 * Hymn class, represents a song that can be viewed.
 * 
 * @author Ben Draut
 * @date 12/24/12
 *
 */
public class Hymn {

	private int number;
	
	private String name;
	
	private String pdfName;
	
	public Hymn(int number, String name, String pdfName) {
		this.number = number;
		this.name = name;
		this.pdfName = pdfName;
	}
	
	public String toString() {
		return number + " - " + name;
	}
	
	public int number() {
		return number;
	}
	
	public String name() {
		return name;
	}
	
	public String pdfName() {
		return pdfName;
	}
}
