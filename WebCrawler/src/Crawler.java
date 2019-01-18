/**
 * Project: WebCrawler
 * Package: PACKAGE_NAME
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Crawler Object
 * @author Nathaniel Graham
 * @version 1.0
 */
public class Crawler implements Runnable {

    private int numSitesVisited;
    private ArrayList<WebNode> visitedNodes = new ArrayList<>();

    Crawler()
    {
        numSitesVisited = 0;
    }

    @Override
    public void run() {

    }

    private void resetVisited()
    {
        numSitesVisited = 0;
    }

    public void BFSNodeSearch(WebNode rootNode)
    {
        resetVisited();
        LinkedList<WebNode> currentLayer = new LinkedList<>();
        currentLayer.add(rootNode);
        visitedNodes.add(rootNode);
        while (numSitesVisited <= WebNode.MAX_SITES && currentLayer.size() > 0)
        {
            WebNode currentNode = currentLayer.pop();
            for (WebNode node : currentNode.getChildren())
            {
                numSitesVisited ++;
                visitedNodes.add(node);
                if(numSitesVisited == WebNode.MAX_SITES)
                {
                    break;
                }
                currentLayer.add(node);
            }
        }
    }

    public ArrayList<WebNode> getVisitedNodes() {
        return visitedNodes;
    }
}
