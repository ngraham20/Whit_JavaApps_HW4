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
    //public static HashMap<WebNode, Boolean> visited = new HashMap<>();

    Crawler()
    {
        WebNode.setTotal_nodes(0);
    }

    @Override
    public void run() {

    }

    public ArrayList<String> scrape(String expression) throws IOException {
        ArrayList<String> results = new ArrayList<>();
        for(WebNode node : visitedNodes)
        {
            results.addAll(node.scrapeNodeFor(expression));
        }
        return results;
    }

    private void resetVisited()
    {
        WebNode.setTotal_nodes(0);
        //visited = new HashMap<>();
        visitedNodes = new ArrayList<>();
    }

    public void BFSNodeSearch(WebNode rootNode) throws IOException {
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

    public ArrayList<WebNode> getVisitedNodes() {
        return visitedNodes;
    }
}
