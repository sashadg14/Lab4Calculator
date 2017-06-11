/**
 * Created by alex o n 11.06.2017.
 */
public class EntryPoint {
    public static void main(String[] args){
        String exp = "7+(5-2*(3-4))-(2*2-4*(4-3!))";
        //System.out.println(eval(exp));
        //System.out.println(eval("(4-1)!"));

        new CalculatorWindow(new Calc().eval(exp));

    }
}
