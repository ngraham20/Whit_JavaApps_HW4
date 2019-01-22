/**
 * Project: WebCrawler
 * Package: PACKAGE_NAME
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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
    private String html;
    private ArrayList<WebNode> children;
    private BufferedReader rdr;

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

    public String getHtml()
    {
        return this.html;
    }

    public ArrayList<String> scrapeNodeFor(String expression) throws IOException {
        ArrayList<String> results = new ArrayList<>();
        BufferedReader rdr = new BufferedReader(new InputStreamReader((new URL(this.url).openStream())));
        this.html = rdr.lines().collect(Collectors.joining());

        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(this.html);
        System.out.println("Scraping site: " + this.url);
        while(matcher.find())
        {
            results.add(matcher.group(1));
        }

        return results;
    }

    public boolean findUrls() {
        if(total_nodes >= MAX_SITES)
        {
            return true;
        }

        try {
            BufferedReader rdr = new BufferedReader(new InputStreamReader(new URL(this.url).openStream()));
            this.html = rdr.lines().collect(Collectors.joining());

            // regex for link href in html
            Pattern pattern = Pattern.compile("<.*?href=\"(https:.*?)\"");
            Matcher matcher = pattern.matcher(this.html);

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
                if(Crawler.visitedNodes.contains(newNode)) // this node exists. Go find a new site to scan
                {
                    continue;
                }
                System.out.println("Site(" + (WebNode.total_nodes + 1) + ") Found: " + url);
                WebNode.incrementNodeCount();
                addChild(new WebNode(url));
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
