import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 * JTree basic tutorial and example
 * @author wwww.codejava.net
 */
public class TreeExample
{
    private JTree tree;
    JFrame jFrame;

    public TreeExample()
    {   jFrame = new JFrame();
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("Root2");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
        vegetableNode.add(new DefaultMutableTreeNode("Potato"));

        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        fruitNode.add(new DefaultMutableTreeNode("Banana"));
        fruitNode.add(new DefaultMutableTreeNode("Mango"));
        fruitNode.add(new DefaultMutableTreeNode("Apple"));
        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
        fruitNode.add(new DefaultMutableTreeNode("Orange"));
        //add the child nodes to the root node
        root.add(root2);
        root2.add(vegetableNode);
        root2.add(fruitNode);

        //create the tree by passing in the root node
        tree = new JTree(root);
       // ImageIcon imageIcon = new ImageIcon(TreeExample.class.getResource("/leaf.jpg"));
        //DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        //renderer.setLeafIcon(imageIcon);

        //tree.setCellRenderer(renderer);
        tree.setShowsRootHandles(true);
        tree.setRootVisible(false);
        jFrame.add(new JScrollPane(tree));


        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("JTree Example");
        jFrame.setSize(200, 200);
        jFrame.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TreeExample();
            }
        });
    }
}