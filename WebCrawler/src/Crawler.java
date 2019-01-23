/**
 * Project: WebCrawler
 * Package: PACKAGE_NAME
 */

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Crawler Object
 * @author Nathaniel Graham
 * @version 1.0
 */
public class Crawler implements Runnable {

    private int numSitesVisited;
    public static ArrayList<WebNode> visitedNodes = new ArrayList<>();

    /** Default Constructor */
    Crawler()
    {
        WebNode.setTotal_nodes(0);
    }

    /** Unused function */
    @Override
    public void run() {

    }

    /**
     * Scrapes all nodes, using a given expression
     * @param expression the expression to scrape with
     * @return all strings returned by the regular expression
     */
    public ArrayList<String> scrape(String expression) {
        ArrayList<String> results = new ArrayList<>();
        for(WebNode node : visitedNodes)
        {
            results.addAll(node.scrapeNodeFor(expression));
        }
        return results;
    }

    /**
     * Clears the visited nodes list
     */
    private void resetVisited()
    {
        WebNode.setTotal_nodes(0);
        //visited = new HashMap<>();
        visitedNodes = new ArrayList<>();
    }

    /**
     * A Breadth-First-Search to generate and link websites together in an abstract tree
     * @param rootNode the node to begin the tree
     */
    public void BFSNodeSearch(WebNode rootNode) {
        resetVisited();
        WebNode.incrementNodeCount(); // account for rootnode
        LinkedList<WebNode> currentLayer = new LinkedList<>();
        currentLayer.add(rootNode);
        //visited.put(rootNode, true);
        visitedNodes.add(rootNode);
        while (currentLayer.size() > 0)
        {
            WebNode currentNode = currentLayer.pop();
            currentNode.findUrls();
            for (WebNode node : currentNode.getChildren())
            {
                //visited.put(node, true);
                visitedNodes.add(node);
                currentLayer.add(node);
            }
        }
    }

    /**
     * Returns the visited nodes
     * @return the visited nodes
     */
    public ArrayList<WebNode> getVisitedNodes() {
        return visitedNodes;
    }
}
