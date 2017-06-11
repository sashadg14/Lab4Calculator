import Nodes.OperationNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex o n 11.06.2017.
 */
public class NodeHandler {
    List<OperationNode> operationNodeList;
    int noOfOperation =-1;
    public NodeHandler(){
        operationNodeList= new LinkedList<>();
        operationNodeList.add(new OperationNode(null,"",null,0));
    }

    public void NextStep(){
        if(noOfOperation+1<operationNodeList.size()){
        noOfOperation++;
        operationNodeList.get(noOfOperation).setAsLeaf(true);
        }
    }
    public void PreviousStep(){
        if(noOfOperation>=0){
        operationNodeList.get(noOfOperation).setAsLeaf(false);
        noOfOperation--;
        }
    }

    public List<OperationNode> getOperationNodeList() {
        return operationNodeList;
    }

    public void setOperationNodeList(List<OperationNode> operationNodeList) {
        this.operationNodeList = operationNodeList;
    }
}
