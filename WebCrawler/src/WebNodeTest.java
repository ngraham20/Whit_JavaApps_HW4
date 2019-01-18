import java.util.ArrayList;

public class WebNodeTest {

    public static void main(String[] args)
    {
        WebNodeTest test = new WebNodeTest();
    }

    WebNodeTest()
    {
        Crawler crawler = new Crawler();

        WebNode root = new WebNode("root");
        WebNode node1 = new WebNode("node1");
        WebNode node2 = new WebNode("node2");
        WebNode node3 = new WebNode("node3");
        WebNode node4 = new WebNode("node4");
        WebNode node5 = new WebNode("node5");
        WebNode node6 = new WebNode("node6");
        WebNode node7 = new WebNode("node7");
        WebNode node8 = new WebNode("node8");
        WebNode node9 = new WebNode("node9");
        WebNode node10 = new WebNode("node10");
        WebNode node11 = new WebNode("node11");
        WebNode node12 = new WebNode("node12");

        root.addChild(node1);
        root.addChild(node2);
        root.addChild(node3);

        node1.addChild(node4);
        node1.addChild(node5);
        node1.addChild(node6);

        node2.addChild(node7);
        node2.addChild(node8);

        node3.addChild(node9);

        node4.addChild(node10);
        node4.addChild(node11);

        node5.addChild(node12);

        crawler.BFSNodeSearch(root);
        ArrayList<WebNode> nodes = crawler.getVisitedNodes();
    }
}
