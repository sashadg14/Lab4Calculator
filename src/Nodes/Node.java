package Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by alex o n 10.06.2017.
 */
public interface Node{
    public double getValue();
    public String getAllExpression();
    public DefaultMutableTreeNode getLeaf();
}
