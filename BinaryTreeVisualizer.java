import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeVisualizer extends JFrame {

    private JPanel treePanel;
    private JButton addButton;
    private JTextField valueField;
    private TreeNode root;

    public BinaryTreeVisualizer() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Binary Tree Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g, getWidth() / 2, 50, root);
            }
        };

        addButton = new JButton("Add Node");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(valueField.getText());
                    root = insertNode(root, value);
                    treePanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BinaryTreeVisualizer.this, "Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        valueField = new JTextField(10);
        JLabel label = new JLabel("Enter value:");

        JPanel inputPanel = new JPanel();
        inputPanel.add(label);
        inputPanel.add(valueField);
        inputPanel.add(addButton);

        add(treePanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void drawTree(Graphics g, int x, int y, TreeNode node) {
        if (node == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(node.val), x - 5, y + 5);

        if (node.left != null) {
            int leftX = x - 100;
            int leftY = y + 100;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, leftX, leftY);
            drawTree(g, leftX, leftY, node.left);
        }

        if (node.right != null) {
            int rightX = x + 100;
            int rightY = y + 100;
            g.setColor(Color.RED);
            g.drawLine(x, y, rightX, rightY);
            drawTree(g, rightX, rightY, node.right);
        }
    }

    private TreeNode insertNode(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (value < root.val) {
            root.left = insertNode(root.left, value);
        } else if (value > root.val) {
            root.right = insertNode(root.right, value);
        }

        return root;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BinaryTreeVisualizer());
    }
}
