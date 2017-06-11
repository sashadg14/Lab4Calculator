/**
 * Created by alex o n 10.06.2017.
 */
import java.util.LinkedList;
public class Calc {
    static int sch=0;
    static boolean isDelim(char c) {
        return c == ' ';
    }
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }
    static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
            //case '!':
                return 2;
            default:
                return -1;
        }
    }
    static void processOperator(LinkedList<Node> st, char op) {
        if(op!='!')
        {
        Node r = st.removeLast();
        Node l = st.removeLast();
        sch++;
        //System.out.println(sch+") "+l.getValue()+""+(char)op+""+r.getValue());
        switch (op) {
            case '+':
                st.add(new OperationNode(l,"+",r,l.getValue() + r.getValue(),sch));
                break;
            case '-':
                st.add(new OperationNode(l,"-",r,l.getValue() - r.getValue(),sch));
                break;
            case '*':
                st.add(new OperationNode(l,"*",r,l.getValue() * r.getValue(),sch));
                break;
            case '/':
                st.add(new OperationNode(l,"/",r,l.getValue() / r.getValue(),sch));
                break;
            case '%':
                //st.add(new OperationNode(l,"%",r,l.getValue() + r.getValue())); проценты над добавить БУДЕТ TODO:
                break;
        }
        System.out.println(st.getLast().getNumberOfOperation()+") "+st.getLast().getAllExpression());
        }
    }

    public static double eval(String s) {
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
        return st.get(0).getValue();
    }
    public static void main(String[] args) throws Exception {
        String exp = "7+(5-2*(3-4))-(2*2-4*(4-3))";
        System.out.println(eval(exp));

    }
}
