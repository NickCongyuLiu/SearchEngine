/*Congyu Liu  C11305457*/

package prog12;

import java.util.List;

public interface Browser {
	/**
	 * This is the comments for the loadPage method.
	 * @param url This is the first param
	 * @return
	 */
    boolean loadPage (String url);
    List<String> getWords ();
    List<String> getURLs ();
}

