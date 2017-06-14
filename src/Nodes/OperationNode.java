package Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by alex o n 11.06.2017.
 */
public class OperationNode implements Node {
    private final Node first;
    private final String operation;
    private final Node second;
    private final double value;
    private final DefaultMutableTreeNode leaf;
    private String allExpression;
    public OperationNode(Node first,String operation,Node second,double value){
        this.first=first;
        this.second=second;
        this.operation=operation;
        this.value=value;
        leaf = new DefaultMutableTreeNode(operation);
        if(first!=null&&second!=null) {
            leaf.add(first.getLeaf());
            leaf.add(second.getLeaf());
        }
    }
    @Override
    public double getValue() {
        return value;
    }
    public String getTotalExpression(){
        if(operation.equalsIgnoreCase("!"))
        return String.valueOf(first.getValue()+operation).replace(".0","");
        if(operation.equalsIgnoreCase("sqrt")||operation.equalsIgnoreCase("ln")||operation.equalsIgnoreCase("log"))
        return String.valueOf(operation+"("+first.getValue()+")").replace(".0","");
        return String.valueOf(first.getValue()+operation+second.getValue()).replace(".0","");
    }

    @Override
    public DefaultMutableTreeNode getLeaf() {
    return leaf;
    }

    public void setAsLeaf(boolean isList){
        if(isList){
            leaf.removeAllChildren();
            leaf.setUserObject(String.valueOf(value));
        }else {
            leaf.add(first.getLeaf());
            leaf.add(second.getLeaf());
            leaf.setUserObject(operation);
            //System.out.println(first.getLeaf()+" "+second.getLeaf());
        }
    }

    public String getAllExpression() {
        return allExpression;
    }

    public void setAllExpression(String allExpression) {
        this.allExpression = allExpression;
    }
}
