package scheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PatientGUI {
	
	public PatientGUI() {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(createGUI());
		frame.setVisible(true);
	}
	
	private JPanel createGUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		mainPanel.setSize(new Dimension(400, 400));
		mainPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel titleLabel = new JLabel("Create a New Patient");
		Font titleLabelFont = titleLabel.getFont();
		titleLabel.setFont(new Font(titleLabelFont.getFontName(), Font.PLAIN, 20));
		
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.gridwidth = 3;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.PAGE_START;
		mainPanel.add(titleLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JLabel firstNameLabel = new JLabel("First Name:");
		Font firstNameLabelFont = firstNameLabel.getFont();
		firstNameLabel.setFont(new Font(firstNameLabelFont.getFontName(), Font.PLAIN, 16));
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(0, 0, 0, 50);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(firstNameLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JTextField firstNameTextField = new JTextField();
		firstNameTextField.setPreferredSize(new Dimension(150, 20));
		firstNameTextField.setMinimumSize(new Dimension(150, 20));
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.weighty = 0.5;
		mainPanel.add(firstNameTextField, constraints);
		
		constraints = new GridBagConstraints();
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		Font lastNameLabelFont = lastNameLabel.getFont();
		lastNameLabel.setFont(new Font(lastNameLabelFont.getFontName(), Font.PLAIN, 16));
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(0, 0, 0, 50);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(lastNameLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JTextField lastNameTextField = new JTextField();
		lastNameTextField.setPreferredSize(new Dimension(150, 20));
		lastNameTextField.setMinimumSize(new Dimension(150, 20));
		constraints.gridy = 2;
		constraints.gridx = 1;
		constraints.weighty = 0.5;
		mainPanel.add(lastNameTextField, constraints);
		
		constraints = new GridBagConstraints();
		
		JLabel doctorLabel = new JLabel("Choose a Doctor:");
		Font doctorLabelFont = doctorLabel.getFont();
		doctorLabel.setFont(new Font(doctorLabelFont.getFontName(), Font.PLAIN, 16));
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.weighty = 0.5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(doctorLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JComboBox doctorComboBox = new JComboBox();
		doctorComboBox.setPreferredSize(new Dimension(150, 20));
		doctorComboBox.setMinimumSize(new Dimension(150, 20));
		constraints.gridy = 3;
		constraints.gridx = 1;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(doctorComboBox, constraints);
		
		constraints = new GridBagConstraints();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		constraints.gridy = 4;
		constraints.gridx = 1;
		constraints.gridwidth = 2;
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		
		JButton createDoctorButton = new JButton("Create Patient");
		createDoctorButton.setPreferredSize(new Dimension(130, 30));
		createDoctorButton.setToolTipText("Create a Patient");
		buttonPanel.add(createDoctorButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.setPreferredSize(new Dimension(100, 30));
		goBackButton.setToolTipText("Go back to the Main Menu");
		buttonPanel.add(goBackButton);
		
		mainPanel.add(buttonPanel, constraints);
		
		return mainPanel;
	}
	
	public static void main(String[] args) {
		PatientGUI gui = new PatientGUI();
	}
}
