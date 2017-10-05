/*Congyu Liu  C11305457*/

package prog12;

import java.util.*;

public class Main {
    public static void main (String[] args) {
	Browser browser = new MyBrowser();
	Google google = new MyGoogle();

	List<String> startingURLs = new ArrayList<String>();
	startingURLs.add("http://www.cs.miami.edu");

	google.gather(browser, startingURLs);

	List<String> keyWords = new ArrayList<String>();
	keyWords.add("Victor");
	keyWords.add("Milenkovic");

	String[] urls = google.search(keyWords, 100);

	System.out.println("Found " + keyWords + " on");
	for (int i = 0; i < urls.length; i++)
	    System.out.println(urls[i]);
    }
}
