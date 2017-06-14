import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alex o n 11.06.2017.
 */
public class CalculatorWindow {
    private JTree tree;
    JFrame jFrame;
    JButton leftButton;
    JButton rightButton;
    int buttonSize=60;
    int topMargin=60;
    JScrollPane jScrollPane;
    TextArea inputLabel;
    JTextField ansverLabel;
    NodeHandler nodeHandler;
    DefaultMutableTreeNode rootNode;
    public CalculatorWindow()
    {
        rootNode=new DefaultMutableTreeNode("");
        jFrame = new JFrame();
        nodeHandler= new NodeHandler();
        tree = new JTree(rootNode);

        //rootNode.add(operationNodeList.get(operationNodeList.size()-1).getLeaf());
        jScrollPane = new JScrollPane(tree);
        tree.setShowsRootHandles(true);
        tree.setRootVisible(true);

        inputLabel =new TextArea();
        inputLabel.setBounds(200,0,500,topMargin);
        jFrame.add(inputLabel);

        ansverLabel =new JTextField();
        ansverLabel.setBounds(0,0,200,topMargin-10);
        jFrame.add(ansverLabel);

       /* leftButton = new JButton("<<");
        rightButton= new JButton(">>");*/
        jFrame.add(jScrollPane);
        /*jFrame.add(leftButton);
        jFrame.add(rightButton);*/
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("JTree Example");
        jFrame.setBounds(400, 200,700,400);
        jFrame.setVisible(true);
        jFrame.getContentPane().setLayout(null);
        jScrollPane.setBounds(0,topMargin,200,300);

        createButtons();
        jFrame.update(jFrame.getGraphics());
    }

    void createButtons(){
        leftButton = new JButton("<<");
        rightButton = new JButton(">>");
        jFrame.add(leftButton);
        jFrame.add(rightButton);
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inputLabel.setText(nodeHandler.NextStep());
                DefaultTreeModel treeModel= (DefaultTreeModel) tree.getModel();
                treeModel.reload();
                for (int i=0;i<tree.getRowCount();i++)
                    tree.expandRow(i);
            }
        });
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inputLabel.setText(nodeHandler.PreviousStep());
                DefaultTreeModel treeModel= (DefaultTreeModel) tree.getModel();
                treeModel.reload();
                for (int i=0;i<tree.getRowCount();i++)
                    tree.expandRow(i);
            }
        });
        createButton("(",200,topMargin,buttonSize,buttonSize);
        createButton(")",200+buttonSize,topMargin,buttonSize,buttonSize);
        createButton("%",200+buttonSize*2,topMargin,buttonSize,buttonSize);
        createButton("1/x",200+buttonSize*3,topMargin,buttonSize,buttonSize);
        createCleanButton("C",200+buttonSize*4,topMargin,buttonSize,buttonSize);

        createButton("7",200,topMargin+buttonSize,buttonSize,buttonSize);
        createButton("8",200+buttonSize,topMargin+buttonSize,buttonSize,buttonSize);
        createButton("9",200+buttonSize*2,topMargin+buttonSize,buttonSize,buttonSize);
        createButton("/",200+buttonSize*3,topMargin+buttonSize,buttonSize,buttonSize);
        createWorkingButton("=",200+buttonSize*4,topMargin+buttonSize,buttonSize,buttonSize);

        createButton("4",200,topMargin+buttonSize*2,buttonSize,buttonSize);
        createButton("5",200+buttonSize,topMargin+buttonSize*2,buttonSize,buttonSize);
        createButton("6",200+buttonSize*2,topMargin+buttonSize*2,buttonSize,buttonSize);
        createButton("*",200+buttonSize*3,topMargin+buttonSize*2,buttonSize,buttonSize);
        leftButton.setBounds(200+buttonSize*4,topMargin+buttonSize*2,buttonSize,buttonSize);

        createButton("1",200,topMargin+buttonSize*3,buttonSize,buttonSize);
        createButton("2",200+buttonSize,topMargin+buttonSize*3,buttonSize,buttonSize);
        createButton("3",200+buttonSize*2,topMargin+buttonSize*3,buttonSize,buttonSize);
        createButton("-",200+buttonSize*3,topMargin+buttonSize*3,buttonSize,buttonSize);
        rightButton.setBounds(200+buttonSize*4,topMargin+buttonSize*3,buttonSize,buttonSize);

        createButton("0",200,topMargin+buttonSize*4,buttonSize,buttonSize);
        createButton(".",200+buttonSize,topMargin+buttonSize*4,buttonSize,buttonSize);
        createButton("sqrt",200+buttonSize*2,topMargin+buttonSize*4,buttonSize,buttonSize);
        createButton("+",200+buttonSize*3,topMargin+buttonSize*4,buttonSize,buttonSize);
    }
    void createButton(final String text, int var1, int var2, int var3, int var4){
        JButton button= new JButton(text);
        button.setBounds(var1, var2, var3, var4);
        jFrame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (text){
                    case "1/x":
                        inputLabel.setText(inputLabel.getText()+"1/");
                        break;
                    case "sqrt":
                        inputLabel.setText(inputLabel.getText()+"sqrt(");
                        break;
                    default:
                        inputLabel.setText(inputLabel.getText()+text);

                }
            }
        });
    }
    void createCleanButton(String text,int var1, int var2, int var3, int var4){
        JButton button= new JButton(text);
        button.setBounds(var1, var2, var3, var4);
        jFrame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inputLabel.setText("");
                ansverLabel.setText("");
                rootNode.setUserObject("");
                rootNode.removeAllChildren();
                DefaultTreeModel treeModel= (DefaultTreeModel) tree.getModel();
                treeModel.setRoot(rootNode);
                treeModel.reload();
            }
        });
    }

    void createWorkingButton(String text,int var1, int var2, int var3, int var4){
        JButton button= new JButton(text);
        button.setBounds(var1, var2, var3, var4);
        jFrame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               // String exp = "7+(5-2*(3-4))-(2*2-4*(4-3!))";
                nodeHandler.setOperationNodeList(new Calc().eval(inputLabel.getText()));
                nodeHandler.setExpression(inputLabel.getText());
                ansverLabel.setText(String.valueOf(nodeHandler.getOperationNodeList().get(nodeHandler.getOperationNodeList().size()-1).getValue()));
                DefaultTreeModel treeModel= (DefaultTreeModel) tree.getModel();
                treeModel.setRoot(nodeHandler.getOperationNodeList().get(nodeHandler.getOperationNodeList().size()-1).getLeaf());
                treeModel.reload();
            }
        });
    }

}
