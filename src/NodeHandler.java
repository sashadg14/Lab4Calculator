import Nodes.OperationNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex o n 11.06.2017.
 */
public class NodeHandler {
    private List<OperationNode> operationNodeList;
    private String expression;
    private int noOfOperation =0;
    private List<String> arrayOfStatesOfExpression;
    public NodeHandler(){
        operationNodeList= new LinkedList<>();
        arrayOfStatesOfExpression = new ArrayList<>();
        operationNodeList.add(new OperationNode(null,"",null,0));
    }

    public void setExpression(String expression) {
        this.expression = expression;
       // operationNodeList.get(0).setAllExpression(expression);
        calculateArrayOfStatesOfExpression(expression);
    }

    public String NextStep(){
        if(noOfOperation+1<=operationNodeList.size()){
        operationNodeList.get(noOfOperation).setAsLeaf(true);
            //if(noOfOperation+1<=operationNodeList.size())
            noOfOperation++;
        }
        return arrayOfStatesOfExpression.get(noOfOperation);
    }
    public String PreviousStep(){
        if(noOfOperation>=0){
            if(noOfOperation>0)
            noOfOperation--;
        operationNodeList.get(noOfOperation).setAsLeaf(false);

        }
        return arrayOfStatesOfExpression.get(noOfOperation);
    }

    public List<OperationNode> getOperationNodeList() {
        return operationNodeList;
    }

    private void calculateArrayOfStatesOfExpression(String expression) {
        arrayOfStatesOfExpression.clear();
        arrayOfStatesOfExpression.add(expression);
        for (int i=0;i<operationNodeList.size();i++) {
            expression=changeExpressionToNextStep(operationNodeList.get(i),expression);
             arrayOfStatesOfExpression.add(expression);
        }
        for (String string: arrayOfStatesOfExpression){
            System.out.println("-------"+string);
        }

    }

    public void setOperationNodeList(List<OperationNode> operationNodeList) {
        this.operationNodeList = operationNodeList;
    }

    private String changeExpressionToNextStep(OperationNode operationNode, String expression){
        System.out.println(operationNode.getTotalExpression()+"-->"+String.valueOf(operationNode.getValue()).replace(".0",""));
        return expression.replace(operationNode.getTotalExpression(), String.valueOf(operationNode.getValue()).replace(".0",""));
    }
}
