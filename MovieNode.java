/**
 * 
 */
package MovieTreePackage;



/**
 * @author ahmed
 *
 */
public class MovieNode {

	public String title; 
	public int releaseYear; 
	MovieNode leftChild;
	MovieNode rightChild;
	
	public MovieNode(int releaseYear, String title ) {
		// TODO Auto-generated constructor stub
		this.releaseYear = releaseYear;
	    this.title = title;
	    this.leftChild = null;
	    this.rightChild= null;
	}
	
	@Override
    public String toString() { 
        return "Title:" + this.title + " Release Year:" + this.releaseYear; 
    }

}
