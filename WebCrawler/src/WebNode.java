/**
 * Project: WebCrawler
 * Package: PACKAGE_NAME
 */

import java.util.ArrayList;

/**
 * WebNode Object
 * @author Nathaniel Graham
 * @version 1.0
 */
public class WebNode {

    private String url;
    private boolean visited;
    private ArrayList<WebNode> children;
    private WebNode parent;

    public static final int maxSites = 100;

    WebNode(String url)
    {
        this.url = url;
        this.visited = false;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public ArrayList<String> findUrls(WebNode rootNode)
    {
        ArrayList<String> completeList = new ArrayList<>();
        for (WebNode node : children)
        {
            completeList.addAll(findUrls(node));
        }
        return completeList;
    }
}
