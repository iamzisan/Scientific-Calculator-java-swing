import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;



class RoundJTextField extends JTextField {
    private int arc;

    public RoundJTextField(int arc) {
        this.arc = arc;
        setOpaque(false);  // Make the text field non-opaque
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        if (!isOpaque()) {
            super.paintComponent(g);
            return;
        }
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                            java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        Border border = getBorder();
        if (arc > 0 && border != null) {
            border = new LineBorder(getBackground(), arc);
        }
        setBorder(border);
        super.paintComponent(g);
    }
}


class GlassyButton extends JButton {
    public GlassyButton(String label) {
        super(label);
        setOpaque(false);  // Make the button non-opaque
        setContentAreaFilled(false);  // Do not fill the content area
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            // Paint a darker background when the button is pressed
            g.setColor(new Color(0, 128, 255, 128)); // You can adjust the color and transparency
        } else {
            // Paint a lighter background when the button is not pressed
            g.setColor(new Color(0, 128, 255, 96)); // You can adjust the color and transparency
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Adjust the transparency
        g2.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }
}


class GlassyRadioButton extends JRadioButton {
    public GlassyRadioButton(String label) {
        super(label);
        setOpaque(false);  // Make the button non-opaque
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isSelected()) {
            // Customize the appearance when the button is selected (ON)
            g.setColor(new Color(0, 128, 255, 128));  // You can adjust the color and transparency
        } else {
            // Customize the appearance when the button is not selected (OFF)
            g.setColor(new Color(0, 128, 255, 96));  // You can adjust the color and transparency
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));  // Adjust the transparency
        g2.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }
}



public class ScientificCalculator {

	private JFrame frame;
	private JTextField textField;
	
	double first;
	double second;
	double result;
	String operation;
	String answer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScientificCalculator window = new ScientificCalculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScientificCalculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 255, 204));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scientific Calculator");
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 11, 380, 53);
		frame.getContentPane().add(lblNewLabel);
		
		int cornerRadius = 10; // Adjust the radius for your preferred roundness
		textField = new RoundJTextField(cornerRadius);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(10, 75, 372, 89);
		textField.setBackground(Color.WHITE); // Set the background color
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnplusminus = new GlassyButton("+/-");
		btnplusminus.setForeground(new Color(0, 0, 51));
		btnplusminus.setEnabled(false);
		btnplusminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a=Double.parseDouble(String.valueOf(textField.getText()));
				a=a*(-1);
				textField.setText(String.valueOf(a));
				
			}
		});
		btnplusminus.setFont(new Font("Dialog", Font.BOLD, 14));
		btnplusminus.setBounds(10, 513, 75, 48);
		frame.getContentPane().add(btnplusminus);
		
		JButton btn0 = new GlassyButton("0");
		btn0.setForeground(new Color(0, 0, 51));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn0.getText();
				textField.setText(num);
			}
		});
		btn0.setEnabled(false);
		btn0.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn0.setBounds(84, 513, 149, 48);
		frame.getContentPane().add(btn0);
		
		JButton btndot = new GlassyButton(".");
		btndot.setForeground(new Color(0, 0, 51));
		btndot.setEnabled(false);
		btndot.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btndot.setBounds(233, 513, 75, 48);
		frame.getContentPane().add(btndot);
		
		JButton btnequal = new GlassyButton("=");
		btnequal.setForeground(new Color(0, 0, 51));
		btnequal.setEnabled(false);
		btnequal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String expression = textField.getText();
		        String[] parts = expression.split(" "); // Split the expression by spaces

		        if (parts.length != 3) {
		            // Handle invalid expression format
		            textField.setText("Invalid expression");
		        } else {
		            // Extract the first number, operator, and second number
		            double num1 = Double.parseDouble(parts[0]);
		            String operator = parts[1];
		            double num2 = Double.parseDouble(parts[2]);

		            if (operator.equals("+")) {
		                result = num1 + num2;
		            } else if (operator.equals("-")) {
		                result = num1 - num2;
		            } else if (operator.equals("*")) {
		                result = num1 * num2;
		            } else if (operator.equals("/")) {
		                result = num1 / num2;
		            } else if (operator.equals("%")) {
		                result = num1 % num2;
		            } else if (operator.equals("^")) { // Check for the "^" operator
		                double result1 = 1;
		                for (int i = 0; i < num2; i++) {
		                    result1 = num1 * result1;
		                }
		                result = result1;
		            } else {
		                // Handle unknown operator
		                textField.setText("Unknown operator");
		                return;
		            }

		            answer = String.format("%.2f", result);

		            // Display the full expression and the answer on separate lines
		            textField.setText(expression + "\n= " + answer);
		            textField.setForeground(Color.BLUE);
		        }
			}
		});
		btnequal.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btnequal.setBounds(307, 513, 75, 48);
		frame.getContentPane().add(btnequal);
		
		JButton btnFact = new GlassyButton("n!");
		btnFact.setForeground(new Color(0, 0, 51));
		btnFact.setEnabled(false);
		btnFact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a=Double.parseDouble(textField.getText());
				double fact=1;
				while(a!=0) {
					fact=fact*a;
					a--;
				}
				textField.setText("");
				textField.setText(textField.getText()+fact);
			}
		});
		btnFact.setFont(new Font("Dialog", Font.BOLD, 14));
		btnFact.setBounds(10, 466, 75, 48);
		frame.getContentPane().add(btnFact);
		
		JButton btn1 = new GlassyButton("1");
		btn1.setForeground(new Color(0, 0, 51));
		btn1.setEnabled(false);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn1.getText();
				textField.setText(num);
			}
		});
		btn1.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn1.setBounds(84, 466, 75, 48);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new GlassyButton("2");
		btn2.setForeground(new Color(0, 0, 51));
		btn2.setEnabled(false);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn2.getText();
				textField.setText(num);
			}
		});
		btn2.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn2.setBounds(158, 466, 75, 48);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new GlassyButton("3");
		btn3.setForeground(new Color(0, 0, 51));
		btn3.setEnabled(false);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn3.getText();
				textField.setText(num);
			}
		});
		btn3.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn3.setBounds(233, 466, 75, 48);
		frame.getContentPane().add(btn3);
		
		JButton btnDiv = new GlassyButton("/");
		btnDiv.setForeground(new Color(0, 0, 51));
		btnDiv.setEnabled(false);
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				operation="/";
				textField.setText(textField.getText() + " / ");
			}
		});
		btnDiv.setFont(new Font("Microsoft Tai Le", Font.BOLD, 16));
		btnDiv.setBounds(307, 466, 75, 48);
		frame.getContentPane().add(btnDiv);
		
		JButton btnX2 = new GlassyButton("x^2");
		btnX2.setForeground(new Color(0, 0, 51));
		btnX2.setEnabled(false);
		btnX2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a=(Double.parseDouble(textField.getText()));
				a=a*a;
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnX2.setFont(new Font("Dialog", Font.BOLD, 14));
		btnX2.setBounds(10, 418, 75, 48);
		frame.getContentPane().add(btnX2);
		
		JButton btn4 = new GlassyButton("4");
		btn4.setForeground(new Color(0, 0, 51));
		btn4.setEnabled(false);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn4.getText();
				textField.setText(num);
			}
		});
		btn4.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn4.setBounds(84, 418, 75, 48);
		frame.getContentPane().add(btn4);
		
		JButton btn5 = new GlassyButton("5");
		btn5.setForeground(new Color(0, 0, 51));
		btn5.setEnabled(false);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn5.getText();
				textField.setText(num);
			}
		});
		btn5.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn5.setBounds(158, 418, 75, 48);
		frame.getContentPane().add(btn5);
		
		JButton btn6 = new GlassyButton("6");
		btn6.setForeground(new Color(0, 0, 51));
		btn6.setEnabled(false);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn6.getText();
				textField.setText(num);
			}
		});
		btn6.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn6.setBounds(233, 418, 75, 48);
		frame.getContentPane().add(btn6);
		
		JButton btnMul = new GlassyButton("X");
		btnMul.setForeground(new Color(0, 0, 51));
		btnMul.setEnabled(false);
		btnMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				operation="*";
				textField.setText(textField.getText() + " * ");
			}
		});
		btnMul.setFont(new Font("Microsoft Tai Le", Font.BOLD, 16));
		btnMul.setBounds(307, 418, 75, 48);
		frame.getContentPane().add(btnMul);
		
		JButton btnX3 = new GlassyButton("x^3");
		btnX3.setForeground(new Color(0, 0, 51));
		btnX3.setEnabled(false);
		btnX3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a=(Double.parseDouble(textField.getText()));
				a=a*a*a;
				textField.setText("");
				textField.setText(textField.getText()+a);
			}
		});
		btnX3.setFont(new Font("Dialog", Font.BOLD, 14));
		btnX3.setBounds(10, 370, 75, 48);
		frame.getContentPane().add(btnX3);
		
		JButton btn7 = new GlassyButton("7");
		btn7.setForeground(new Color(0, 0, 51));
		btn7.setEnabled(false);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn7.getText();
				textField.setText(num);
			}
		});
		btn7.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn7.setBounds(84, 370, 75, 48);
		frame.getContentPane().add(btn7);
		
		JButton btn8 = new GlassyButton("8");
		btn8.setForeground(new Color(0, 0, 51));
		btn8.setEnabled(false);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn8.getText();
				textField.setText(num);
			}
		});
		btn8.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn8.setBounds(158, 370, 75, 48);
		frame.getContentPane().add(btn8);
		
		JButton btn9 = new GlassyButton("9");
		btn9.setForeground(new Color(0, 0, 51));
		btn9.setEnabled(false);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=textField.getText()+btn9.getText();
				textField.setText(num);
			}
		});
		btn9.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btn9.setBounds(233, 370, 75, 48);
		frame.getContentPane().add(btn9);
		
		JButton btnMinus = new GlassyButton("-");
		btnMinus.setForeground(new Color(0, 0, 51));
		btnMinus.setEnabled(false);
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				operation="-";
				textField.setText(textField.getText() + " - ");
			}
		});
		btnMinus.setFont(new Font("Microsoft Tai Le", Font.BOLD, 26));
		btnMinus.setBounds(307, 370, 75, 48);
		frame.getContentPane().add(btnMinus);
		
		JButton btnXY = new GlassyButton("x^y");
		btnXY.setForeground(new Color(0, 0, 51));
		btnXY.setEnabled(false);
		btnXY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = Double.parseDouble(textField.getText());
		        operation = "x^y";
		        
		        // Append " ^ " to the current text, indicating the operation
		        textField.setText(textField.getText() + " ^ ");
				
			}
		});
		btnXY.setFont(new Font("Dialog", Font.BOLD, 14));
		btnXY.setBounds(10, 322, 75, 48);
		frame.getContentPane().add(btnXY);
		
		JButton btnMod = new GlassyButton("%");
		btnMod.setForeground(new Color(0, 0, 51));
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = Double.parseDouble(textField.getText());
		        operation = "%";
		        
		        // Append the "%" to the current text, indicating the operation
		        textField.setText(textField.getText() + " % ");
			}
		});
		btnMod.setEnabled(false);
		btnMod.setFont(new Font("Microsoft Tai Le", Font.BOLD, 20));
		btnMod.setBounds(84, 322, 75, 48);
		frame.getContentPane().add(btnMod);
		
		JButton btnC = new GlassyButton("C");
		btnC.setForeground(new Color(0, 0, 51));
		btnC.setEnabled(false);
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnC.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btnC.setBounds(158, 322, 75, 48);
		frame.getContentPane().add(btnC);
		
		JButton btnB = new GlassyButton("B");
		btnB.setForeground(new Color(0, 0, 51));
		btnB.setEnabled(false);
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentText = textField.getText();
		        if (!currentText.isEmpty()) {
		            // Remove the last character from the text
		            textField.setText(currentText.substring(0, currentText.length() - 1));
		        }
			}
		});
		btnB.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btnB.setBounds(233, 322, 75, 48);
		frame.getContentPane().add(btnB);
		
		JButton btnPlus = new GlassyButton("+");
		btnPlus.setForeground(new Color(0, 0, 51));
		btnPlus.setEnabled(false);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				operation="+";
				textField.setText(textField.getText() + " + ");
				
				
			}
		});
		btnPlus.setFont(new Font("Microsoft Tai Le", Font.BOLD, 24));
		btnPlus.setBounds(307, 322, 75, 48);
		frame.getContentPane().add(btnPlus);
		
		JButton btn1byx = new GlassyButton("1/x");
		btn1byx.setForeground(new Color(0, 0, 51));
		btn1byx.setEnabled(false);
		btn1byx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = 1.0 / value;
		        textField.setText("1/" + value + " = " + result);
			}
		});
		btn1byx.setFont(new Font("Dialog", Font.BOLD, 14));
		btn1byx.setBounds(10, 274, 75, 48);
		frame.getContentPane().add(btn1byx);
		
		JButton btnLog = new GlassyButton("Log");
		btnLog.setForeground(new Color(0, 0, 51));
		btnLog.setEnabled(false);
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.log(value);
		        textField.setText("ln " + value + " = " + result);
			}
		});
		btnLog.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLog.setBounds(84, 274, 75, 48);
		frame.getContentPane().add(btnLog);
		
		JButton btnSinh = new GlassyButton("Sinh");
		btnSinh.setForeground(new Color(0, 0, 51));
		btnSinh.setEnabled(false);
		btnSinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.sinh(value);
		        textField.setText("sinh " + value + " = " + result);
			}
		});
		btnSinh.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSinh.setBounds(158, 274, 75, 48);
		frame.getContentPane().add(btnSinh);
		
		JButton btnCosh = new GlassyButton("Cosh");
		btnCosh.setForeground(new Color(0, 0, 51));
		btnCosh.setEnabled(false);
		btnCosh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.cosh(value);
		        textField.setText("cosh " + value + " = " + result);
			}
		});
		btnCosh.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCosh.setBounds(233, 274, 75, 48);
		frame.getContentPane().add(btnCosh);
		
		JButton btnTanh = new GlassyButton("Tanh");
		btnTanh.setForeground(new Color(0, 0, 51));
		btnTanh.setEnabled(false);
		btnTanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.tanh(value);
		        textField.setText("tanh " + value + " = " + result);
			}
		});
		btnTanh.setFont(new Font("Dialog", Font.BOLD, 14));
		btnTanh.setBounds(307, 274, 75, 48);
		frame.getContentPane().add(btnTanh);
		
		
		
		JButton btnR = new GlassyButton("R");
		btnR.setText("Root");
		btnR.setForeground(new Color(0, 0, 51));
		btnR.setEnabled(false);
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.sqrt(value);
		        textField.setText("√" + value + " = " + result);
			}
		});
		btnR.setFont(new Font("Dialog", Font.BOLD, 14));
		btnR.setBounds(10, 226, 75, 48);
		frame.getContentPane().add(btnR);
		
		JButton btnex = new GlassyButton("e^x");
		btnex.setForeground(new Color(0, 0, 51));
		btnex.setEnabled(false);
		btnex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.exp(value);
		        textField.setText("e^" + value + " = " + result);
			}
		});
		btnex.setFont(new Font("Dialog", Font.BOLD, 14));
		btnex.setBounds(84, 226, 75, 48);
		frame.getContentPane().add(btnex);
		
		JButton btnSin = new GlassyButton("Sinx");
		btnSin.setForeground(new Color(0, 0, 51));
		btnSin.setEnabled(false);
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double value = Double.parseDouble(textField.getText());
		        double result = Math.sin(Math.toRadians(value)); // Calculate sine in radians
		        textField.setText("sin " + value + " = " + result);
				
			}
		});
		btnSin.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSin.setBounds(158, 226, 75, 48);
		frame.getContentPane().add(btnSin);
		
		JButton btnCos = new GlassyButton("Cosx");
		btnCos.setForeground(new Color(0, 0, 51));
		btnCos.setEnabled(false);
		btnCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.cos(Math.toRadians(value)); // Calculate cosine in radians
		        textField.setText("cos " + value + " = " + result);
			}
			
		});
		btnCos.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCos.setBounds(233, 226, 75, 48);
		frame.getContentPane().add(btnCos);
		
		JButton btnTan = new GlassyButton("Tanx");
		btnTan.setForeground(new Color(0, 0, 51));
		btnTan.setEnabled(false);
		btnTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = Double.parseDouble(textField.getText());
		        double result = Math.tan(Math.toRadians(value)); // Calculate tangent in radians
		        textField.setText("tan " + value + " = " + result);
			}
		});
		btnTan.setFont(new Font("Dialog", Font.BOLD, 14));
		btnTan.setBounds(307, 226, 75, 48);
		frame.getContentPane().add(btnTan);
		
		JRadioButton btnOn = new GlassyRadioButton("ON");
		btnOn.setForeground(new Color(0, 0, 51));
		btnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1.setEnabled(true);
				btn2.setEnabled(true);
				btn3.setEnabled(true);
				btn4.setEnabled(true);
				btn5.setEnabled(true);
				btn6.setEnabled(true);
				btn7.setEnabled(true);
				btn8.setEnabled(true);
				btn9.setEnabled(true);
				btn0.setEnabled(true);
				btndot.setEnabled(true);
				btnPlus.setEnabled(true);
				btnMinus.setEnabled(true);
				btnMul.setEnabled(true);
				btnDiv.setEnabled(true);
				btnequal.setEnabled(true);
				btnMod.setEnabled(true);
				btnC.setEnabled(true);
				btnB.setEnabled(true);
				btnplusminus.setEnabled(true);
				btnFact.setEnabled(true);
				btnX2.setEnabled(true);
				btnX3.setEnabled(true);
				btnXY.setEnabled(true);
				btn1byx.setEnabled(true);
				btnR.setEnabled(true);
				btnLog.setEnabled(true);
				btnSin.setEnabled(true);
				btnCos.setEnabled(true);
				btnTan.setEnabled(true);
				btnSinh.setEnabled(true);
				btnCosh.setEnabled(true);
				btnTanh.setEnabled(true);
				btnex.setEnabled(true);
			}
		});
		buttonGroup.add(btnOn);
		btnOn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOn.setBounds(98, 171, 61, 48);
		frame.getContentPane().add(btnOn);
		
		JRadioButton btnOff = new GlassyRadioButton("OFF");
		btnOff.setForeground(new Color(0, 0, 51));
		btnOff.setSelected(true);
		btnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				btn1.setEnabled(false);
				btn2.setEnabled(false);
				btn3.setEnabled(false);
				btn4.setEnabled(false);
				btn5.setEnabled(false);
				btn6.setEnabled(false);
				btn7.setEnabled(false);
				btn8.setEnabled(false);
				btn9.setEnabled(false);
				btn0.setEnabled(false);
				btndot.setEnabled(false);
				btnPlus.setEnabled(false);
				btnMinus.setEnabled(false);
				btnMul.setEnabled(false);
				btnDiv.setEnabled(false);
				btnequal.setEnabled(false);
				btnMod.setEnabled(false);
				btnC.setEnabled(false);
				btnB.setEnabled(false);
				btnplusminus.setEnabled(false);
				btnFact.setEnabled(false);
				btnX2.setEnabled(false);
				btnX3.setEnabled(false);
				btnXY.setEnabled(false);
				btn1byx.setEnabled(false);
				btnR.setEnabled(false);
				btnLog.setEnabled(false);
				btnSin.setEnabled(false);
				btnCos.setEnabled(false);
				btnTan.setEnabled(false);
				btnSinh.setEnabled(false);
				btnCosh.setEnabled(false);
				btnTanh.setEnabled(false);
				btnex.setEnabled(false);
				
				
			}
		});
		buttonGroup.add(btnOff);
		btnOff.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOff.setBounds(233, 171, 61, 48);
		frame.getContentPane().add(btnOff);
		
		JLabel lblNewLabel_1 = new JLabel("Developed by Zisan");
		lblNewLabel_1.setFont(new Font("Castellar", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(222, 61, 149, 14);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setBounds(100, 100, 404, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
