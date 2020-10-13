package calculator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame {
	private JButton numbers[]=new JButton[10];
	private JButton reset,equals;
	private JButton operators[]=new JButton[4];
	private JPanel grid,oper,output,labels;
	private JLabel cal,cal2;
	private double num1,num2;
	int operator=0;
	String currentNum="0",operatortext;
	boolean type=false;
	public static void main(String[] args) {
		new Calculator();
	}
	public Calculator() {
		this.setSize(400,600);
		this.setResizable(false);
		
		operators[0]=createOperator("+",1);
		operators[1]=createOperator("-",2);
		operators[2]=createOperator("x",3);
		operators[3]=createOperator("/",4);
		reset=createReset();
		equals=createEquals();
		
		cal=new JLabel();
		cal.setFont (new Font ("TimesRoman", Font.BOLD, 50));
		cal.setText(" ");
		
		cal2=new JLabel();
		cal2.setFont (new Font ("TimesRoman", Font.BOLD, 50));
		cal2.setText(currentNum);
		
		grid=new JPanel();
		oper=new JPanel();
		output=new JPanel();
		labels=new JPanel();
		
		output.add(cal);
		
		grid.setLayout(new GridLayout(4,3));
		oper.setLayout(new GridLayout(6,0));
		output.setLayout(new FlowLayout());
		labels.setLayout(new BorderLayout());
		
		oper.add(operators[0]);
		oper.add(operators[1]);
		oper.add(operators[2]);
		oper.add(operators[3]);
		oper.add(reset);
		oper.add(equals);
		
		labels.add(cal,BorderLayout.NORTH);
		labels.add(cal2,BorderLayout.SOUTH);
		
		
		for(int i=0;i<10;i++) {
			numbers[i]=createNumber(i);
			grid.add(numbers[i]);
		}
		
		this.add(labels,BorderLayout.NORTH);
		this.add(oper,BorderLayout.WEST);
		this.add(grid,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public JButton createNumber(int num) {
		JButton but=new JButton(Integer.toString(num));
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(type) {
					num2=0;
					type=false;
				}
				if(operator==0) {
					num1=num1*10+num;
					currentNum=Double.toString(num1);
				}
				else {
					num2=num2*10+num;
					currentNum=Double.toString(num2);
				}
				cal2.setText(currentNum);
			}
			
		});

		return but;
	
	}
	
	public JButton createOperator(String text,int num) {
		JButton but=new JButton(text);
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cal2.setText("0");
				if(operator==0) {
					cal.setText(currentNum+text);
				}
				else if(!type) {
					num1=calculate(num1,num2,operator);
					currentNum=Double.toString(num1);
					cal.setText(currentNum+text);
					currentNum="0";
					cal2.setText(currentNum);
					num2=0;
				}
				else {
					type=false;
					cal.setText(Double.toString(num1)+text);
					num2=0;
				}
				operatortext=text;
				operator=num;
				currentNum="";
			}
			
		});

		return but;
	}
	
	public JButton createEquals() {
		JButton but=new JButton("=");
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(operator!=0) {
					num1=calculate(num1,num2,operator);
					currentNum=Double.toString(num1);
					cal.setText(currentNum+operatortext);
					type=true;
				}
				
				
			}
			
		});

		return but;
	}
	
	public JButton createReset() {
		JButton but=new JButton("CE");
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				num1=0;
				num2=0;
				currentNum="0";
				cal.setText("0");
				cal2.setText(" ");
				operator=0;
				
				
			}
			
		});

		return but;
	}
	
	public double calculate(double n1,double n2,int op) {
		if(op==1)n1+=n2;
		else if(op==2)n1-=n2;
		else if(op==3)n1*=n2;
		else if(op==4)n1/=n2;
		return n1;
	}
}
