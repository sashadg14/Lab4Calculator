import Nodes.OperationNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by alex o n 11.06.2017.
 */
public class CalculatorWindow {
    private JTree tree;
    JFrame jFrame;
    JButton button;
    JButton button2;
    JScrollPane jScrollPane;
    NodeHandler nodeHandler;
    public CalculatorWindow(final List<OperationNode> operationNodeList)
    {   jFrame = new JFrame();
        nodeHandler= new NodeHandler();
        nodeHandler.setOperationNodeList(operationNodeList);
        tree = new JTree(operationNodeList.get(operationNodeList.size()-1).getLeaf());
        jScrollPane = new JScrollPane(tree);
        button= new JButton("BUTTON");
        button2= new JButton("BUTTON2");
        tree.setShowsRootHandles(true);
        jFrame.add(jScrollPane);
        jFrame.add(button);
        jFrame.add(button2);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("JTree Example");
        jFrame.setBounds(400, 200,700,400);
        jFrame.setVisible(true);
        jFrame.getContentPane().setLayout(null);
        jScrollPane.setBounds(0,40,200,300);
        button.setBounds(200,40,100,50);
        button2.setBounds(300,40,100,50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            nodeHandler.NextStep();
                DefaultTreeModel treeModel= (DefaultTreeModel) tree.getModel();
                treeModel.reload();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            nodeHandler.PreviousStep();
                DefaultTreeModel treeModel= (DefaultTreeModel) tree.getModel();
                treeModel.reload();
                jScrollPane.updateUI();
            }
        });
    }
}
