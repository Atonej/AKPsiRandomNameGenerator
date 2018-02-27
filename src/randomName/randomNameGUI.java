package randomName;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/***********************************************************************
 * This class is the GUI interface that will show the brothers of the 
 * week
 * 
 * @author Atone Joryman
 * @version February 2nd,2018
 *
 **********************************************************************/
public class randomNameGUI extends JFrame implements ActionListener {
	/** use for loading and saving a file **/
	private static final long serialVersionUID = 1L;
	
	/**generate button**/
	private JButton generate;
	
	/**reset button**/ 
	private JButton reset;
	
	/**show the paired names**/
	private JLabel names;
	
	/**show the current attendance**/
	private JLabel count;
	
	/**arraylist of checkboxes**/
	private ArrayList<JCheckBox> bros = new ArrayList<JCheckBox>();
	
	/**access the NameAKPsi class**/
	private randomNameAKPSI AKPsi;
	
	/**for names**/
	private JPanel namePanel;
	
	/**selected checkboxes**/
	private JCheckBox choose;
	
	/**for buttons**/
	private JPanel resultPanel;
	
	/**display the instructions**/
	private JPanel topPanel;
	
	/**show the paired people**/
	private JPanel pairPanel;

	/**show the matches**/
	private JLabel match;
	
	/**currentName on array**/
	private String[] currentName;
	
	/**the only brother who won't be paired**/
	private String only;
	
	/**keeping track of who is left**/
	private int keepTrack;

	/**button for setting the attendance**/
	private JButton set;
	
	/**see if set attendance was hit**/
	private boolean require;
	
	/*******************************************************************
	 * This constructor is the holder of the interface that would be
	 * shown users
	 ******************************************************************/
	public randomNameGUI() {
		//intialize panels
		topPanel = new JPanel();
		namePanel = new JPanel();
		resultPanel = new JPanel();
		pairPanel = new JPanel();
		
		require = false;

		//set layouts
		namePanel.setLayout(new GridLayout(20, 5));
		pairPanel.setLayout(new GridLayout(10, 2));
		//initialize the base class
		AKPsi = new randomNameAKPSI();

		//update tracking
		keepTrack = AKPsi.getCount();
				
		generate = new JButton("Generate");
		reset = new JButton("Reset");
		set = new JButton("Set Attendance");

		//assign strings
		names = new JLabel(
				"Names of current Chapter Members are below please "
						+ "check off anyone who is not here for "
						+ "brother of the week and then set the "
						+ "attendance:\n ");
		names.setForeground(Color.blue);

		count = new JLabel("There are currently " + AKPsi.getCount()
				+ " Brothers in Attendance");
		count.setForeground(Color.red);

		match = new JLabel("The current match ups are:\n ");
		
		//apply the button listeners
		generate.addActionListener(this);
		reset.addActionListener(this);
		set.addActionListener(this);

		setTitle("Brother of The Week!! - Developed by Atone Joryman");

		//make assignments to panels
		topPanel.add(names);
		getBros();
		namePanel.add(count);
		pairPanel.add(match);
		resultPanel.add(set);
		resultPanel.add(generate);
		resultPanel.add(reset);
		

		//put onto the frame
		add(topPanel, BorderLayout.NORTH);
		add(namePanel, BorderLayout.CENTER);
		add(resultPanel, BorderLayout.SOUTH);
		add(pairPanel, BorderLayout.EAST);

		//set up basic function and display 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(1000, 700);
		validate();
		repaint();

	}

	/*******************************************************************
	 * This method will get the brothers that are either checked or 
	 * unchecked in the arraylist of checkboxes
	 ******************************************************************/
	public void getBros() {
		ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();

		// loop through array string of bros name
		for (int i = 0; i < AKPsi.getName().length; i++) {
			// put array to temp array
			String[] name = AKPsi.getName();
			// put into private field
			currentName = name;

			// set up checkboxes and make them automatically selected
			JCheckBox selected = new JCheckBox(name[i]);
			selected.setSelected(true);
			choose = selected;
			checkBoxes.add(selected);
			// private field
			bros = checkBoxes;
			//update panel
			namePanel.add(choose);

		}
		
		

	}

	/*******************************************************************
	 * This is where the action listener is applied for the mentioned 
	 * buttons
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (generate == e.getSource()) {
			if(!require) {
				JOptionPane.showMessageDialog(null, 
						"Must set the attendance first");
				return;
			}
			
			//if there is one brother left
			if (AKPsi.getIsOne()) {
				//see who is the only name left
				for (String only : currentName) {
					if (only != null) {
						this.only = only;
						break;
					}
				}
				//show message
				JOptionPane.showMessageDialog(null,
						"There is only one member: \n" + only);
				return;
			}
			
			
			//display when no more brothers
			if(AKPsi.getIsZero()) {
				JOptionPane.showMessageDialog(null, "Those are all the "
						+ "brothers of the week");
				return;
			}
			
				
			ArrayList<String> followList = AKPsi.generate();

			// stream through the pairs and collect them for jpanel
			String collect = followList.stream()
					.map(name -> name.trim())
					// joins the list by separating with a new line
					.collect(Collectors.joining("\n"));

			JLabel m = new JLabel(collect);

			this.match.setText(this.match.getText() + "\n ");
			pairPanel.add(m);

		}

		//start up another screen for brother of the week
		if (reset == e.getSource()) {
			dispose();
			new randomNameGUI();
		}
		
		//setting of attendance
		if(set == e.getSource()) {
			//required attendance is flagged free
			require = true;
			
			//further instructions
			JOptionPane.showMessageDialog(null,"Brothers Recorded: "
					+ "You may now continue");
			for (JCheckBox checkBox : bros) {
				// not selected then loop through each name to find to 
				//take out
				if (!checkBox.isSelected()) {
					for (String take : AKPsi.getName()) {
						if (checkBox.getText() == take) {
							// keep track of bros not here
							keepTrack--;
							AKPsi.makeNull(take);

						}

					}
				}
			}
			
			count.setText("There are currently " + keepTrack
					+ " Brothers in Attendance");
			//update attendance
			AKPsi.setCount(keepTrack);
		}

	}

	/*******************************************************************
	 * main program to start java application
	 * @param args
	 ******************************************************************/
	public static void main(String[] args) {
		new randomNameGUI();
	}
}
