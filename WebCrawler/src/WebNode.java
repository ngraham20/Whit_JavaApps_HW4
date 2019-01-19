/**
 * Project: WebCrawler
 * Package: PACKAGE_NAME
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * WebNode Object
 * @author Nathaniel Graham
 * @version 1.0
 */
public class WebNode {

    private String url;
    private ArrayList<WebNode> children;

    public static final int MAX_SITES = 500;
    private static int total_nodes = 0;

    WebNode(String url)
    {
        this.url = url;
        this.children = new ArrayList<>();

        //Crawler.visited.put(this, true);
    }

    public static void incrementNodeCount()
    {
        total_nodes++;
    }

    public static int getTotal_nodes()
    {
        return WebNode.total_nodes;
    }

    public static void setTotal_nodes(int num)
    {
        WebNode.total_nodes = num;
    }


    public ArrayList<WebNode> getChildren() {
        return children;
    }

    public void addChild(WebNode child)
    {
        children.add(child);
    }

    public String getUrl() {
        return url;
    }

    public boolean findUrls() throws IOException {
        if(total_nodes >= MAX_SITES)
        {
            return true;
        }
        // regex for link href in html
        Pattern pattern = Pattern.compile("<.*?href=\"(https:.*?)\"");

        URL _url = new URL(this.url);

        try {
            BufferedReader rdr = new BufferedReader(new InputStreamReader(_url.openStream()));
            String html = rdr.lines().collect(Collectors.joining());
            Matcher matcher = pattern.matcher(html);

            System.out.println("Current Site: " + this.url);
            while (total_nodes < MAX_SITES && matcher.find()) {
                String url = matcher.group(1);
                if(url.contains(".css"))
                {
                    continue;
                }
                if(url.equals(this.url))
                {
                    continue;
                }
                WebNode newNode = new WebNode(url);
                if(Crawler.visited.get(newNode) != null) // this node exists. Go find a new site to scan
                {
                    continue;
                }
                System.out.println("Site(" + Integer.toString(WebNode.total_nodes +1) +") Found: " + url);
                WebNode.incrementNodeCount();
                children.add(new WebNode(url));
            }
        }
        catch(IOException e)
        {
            System.out.println("Site Redirected too many times. Skipping. . .");
            return false;
        }
        System.out.println();
        return true;
    }
}
