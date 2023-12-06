package Swing;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorDemo extends JFrame implements ActionListener {
    private JButton btn1, btn2, btn3, btn4;
    /* 2 so va ket qua */
    private JTextField tf1, tf2, tf3;
    private double result;
    /* dung de nhan tang ContentPane JFrame */
    private Container container;
    /* dung panel de nhom cac thanh phan tren giao dien */
    private JPanel panel1, panel2;

    public CalculatorDemo(String s) {
        super(s);
        /* lay class ContentPane de dat cac doi tuong hien thi */
        container = this.getContentPane();

        /* tao cac thanh phan tren giao dien */
        JLabel num1 = new JLabel("1st number: ");
        tf1 = new JTextField();
        JLabel num2 = new JLabel("1st number: ");
        tf2 = new JTextField();
        JLabel resultl = new JLabel("Ket qua: ");
        tf3 = new JTextField();

        tf3.setEditable(false);

        /* panel 1 */
        panel1 = new JPanel();
        /* layout gom 3 cot */
        panel1.setLayout(new GridLayout(3, 2));
        /* panel 1 */
        panel1.add(num1);
        panel1.add(tf1);
        panel1.add(num2);
        panel1.add(tf2);
        panel1.add(resultl);
        panel1.add(tf3);
        /* tao 4 buttons */
        btn1 = new JButton("+");
        btn2 = new JButton("-");
        btn3 = new JButton("*");
        btn4 = new JButton(":");
        /* Panel2 co 4 nut */
        panel2 = new JPanel();
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);
        panel2.add(btn4);

        /* 2 panel get in ContentPane */
        container.add(panel1);
        container.add(panel2, "South");

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);

        /* kich thuoc hien thi */
        this.setSize(350, 200);
        this.setVisible(true);

        // Set default close operation to EXIT_ON_CLOSE
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Add() {
        result = Double.parseDouble(tf1.getText()) + Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    public void Minus() {
        result = Double.parseDouble(tf1.getText()) - Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    public void Multi() {
        result = Double.parseDouble(tf1.getText()) * Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    public void Div() {
        result = Double.parseDouble(tf1.getText()) / Double.parseDouble(tf2.getText());
        tf3.setText(String.valueOf(result));
    }

    /* bat dau tinh toan khi nguoi dung nhap phep tinh */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "+") {
            // khi chua nhap so da bam tinh toan
            if (tf1.getText().equals("") || tf2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ban chua nhap du");
            } else {
                Add();
            }
        }

        if (e.getActionCommand() == "-") {
            if (tf1.getText().equals("") || tf2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ban chua nhap du");
            } else {
                Minus();
            }
        }
        if (e.getActionCommand() == "*") {
            if (tf1.getText().equals("") || tf2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ban chua nhap du");
            } else {
                Multi();
            }
        }
        if (e.getActionCommand() == ":") {
            if (tf1.getText().equals("") || tf2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ban chua nhap du");
            } else {
                Div();
            }
        }
    }

    public static void main(String[] args) {
        CalculatorDemo cal = new CalculatorDemo("SimpleCalculator");
        cal.setLocationRelativeTo(null);
    }
}
