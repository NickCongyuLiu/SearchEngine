/*Congyu Liu C11305457*/

package prog12;

import java.util.*;

public class MyGoogle implements Google {
  /** Map from String url to int id, where ids are assigned
      consecutively as pages are discovered. */
  private Map<String, Integer> urlToID = new TreeMap<String, Integer>();
  
  /** Number of references (links from other pages) to each page,
      indexed by page id. */
  private List<Integer> numRefs = new ArrayList<Integer>();
  
  /** Map from in id to String url. */
  private List<String> idToURL = new ArrayList<String>();
  
  /** Add a new page to the preceeding three structures. */
  private void addPage (String url) {
    urlToID.put(url, urlToID.size());
    numRefs.add(0);
    idToURL.add(url);
  }	
  
  /** Map from String word to int id, where ids are assigned
      consecutively as words are discovered. */
  private Map<String, Integer> wordToID = new HashMap<String, Integer>();
  
  /** List of lists, indexed by word id.  Each list is the set of
      page ids of pages which contain the word with that word id.
      The page ids are stored in increasing numerical order. */
  private List<List<Integer>> wordPageIDs = new ArrayList<List<Integer>>();
  
  /** Add a new word to the preceeding two structures. */
  private void addWord (String word) {
    wordToID.put(word, wordToID.size());
    wordPageIDs.add(new ArrayList<Integer>());
  }
  
  /** Visit every page reachable from the pages with urls in
      startingURLs.  If the page has not been seen, record all the
      relevant information in the five data structures.
      @param browser a Browser to view the internet
      @param startingURLs web pages to start from
  */
  public void gather (Browser browser, List<String> startingURLs) {
    Queue<String> urlQueue = new ArrayDeque<String>();
    
    // Put unknown pages into the queue, but don't look at them yet.
    for (String url : startingURLs)
      if (!urlToID.containsKey(url)) {
        addPage(url);
        urlQueue.offer(url);
      }
    
    // Look at each page in the queue.
    int count = 0;
    while (!urlQueue.isEmpty() && count++ < 100) {
      String url = urlQueue.poll();
      System.out.println("dequeued " + url);
      if (browser.loadPage(url)) {
        // List of urls on this page, WITH duplications.
        List<String> urlsOnPage = browser.getURLs();

        // Set of page ids of these urls, WITHOUT duplications.
	Set<Integer> pageIDsOnPage = new HashSet<Integer>();

        for (String urlOnPage : urlsOnPage) {
          // Put links to unknown pages into the queue.
          if (!urlToID.containsKey(urlOnPage)) {
            addPage(urlOnPage);
            urlQueue.offer(urlOnPage);
          }

          // Get the id of a url linked from the current page.
          int pageIDOnPage = urlToID.get(urlOnPage);

          // If this is the first appearance, increment its numRefs.
	  if (!pageIDsOnPage.contains(pageIDOnPage)) {
	      pageIDsOnPage.add(pageIDOnPage);
	      numRefs.set(pageIDOnPage, numRefs.get(pageIDOnPage) + 1);
              // System.out.println("new link to " + urlOnPage);
          }
        }
        
        // The id of the current page.
        int pageID = urlToID.get(url);

        // The words on this page.
        List<String> words = browser.getWords();
        for (String word : words) {
          // Record new words.
          if (!wordToID.containsKey(word))
            addWord(word);
          
          // Add the page id of this page to the end of the
          // list of page ids for this word, but do it only
          // once even if this word appears multiple times
          // on this page.
          int wordID = wordToID.get(word);
          List<Integer> list = wordPageIDs.get(wordID);
          if (list.isEmpty() || list.get(list.size()-1) != pageID)
            list.add(pageID);
        }
      }
    }

    if (true) {
      System.out.println("urlToID:");
      System.out.println(urlToID);
      System.out.println("idToURL:");
      System.out.println(idToURL);
      System.out.println("numRefs:");
      System.out.println(numRefs);
      System.out.println("wordToID:");
      System.out.println(wordToID);
      System.out.println("wordPageIDs:");
      System.out.println(wordPageIDs);
    }
  }
  
  /** Return an array of up to numResults urls of pages containing
      all words in keyWords.  Array is ordered by decreasing number
      of references.
      @param keyWords list of words to look for
      @param numResults maximum number of pages to return
      @return list of URLs of pages containing all words in keyWords
  */
  public String[] search (List<String> keyWords, int numResults) {
    // Iterator into list page ids for each key word.
    Iterator<Integer>[] pageIDiterators =
      (Iterator<Integer>[]) new Iterator[keyWords.size()];
    
    String[] results = new String[0];
    return results;
  }
}
