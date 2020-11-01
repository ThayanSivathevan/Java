package calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Math extends JFrame {
	private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
	private JButton fib,prime,fact,clr;
	private JPanel label0,pb1,pb2,pb21,pb22,pb23,p1,p12,p2,p22,p3,p32,p4,p42;
	private JLabel input,output,output1;
	private int i,inputInt=0,outputInt=0,primeInt,fibInt=1,fibInt2=0,fibInt3,factInt;
	private String inputString="0",outputString="0",fibString,primeString,factString;



	public Math(){
		this.setSize(268,250);
		this.setLocation(40,40);
		this.setResizable(false);
		this.setTitle("TicTacToe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1=makeMeButtons("1",1);
		b2=makeMeButtons("2",2);
		b3=makeMeButtons("3",3);
		b4=makeMeButtons("4",4);
		b5=makeMeButtons("5",5);
		b6=makeMeButtons("6",6);
		b7=makeMeButtons("7",7);
		b8=makeMeButtons("8",8);
		b9=makeMeButtons("9",9);
		b0=makeMeButtons("0",0);
		fib=makeMeButtons2("Fibanaci",1);
		prime=makeMeButtons2("Prime",2);
		fact=makeMeButtons2("Factorial",3);
		clr=makeMeButtons2("CLEAR",4);
		input=new JLabel("0");
		output=new JLabel("0");
		output1=new JLabel("");
		label0=new JPanel();
		label0.setLayout(new BorderLayout ());
		pb1=new JPanel();
		pb1.setLayout(new BorderLayout ());
		pb2=new JPanel();
		pb2.setLayout(new BorderLayout ());
		pb2.setLayout(new BorderLayout ());
		p1=new JPanel();
		p1.setLayout(new BorderLayout ());
		p2=new JPanel();
		p2.setLayout(new BorderLayout ());
		p3=new JPanel();
		p3.setLayout(new BorderLayout ());
		p4=new JPanel();



		pb1.add(fib,BorderLayout.NORTH);
		pb1.add(prime,BorderLayout.WEST);
		pb1.add(clr,BorderLayout.EAST);
		pb1.add(fact,BorderLayout.SOUTH);

		p1.add(b1, BorderLayout.WEST);
		p1.add(b2, BorderLayout.CENTER);
		p1.add(b3, BorderLayout.EAST);

		p2.add(b4, BorderLayout.WEST);
		p2.add(b5, BorderLayout.CENTER);
		p2.add(b6, BorderLayout.EAST);

		p3.add(b7, BorderLayout.WEST);
		p3.add(b8, BorderLayout.CENTER);
		p3.add(b9, BorderLayout.EAST);
		p3.add(b0, BorderLayout.SOUTH);
		p2.add(p1,BorderLayout.NORTH);
		p2.add(p3,BorderLayout.SOUTH);

		pb2.add(p2, BorderLayout.SOUTH);

		label0.add(input,BorderLayout.NORTH);
		label0.add(output,BorderLayout.CENTER);
		label0.add(output1,BorderLayout.SOUTH);

		pb2.add(label0, BorderLayout.NORTH);
		this.add(pb1,BorderLayout.WEST);
		this.add(pb2,BorderLayout.EAST);
		this.setVisible(true);

	}
	public JButton makeMeButtons(String name,int inputInc){

		JButton theBut = new JButton(name);


		theBut.addActionListener(new ActionListener() {



			public void actionPerformed(ActionEvent e) {
				if(inputString.length()==17){
					output1.setText("Buffer full");
				}
				else{
					inputString=inputString+Integer.toString(inputInc);
					inputInt=(inputInt*10)+inputInc;
					input.setText(inputString);
				}

			}

		});



		return theBut; 

	} 
	public JButton makeMeButtons2(String name,int cmd){

		JButton theBut = new JButton(name);


		theBut.addActionListener(new ActionListener() {



			public void actionPerformed(ActionEvent e) {
				if(cmd==1){
					if(inputInt>0){
						for(i=1;i<=inputInt;i++){
							fibInt3=fibInt;
							fibInt=fibInt+fibInt2;
							fibInt2=fibInt3;
						}
					}
					else if(inputInt==0){
						fibInt=0;
						
					}
					fibString=Integer.toString(fibInt);
					output.setText(fibString);
					fibInt=1;
					fibInt3=0;
					fibInt2=0;
				}
				else if(cmd==2){
					
				}
				else if(cmd==3){
					
				}
				else if(cmd==4){
					clear();
				}



			}

		});



		return theBut; 

	} 
	public void clear(){
		input.setText("0");
		output.setText("0");
		output1.setText(null);
		inputInt=0;
		outputInt=0;
		fibInt2=0;
		factInt=0;
		inputString="0";
		outputString="0";
		fibString=null;
		primeString=null;
		factString=null;
	}


	public static void main(String[] args) {
		new Math();

	}

}
