package school;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MultipleChoice extends JFrame {

	JPanel main,radio,south;
	JLabel question,previous,statsNorth,statsCenter,statsSouth;
	JButton next;
	JRadioButton[] ans=new JRadioButton[4];
	int questionN=0;
	int answerN=100;
	int score=0;
	int correct=0,incorrect=0;
	final String[] questions={"Find the slope of the adjoining points A(-1,5) and B(-3,1)",
							  "The solution to the linear system x+y=-2 and x-4y=-12",
							  "When n=101,which of the following expressions has an even value",
							  "Edyln has 3 roses, 2 tulips,4 daisies, and 6 lilies. What chance is there of a rose",
							  "Solve for the last angle, if the other angles in a triangle are 78 and 56"};
	final String[][] answers= {{"5","-2","2","-6"},
			{"(-4,2)","(-4,-2)","(2,-4)","(3,2)"},
			{"n-12","2n-2","3n+2","n+2"},
			{"12/15","2/15","3/15","4/15"},
			{"102 degrees","22 degrees","134 degrees","46 degrees"}};
	final int[] rightAns= {2,0,1,2,3};
	public MultipleChoice() {
		this.setSize(700,300);
		this.setResizable(false);
		
		question=new JLabel("Question 1: "+questions[0]+"    Score:"+Integer.toString(score));
		previous=new JLabel("Click next to go to next question");
		
		next=nextQuestion("Next",this);
		
		main = new JPanel();
		radio=new JPanel();
		south=new JPanel();
		
		main.setLayout(new BorderLayout());
		radio.setLayout(new FlowLayout());
		south.setLayout(new FlowLayout());
		
		
		main.add(question,BorderLayout.NORTH);
		createAnswers();
		main.add(radio,BorderLayout.CENTER);
		south.add(previous);
		south.add(next);
		main.add(south,BorderLayout.SOUTH);
		this.add(main);
		this.setVisible(true);
	}

	public void createAnswers() {
		if(questionN!=0)radio.removeAll();
		ButtonGroup group = new ButtonGroup();
		for(int i=0;i<4;i++) {
			ans[i]=createAnswer(answers[questionN][i],i);
			group.add(ans[i]);
			radio.add(ans[i]);
		}
		
	}
	public JRadioButton createAnswer(String name,int n) {
		JRadioButton theRadio= new JRadioButton(name);
		theRadio.addActionListener(new ActionListener() {



			public void actionPerformed(ActionEvent e) {
				answerN=n;
			}
		});
		return theRadio;
	}
	
	public JButton nextQuestion(String t,JFrame x) {
		JButton but=new JButton(t);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(answerN==100) {
					previous.setText("Choose answer before clicking next");
				}
				else {
					if(answerN==rightAns[questionN]) {
						score++;
						previous.setText("Previous answer was correct");
						correct++;
					}
					else {
						previous.setText("Previous answer was incorrect");
						incorrect++;
					}
					if(questionN<4) {
						answerN=100;
						questionN++;
						question.setText("Question "+Integer.toString(questionN+1)+":" +questions[questionN]+"    Score:"+Integer.toString(score));
						createAnswers();
					}
					else{
						x.setVisible(false);
						main.removeAll();
						DecimalFormat df = new DecimalFormat("0.#");
						
						statsNorth=new JLabel("You got "+correct+" answers right");
						statsCenter=new JLabel("You got "+incorrect+" answers wrong");
						statsSouth=new JLabel("Your score is %"+df.format((score/5.0)*100.0));
						main.add(statsNorth,BorderLayout.NORTH);
						main.add(statsCenter,BorderLayout.CENTER);
						main.add(statsSouth,BorderLayout.SOUTH);
						x.setVisible(true);
					}
					
				}
		}});
		return but;
	}

	public static void main(String []args) {
		new MultipleChoice();
	}
}
