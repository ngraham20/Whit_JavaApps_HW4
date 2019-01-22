import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class WebNodeTest {

    public static void main(String[] args)
    {
        WebNodeTest test = new WebNodeTest();
    }

    WebNodeTest()
    {
        Crawler crawler = new Crawler();

        WebNode root = new WebNode("https://www.whitworth.edu/cms/");

        try {
            crawler.BFSNodeSearch(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<WebNode> nodes = crawler.getVisitedNodes();

        try {
            ArrayList<String> results = crawler.scrape("[a-zA-Z\\-_.1-9]+@[a-zA-Z.1-9]+");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
