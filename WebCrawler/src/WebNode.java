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
    private ArrayList<String> urls;

    public static final int MAX_SITES = 100;

    WebNode(String url)
    {
        this.url = url;
        this.visited = false;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public ArrayList<WebNode> getChildren() {
        return children;
    }

    public void addChild(WebNode child)
    {
        children.add(child);
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setVisited(boolean arg)
    {
        this.visited = arg;
    }
}
