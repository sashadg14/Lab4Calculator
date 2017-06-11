/**
 * Created by alex o n 10.06.2017.
 */

import Nodes.Node;
import Nodes.NumericNode;
import Nodes.OperationNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Calc {
     int sch=0;
     private List<OperationNode> operationNodeList;
     public Calc(){
         operationNodeList= new ArrayList<>();
     }
     boolean isDelim(char c) {
        return c == ' ';
    }
     boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%'||c=='!';
    }
     int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '!':
                return 3;
            default:
                return -1;
        }
    }
     void processOperator(LinkedList<Node> st, char op) {
        sch++;
        OperationNode operationNode = null;
        if(op!='!') {
        Node r = st.removeLast();
        Node l = st.removeLast();
        switch (op) {
            case '+':
                operationNode=new OperationNode(l,"+",r,l.getValue() + r.getValue());
                break;
            case '-':
                operationNode=new OperationNode(l,"-",r,l.getValue() - r.getValue());
                break;
            case '*':
                operationNode=new OperationNode(l,"*",r,l.getValue() * r.getValue());
                break;
            case '/':
                operationNode=new OperationNode(l,"/",r,l.getValue() / r.getValue());
                break;
            case '%':
                //st.add(new Nodes.OperationNode(l,"%",r,l.getValue() + r.getValue())); проценты над добавить БУДЕТ TODO:
                break;
        }
        }
        else {
            Node r = st.removeLast();
            int result=1;
            for (int i=1;i<=r.getValue();i++)
                result*=i;
            operationNode=new OperationNode(r,"!",r,result);
           // System.out.println(r.getClass().getName());
        }
        st.add(operationNode);
        operationNodeList.add(operationNode);
        System.out.println(sch+") "+st.getLast().getAllExpression());
    }

    public List<OperationNode> eval(String s) {
        LinkedList<Node> st = new LinkedList<Node>();
        LinkedList<Character> op = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isDelim(c))
                continue;
            if (c == '(')
                op.add('(');
            else if (c == ')') {
                while (op.getLast() != '(')
                    processOperator(st, op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                    processOperator(st, op.removeLast());
                op.add(c);
            } else {
                String operand = "";
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    operand += s.charAt(i++);
                --i;
                st.add(new NumericNode(Integer.parseInt(operand)));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        return operationNodeList;
    }
   /* public  void main(String[] args) throws Exception {
        String exp = "7+(5-2*(3-4))-(2*2-4*(4-3!))";
        //System.out.println(eval(exp));
        //System.out.println(eval("(4-1)!"));
        nodeHandler= new NodeHandler();
        new CalculatorWindow(eval(exp).getLeaf());

    }*/
}
