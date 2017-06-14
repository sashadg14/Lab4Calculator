import Nodes.Node;
import Nodes.NumericNode;
import Nodes.OperationNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Calc {
     private int sch=0;
     private final List<OperationNode> operationNodeList;
     public Calc(){
         operationNodeList= new ArrayList<>();
     }
     private boolean isDelim(String c) {
        return c.equalsIgnoreCase(" ");
    }
     private boolean isOperator(String c) {
        return c.equalsIgnoreCase("+") || c.equalsIgnoreCase("-") ||
                c.equalsIgnoreCase("*") || c.equalsIgnoreCase("/") || c.equalsIgnoreCase("%")||c.equalsIgnoreCase("!")||c.equalsIgnoreCase("t");
    }
     private int priorityOfOperation(String op) {
         switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            case "!":
            case "t":
                return 4;
            default:
                return 0;
        }
    }
     private void calculatingOperator(LinkedList<Node> st, String op) {
        sch++;
        OperationNode operationNode = null;
        if(op.equalsIgnoreCase("!")) {
            Node r = st.removeLast();
            int result=1;
            for (int i=1;i<=r.getValue();i++)
                result*=i;
            operationNode=new OperationNode(r,"!",r,result);
            operationNodeList.add(operationNode);
            st.add(operationNode);
             //System.out.println("adsasda "+result);
        }else if(op.equalsIgnoreCase("t")){
            Node r = st.removeLast();
            operationNode=new OperationNode(r,"sqrt",r,Math.sqrt(r.getValue()));
            operationNodeList.add(operationNode);
            st.add(operationNode);
        }else {
            Node r = st.removeLast();
            Node l = st.removeLast();
            switch (op) {
                case "+":
                    operationNode = new OperationNode(l, "+", r, l.getValue() + r.getValue());
                    break;
                case "-":
                    operationNode = new OperationNode(l, "-", r, l.getValue() - r.getValue());
                    break;
                case "*":
                    operationNode = new OperationNode(l, "*", r, l.getValue() * r.getValue());
                    break;
                case "/":
                    operationNode = new OperationNode(l, "/", r, l.getValue() / r.getValue());
                    break;
                case "%":
                    //st.add(new Nodes.OperationNode(l,"%",r,l.getValue() + r.getValue())); проценты над добавить БУДЕТ TODO:
                    break;
            }
            st.add(operationNode);
            operationNodeList.add(operationNode);
            System.out.println(sch + ") " + st.getLast().getTotalExpression());
        }
    }

    public List<OperationNode> eval(String s) {
        LinkedList<Node> st = new LinkedList<>();
        LinkedList<String> op = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            String c =""+s.charAt(i);
            if(c.equalsIgnoreCase("s")||c.equalsIgnoreCase("q")||c.equalsIgnoreCase("r")||c.equalsIgnoreCase("l")||c.equalsIgnoreCase("o"))
                continue;
            System.out.println(c);
            if (isDelim(c))
                continue;
            if (c.equalsIgnoreCase("("))
                op.add("(");
            else if (c.equalsIgnoreCase(")")) {
                while (!op.getLast().equalsIgnoreCase("("))
                    calculatingOperator(st, op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priorityOfOperation(op.getLast()) >= priorityOfOperation(c))
                    calculatingOperator(st, op.removeLast());
                op.add(c);
            } else {
                String operand = "";
                while (i < s.length() && (Character.isDigit(s.charAt(i))||String.valueOf(s.charAt(i)).equalsIgnoreCase(".")))
                    operand += s.charAt(i++);
                --i;
                st.add(new NumericNode(Double.parseDouble(operand)));
            }
        }
        while (!op.isEmpty())
            calculatingOperator(st, op.removeLast());
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
