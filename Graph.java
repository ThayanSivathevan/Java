// The "Graph" class.

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.math.*;
public class Graph extends JFrame //extends JFrame class, class acts like Jframe
{

    public static void main (String[] args)
    {
	new Graph (); //creates new graph JFrame
    }



    boolean linearDraw, quadraticDraw; //booleans for graph to paint in equations
    JFormattedTextField slope, y_int, Ainput, Binput, Cinput; //gets inputs for equations
    Font font = new Font ("Serif", Font.BOLD, 12);
    Font font2 = new Font ("Serif", Font.BOLD, 32); //font for labels
    JLabel linear, quadratic, mLabel, bLabel, ALabel, BLabel, CLabel, zeros, pois, xy; //Labels for everything
    JButton graphLinear, graphQuadratic; //two buttons used to draw and redraw eqations
    JPanel panel, Npanel, QuadraticPanel, QuadraticEquation, QuadraticPanel1, QuadraticPanel2, QuadraticPanel3, LinearPanel, LinearEquation, LinearPanel1, LinearPanel2, GraphPanel; //Panels holding all the components
    double x, y; //doubles for x and y coords
    double m, yint, a, b, c; //doubles for equation inputs
    double zero[] = new double [2]; //Zeros stored in here
    double poi[] [] = new double [2] [2]; //Point of intersections of the linear and quadratic equations are stored here

    public Graph ()  //sets up the Graph class JFrame
    {
	this.setTitle ("Graphing Stuff"); //Set up the basics of the Jframe like title and size
	this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	this.setResizable (false);
	this.setSize (550, 600);

	//Here is where all the components are being intialized and set up

	Container content = this.getContentPane ();//Creates a container for all the components including the JPanels
	
	
	//creates new title page class
	 titlepage b = new titlepage ();
	//sets location to the middle
	this.setLocationRelativeTo (null);
	//adds titlepage
	content.add (b);
	//sets the jframe to visible
	this.setVisible (true);
	//delay
	try
	{
	    Thread.sleep (2000);
	}
	catch (InterruptedException ex)
	{
	    Thread.currentThread ().interrupt ();
	}
	//sets the frame to invisible so that it can remove the titlepage class
	this.setVisible (false);
	content.remove (b);
	//Intializing and setting up the labels
	linear = lab ("y=mx+b");
	quadratic = lab ("y=ax+bx+c");
	mLabel = lab ("Enter m or slope");
	bLabel = lab ("  Enter b or y interept");
	ALabel = lab ("Enter a ");
	BLabel = lab ("Enter b ");
	CLabel = lab ("Enter c ");
	xy = lab (" ");
	zeros = lab ("Enter Quadratic");
	pois = lab ("Enter Linear");

	//Sets up the textfields for getting input
	slope = textfield ();
	y_int = textfield ();
	Ainput = textfield ();
	Binput = textfield ();
	Cinput = textfield ();

	//two buttons used to draw equations when clicked
	graphLinear = button ("     Graph Linear ");
	graphQuadratic = button2 ("    Graph Quadratic ");

	//intializes panels
	GraphPanel = panel ();
	panel = panel ();
	LinearPanel = panel ();
	LinearEquation = panel ();
	LinearPanel1 = panel ();
	LinearPanel2 = panel ();
	QuadraticPanel = panel ();
	QuadraticEquation = panel ();
	QuadraticPanel1 = panel ();
	QuadraticPanel2 = panel ();
	QuadraticPanel3 = panel ();
	Npanel = panel ();

	//Here is where the organization of the JFrame happens,

	//adds all the linear equation components to the linear panels
	LinearPanel1.add (slope, BorderLayout.WEST);
	LinearPanel1.add (mLabel, BorderLayout.NORTH);
	LinearPanel2.add (y_int, BorderLayout.WEST);
	LinearPanel2.add (bLabel, BorderLayout.NORTH);
	LinearPanel.add (linear, BorderLayout.NORTH);
	LinearPanel.add (LinearPanel1, BorderLayout.WEST);
	LinearPanel.add (LinearPanel2, BorderLayout.CENTER);
	LinearPanel.add (graphLinear, BorderLayout.SOUTH);

	//adds all the quadratic equation components to the quadratic panels
	QuadraticPanel1.add (Ainput, BorderLayout.WEST);
	QuadraticPanel1.add (ALabel, BorderLayout.NORTH);
	QuadraticPanel2.add (Binput, BorderLayout.WEST);
	QuadraticPanel2.add (BLabel, BorderLayout.NORTH);
	QuadraticPanel3.add (Cinput, BorderLayout.WEST);
	QuadraticPanel3.add (CLabel, BorderLayout.NORTH);
	QuadraticPanel.add (quadratic, BorderLayout.NORTH);
	QuadraticPanel.add (QuadraticPanel1, BorderLayout.WEST);
	QuadraticPanel.add (QuadraticPanel2, BorderLayout.CENTER);
	QuadraticPanel.add (QuadraticPanel3, BorderLayout.EAST);
	QuadraticPanel.add (graphQuadratic, BorderLayout.SOUTH);

	//adds the point of intersection and zeros labels to a panel
	Npanel.add (pois, BorderLayout.NORTH);
	Npanel.add (zeros, BorderLayout.CENTER);

	//adds the linear, quadratic and npanel to a panel
	panel.add (LinearPanel, BorderLayout.NORTH);
	panel.add (QuadraticPanel, BorderLayout.WEST);
	panel.add (Npanel, BorderLayout.CENTER);

	//adds the panel to the JFrame
	content.add (panel, BorderLayout.NORTH);

	//creates a new board class, which was created within the graph cllass. Its purpose is to draw everything on it and to get x and y coords from it.
	Board board = new Board ();
	//adds it to the JFrame
	GraphPanel.add (board, BorderLayout.CENTER);
	content.add (xy, BorderLayout.EAST);
	content.add (GraphPanel, BorderLayout.CENTER);

	//puts jFrame in the middle of the screen
	this.setLocationRelativeTo (null);
	//Makes JFrame seen
	this.setVisible (true);


    }


    //JPanel method that return an intialized JPanel for the all the JPanels created
    public JPanel panel ()
    {
	JPanel panel1 = new JPanel ();

	panel1.setLayout (new BorderLayout ());

	return panel1;

    }


    //JButton method that returns a button for linear equation
    public JButton button (String name)
    {

	JButton button1 = new JButton (name);

	//adds what happens when button is clicked
	button1.addActionListener (new ActionListener ()
	{
	    public void actionPerformed (ActionEvent e)
	    {

		//when repaint|() is used it will draw the linear equation
		linearDraw = true;
		//gets slope and y intercept
		m = Double.parseDouble (slope.getText ());

		yint = Double.parseDouble (y_int.getText ());

		//if the quadratic function has been entered it will find the point of intersections with a method
		if (quadraticDraw)
		    getPoi ();
		//draws the linear equation
		repaint ();

	    }
	}
	);
	return button1;

    }


    //JButton method that returns a button for quadratic equation
    public JButton button2 (String name)
    {

	JButton button1 = new JButton (name);

	button1.addActionListener (new ActionListener ()
	{
	    public void actionPerformed (ActionEvent e)
	    {
		//when repaint is used it will draw the quadratic equation



		a = Double.parseDouble (Ainput.getText ());

		b = Double.parseDouble (Binput.getText ());

		c = Double.parseDouble (Cinput.getText ());
		if (a != 0 || b != 0 || c != 0)
		{


		    //gets values of quadratic coeeficients and y-intercept
		    quadraticDraw = true;
		    //finds the zeros of the equation
		    getZeros ();
		    //gets point of intersections if linear eqaution has been drawn
		    if (linearDraw)
			getPoi ();

		}
		else
		{
		    quadraticDraw = false;
		}
		repaint ();

	    }
	}
	);
	return button1;

    }


    //method that outputs the zeros of the quadratic equation
    public void getZeros ()
    {
	//get zeros of the quadratic equation using the quadratic formula
	zero [0] = (-b + Math.sqrt ((b * b) - (4 * a * c))) / (2 * a);
	zero [1] = (-b - Math.sqrt ((b * b) - (4 * a * c))) / (2 * a);
	//rounds zero results to the nearest hundreth
	zero [0] = Math.round (zero [0] * 100.0) / 100.0;
	zero [1] = Math.round (zero [1] * 100.0) / 100.0;
	//outputs the zeros in a label after changing the zeros from doubles to Strings
	zeros.setText ("The zeros " + Double.toString (zero [0]) + " , " + Double.toString (zero [1]));
    }


    public void getPoi ()
    {
	//creates a new quadratic equation for the point of intersections using the differnece of the quadratic equation when the linear equation is subtracted from it
	double nb, nc;
	//new b coefficent and new y-intercept values
	nb = b - m;
	nc = c - yint;

	//finds the zeros of this new quadratic equation , which are the point of intersections, using the quadratic formula
	poi [0] [0] = (-nb - Math.sqrt ((nb * nb) - (4 * a * nc))) / (2 * a);
	poi [1] [0] = (-nb + Math.sqrt ((nb * nb) - (4 * a * nc))) / (2 * a);

	//Using the zeros, finds the y corrds of the point of the intersections
	poi [0] [1] = m * poi [0] [0] + yint;
	poi [1] [1] = m * poi [1] [0] + yint;

	//rounds the values to the nearest hundreth
	poi [0] [0] = Math.round (poi [0] [0] * 100.0) / 100.0;
	poi [1] [0] = Math.round (poi [1] [0] * 100.0) / 100.0;
	poi [0] [1] = Math.round (poi [0] [1] * 100.0) / 100.0;
	poi [1] [1] = Math.round (poi [1] [1] * 100.0) / 100.0;

	//outputs the point of intersections in a label after changing the values to strings
	pois.setText (" The point of intersections x1=" + Double.toString (poi [0] [0]) + " ,y1= " + Double.toString (poi [0] [1]) + " and x2=" + Double.toString (poi [1] [0]) + " ,y2= " + Double.toString (poi [1] [1]));

    }


    //sets up the textfields
    public JFormattedTextField textfield ()
    {
	//makes textfield only have real number inputs
	NumberFormat amountFormat = NumberFormat.getNumberInstance ();
	JFormattedTextField amountField = new JFormattedTextField (amountFormat);
	//sets intial values of the textfields
	amountField.setValue (new Double (0));
	//sets up how big the textfield is
	amountField.setColumns (3);

	return amountField;
    }


    //intializes the labels
    public JLabel lab (String name)
    {
	JLabel label = new JLabel (name);
	//sets up font for the label
	label.setFont (font);

	return label;


    }


    //class which acts as a component, which will be used to draw all the title page
    public class titlepage extends JComponent
    {

	public Dimension getPreferredSize ()
	{
	    return new Dimension (550, 600);
	}


	public void paint (Graphics g)
	{
	    //calls paintComponent method from the super class
	    super.paintComponent (g);
	    //sets color
	    g.setColor (Color.white);
	    //background of the graph
	    g.fillRect (0, 0, 550, 600);
	    g.setColor (Color.black);
	    g.setFont (font2);
	    g.drawString ("Graphs Quadratic and Linear equations", 0, 275);
	}

    }


    //class which acts as a component, which will be used to draw all the grpah and equation stuff
    public class Board extends JComponent
    {

	public Board ()
	{
	    //adds the mouse motion listenor specifically to the Board class
	    this.addMouseMotionListener (new MouseMotionAdapter ()
	    {
		//when mouse is dragged this method is used, but is not used in this program
		public void mouseDragged (MouseEvent e)
		{

		}
		//if the mouse is moved this method is used, used to output the x and y values of the mouse when on the graph
		public void mouseMoved (MouseEvent e)
		{
		    //gets x and y values for the component and converts it so that the values match the graph x and y coords
		    x = (e.getX () / 10.0) - 20.0;
		    y = (20.0 - (e.getY () / 10.0));
		    //rounds the graph x and y coords to the nearest hundreth
		    x = Math.round (x * 10000.0) / 10000.0;
		    y = Math.round (y * 10000.0) / 10000.0;
		    //changes the values the strings and outputs them in a label
		    xy.setText (Double.toString (x) + " , " + Double.toString (y));

		}




	    }
	    );


	}
	//sets the components size
	public Dimension getPreferredSize ()
	{
	    return new Dimension (415, 415);
	}
	//paint method, where all the graphics methods and statement will be used and draw with
	public void paint (Graphics g)
	{
	    //calls paintComponent method from the super class
	    super.paintComponent (g);
	    //sets color
	    g.setColor (Color.white);
	    //background of the graph
	    g.fillRect (0, 0, 415, 415);

	    //draws graph
	    drawGraph (g);
	    //draws linear equation if linearDraw is true
	    if (linearDraw)
		drawLinear (g);
	    //draws quadratic equation if quadraticDraw is true
	    if (quadraticDraw)
		drawQuadratic (g);
	}

	public void drawGraph (Graphics g)
	{

	    //sets color to black
	    g.setColor (Color.black);
	    //draws the x axis
	    g.drawLine (0, 200, 400, 200);
	    //draws the y axis
	    g.drawLine (200, 0, 200, 400);
	    //creates a for loop that runs 20 times
	    for (int i = 0 ; i < 21 ; i = i + 1)
	    {
		//draws a 20 pixel line perpendicular on the x axis every 20 pixels
		g.drawLine (i * 20, 190, i * 20, 210);
		//draws a 20 pixel line perpendicular on the y axis every 20 pixels
		g.drawLine (190, i * 20, 210, i * 20);
		//when the for loop is not on the 10th run, then the numbers are drawn to the graph
		if (i != 10)
		{
		    //changes the i value to match the graph number, and draws each value to match the previous lines drawn, the coordinates of the graph
		    g.drawString (Integer.toString ((i - 10) * 2), i * 20, 220);
		    g.drawString (Integer.toString ((10 - i) * 2), 213, (i * 20) + 8);
		}
	    }

	}
	//draws the linear eqautions



    }





    public void drawLinear (Graphics g)
    {
	g.setColor (Color.blue);
	//a new y1 and y2
	double y1, y2;
	//using the linear equation and using minimum and maximum x values, finds two y values
	y1 = (m * (-20) + yint);
	y2 = (m * (20) + yint);
	//After converting the y values to component pixel values,draws a line between the two sets of coordinates that represents the linearequation
	g.drawLine (0, (int) ((20 - y1) * 10), 400, (int) ((20 - y2) * 10));

    }


    public void drawQuadratic (Graphics g)
    {
	g.setColor (Color.red);
	double y;
	//creates a for loop that uses every 0.0001 x value
	for (double i = -20.0 ; i < 20.0001 ; i = i + 0.0001)
	{
	    //finds a y value for each x value using the quadratic equation inputed
	    y = a * (i * i) + (b * i) + c;
	    //Converts each x and y graph value to component pixel values and draws a 1 by 1 oval for each set of coords
	    g.fillOval ((int) ((i + 20) * 10), (int) ((20 - y) * 10), 1, 1);
	}

    }





}
// Graph class
