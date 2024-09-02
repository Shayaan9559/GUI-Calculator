import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a Java program for a calculator with a graphical user interface
 * using Swing and AWT libraries. It consists of buttons for numbers
 * and arithmetic operators. It can perform basic arithmetic
 * operations such as addition, subtraction,  multiplication and division.
 * It is also capable of using DMAS. It can also divide by zero.
 *
 * @Shayaan
 * @2/4/23
 */
public class Calculator extends JFrame implements ActionListener
{
    //declaring instance variables
    private JButton[] buttons;
    private JPanel buttonPanel;
    private JTextField textField;
    private JButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private JButton buttonAdd, buttonMinus, buttonMultiplication, buttonDivide, buttonEquals, buttonClear;
    private double num1, num2, answer;
    private char operator;
    private ArrayList <Character> operators; 
    /**
     * Constructor for objects of class calculator
     */
    public Calculator()
    {
        // initializing instance variables
        calculatorGui();
        operators = new ArrayList<>();
        pack();
        setSize(350, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //method to create the graphical user interface and 
    //add action listeners to the buttons
    private void calculatorGui(){
        //setting up textfield style, text and alignment.
        textField = new JTextField();
        textField.setEditable(false);
        textField.setText("0");
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBounds(0, 0, 200, 30);
        textField.setEditable(false);
        textField.setText("0");
        Font myFont = new Font("Ariel",Font.BOLD, 32);
        textField.setFont(myFont);
        
        //create a panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setBounds(20, 50, 200, 240);
        panel.setLayout(new GridLayout(4, 4));
        panel.setPreferredSize(new Dimension(350, 350));
        
        //create number buttons
        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        
        //create operator buttons
        buttonAdd = new JButton("+");
        buttonMinus = new JButton("-");
        buttonMultiplication = new JButton("x");
        buttonDivide = new JButton("÷");
        buttonEquals = new JButton("=");
        buttonClear = new JButton("AC");
        
        //add buttons to an array for easier manipulation
        buttons = new JButton[] {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9};
        
        //add actionlisteners to the buttons
        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        buttonAdd.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonMultiplication.addActionListener(this);
        buttonDivide.addActionListener(this);
        buttonEquals.addActionListener(this);
        buttonClear.addActionListener(this);
        
        //add buttons to panel
        panel.setLayout(new GridLayout(4, 4));
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(buttonDivide);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(buttonMultiplication);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(buttonMinus);
        panel.add(buttonClear);
        panel.add(button0);
        panel.add(buttonEquals);
        panel.add(buttonAdd);
        
        
        //set layout of contentpane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(textField, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.SOUTH);
    }
    
    //boolean flags to keep track of button presses
    private boolean zeroClicked = false;
    private boolean equalClicked = false;
    private boolean acClicked = false;
    
    //check if character is an operator
    private boolean checkOperator(char c){
        return c == '+' || c == '-' || c == 'x' || c == '÷';
    }
    
    //check if string is negative
    private boolean checkNegative(String s){
        return s.length() > 1 && s.charAt(0) == '-' && Character.isDigit(s.charAt(1));
    }
    
    //handles button clicks and functionality
    @Override
    public void actionPerformed(ActionEvent e) {
    //get the text in the textfield
    String text = textField.getText();
    //get the lenght of text in textfield
    int textLength = text.length();
    
    // If only operators are entered, show them on the screen
    if (textLength == 0 && (e.getSource() == buttonAdd || e.getSource() == buttonMinus || e.getSource() == buttonMultiplication || e.getSource() == buttonDivide)) {
        textField.setText(textField.getText() + e.getActionCommand());
        return;
    }
        
    //handle button clicks
    for (int i = 0; i < 10; i++) {
        if (e.getSource() == buttons[i]) {
            if(e.getSource() == buttons[0]){
            zeroClicked = true;
        }
            if(equalClicked){
            textField.setText(Integer.toString(i));
            equalClicked = false;
        }
            else if(acClicked && textField.getText().equals("0")){
            textField.setText("");
            textField.setText(textField.getText() + i);
            acClicked = false;
        }   
            else if(textField.getText().equals("0")){
            textField.setText("");
            textField.setText(textField.getText() + i);
        }
    
            else{
            textField.setText(textField.getText() + i);
            }
        }
    }
    
    // Handle operator-specific input
    if (e.getSource() == buttonAdd) {
        if (textLength > 0) {
            if(textField.getText().equals("0") && !zeroClicked){
                textField.setText("");
            }
            operators.add('+');
            textField.setText(textField.getText() + "+");
        }
    } else if (e.getSource() == buttonMinus) {
        if (textLength > 0) {
             if(textField.getText().equals("0") && !zeroClicked){
                textField.setText("");
            }
            operators.add('-');
            textField.setText(textField.getText() + "-");
        }
    } else if (e.getSource() == buttonMultiplication) {
        if (textLength > 0) {
             if(textField.getText().equals("0") && !zeroClicked){
                textField.setText("");
            }
            operators.add('*');
            textField.setText(textField.getText() + "x");
        }
    } else if (e.getSource() == buttonDivide) {
        if (textLength > 0) {
             if(textField.getText().equals("0") && !zeroClicked){
                textField.setText("");
            } 
            operators.add('/');
            textField.setText(textField.getText() + "÷");
        }
    } else if (e.getSource() == buttonEquals) {
    //gets input from textfield and splits it into an arraylist of
    //strings
    String Input = textField.getText();
    String[] parts = Input.split("(?<=[-+x÷])|(?=[-+x÷])");
    ArrayList<String> input = new ArrayList<>(Arrays.asList(parts));

    //remove operator from the beginning of the input
    for(int i = 0; i < input.size(); i++){
        if(input.get(0).matches("[x÷+]")){
            input.remove(0);
        }
        //handle negative numbers at the beginning of the input
        else if(input.get(0).equals("-")){
            if(input.get(1).matches("\\d+")){
                input.set(0, "-" + input.get(1));
                input.remove(1);
            }
            else{
                input.remove(0);
                //System.out.print(input); for debugging
            }
        }
    }
    
    //remove any non digit character at end of input 
    for(int i = 0; i < input.size(); i++){
        if(!Character.isDigit(input.get(input.size() - 1).charAt(0))){
            input.remove(input.size() - 1);
        }
    }
    
    //combine consecutive operators and handle negative numbers in the
    //middle of the input
    for (int i = input.size() - 1; i >= 1; i--) {
        if (checkOperator(input.get(i).charAt(0))) {
            int j = i - 1;
            //System.out.print(input); for debugging
            while (j >= 0 && !checkNegative(input.get(j)) && checkOperator(input.get(j).charAt(0))) {
                input.remove(j);
                j--;
                //System.out.print(input); for debugging
            }
        } 
    }

    //handle cases when input contains only an operator and a number
    if(input.size() == 2){
        if(input.get(0).equals("+")){
            input.remove(0);
        }
        else if(input.get(0).equals("-")){
            String num = "-";
            num = num.concat(input.get(1));
            input.set(0, num);
            input.remove(1);
        }
    }
    
    //handles expression based on DMAS rule
    double d = 0.0;
    for (String operator : new String[]{"÷", "x", "+", "-"}) {
    int i = 1;
    while (i < input.size()) {
        if (input.get(i).equals(operator)) {
            if (input.get(i - 1) != null) {
                d = Double.parseDouble(input.get(i - 1));
            }
            double b = Double.parseDouble(input.get(i + 1));
            double result = 0.0;
            switch (operator) {
                case "÷":
                    result = d / b;
                    break;
                case "x":
                    result = d * b;
                    //System.out.println(result); for debugging
                    break;
                case "+":
                    result = d + b;
                    break;
                case "-":
                    result = d - b;
                    break;
            }
            input.set(i, String.valueOf(result));
            input.remove(i + 1);
            input.remove(i - 1);
            i -= 2;
        }
        i += 2;
        }
    }
    
    //get the final answer and set it to textfield
    double answer = Double.parseDouble(input.get(0));
        if(answer == (int) answer){
            textField.setText(String.valueOf((int) answer));
    }
        else if(answer != (int) answer)
        {
            textField.setText(String.valueOf(answer));
    }
    //equals has been clicked
    equalClicked = true;
    }
    
    //checks and handles case when AC is clicked
    if(e.getSource() == buttonClear){
        acClicked = true;
        zeroClicked = false;
        num1 = 0;
        num2 = 0;
        answer = 0;
        operator = '\0';
        textField.setText("0");
        }
    }
}



