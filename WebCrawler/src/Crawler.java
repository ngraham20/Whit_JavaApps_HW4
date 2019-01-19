/**
 * Project: WebCrawler
 * Package: PACKAGE_NAME
 */

import java.io.IOException;
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
    private ArrayList<WebNode> visitedNodes = new ArrayList<>();
    public static HashMap<WebNode, Boolean> visited = new HashMap<>();

    Crawler()
    {
        WebNode.setTotal_nodes(0);
    }

    @Override
    public void run() {

    }

    public ArrayList<String> scrape(String expression)
    {
        return new ArrayList<>();
    }

    private void resetVisited()
    {
        WebNode.setTotal_nodes(0);
        visited = new HashMap<>();
        visitedNodes = new ArrayList<>();
    }

    public void BFSNodeSearch(WebNode rootNode) throws IOException {
        resetVisited();
        WebNode.incrementNodeCount(); // account for rootnode
        LinkedList<WebNode> currentLayer = new LinkedList<>();
        currentLayer.add(rootNode);
        visited.put(rootNode, true);
        visitedNodes.add(rootNode);
        while (currentLayer.size() > 0)
        {
            int numNodes = WebNode.getTotal_nodes();
            WebNode currentNode = currentLayer.pop();
            currentNode.findUrls();
            for (WebNode node : currentNode.getChildren())
            {
                visited.put(node, true);
                visitedNodes.add(node);
                currentLayer.add(node);
            }
        }
    }

//    public void DFSNodeSearch(WebNode rootnode)
//    {
//        visitedNodes.add(rootnode);
//
//        if (numSitesVisited == WebNode.MAX_SITES)
//        {
//            return;
//        }
//
//        for(WebNode node : rootnode.getChildren())
//        {
//            visitedNodes.add(node);
//        }
//    }

    public HashMap<WebNode, Boolean> getVisitedNodes() {
        return visited;
    }
}
