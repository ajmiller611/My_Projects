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

public class MakeAppointmentGUI {
	
	public MakeAppointmentGUI() {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(createGUI());
		frame.setVisible(true);
	}
	
	public JPanel createGUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		mainPanel.setSize(new Dimension(400, 400));
		mainPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel titleLabel = new JLabel("Make a New Appointment");
		Font titleLabelFont = titleLabel.getFont();
		titleLabel.setFont(new Font(titleLabelFont.getFontName(), Font.PLAIN, 20));
		
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.PAGE_START;
		mainPanel.add(titleLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JLabel doctorLabel = new JLabel("Choose a Doctor:");
		Font firstNameLabelFont = doctorLabel.getFont();
		doctorLabel.setFont(new Font(firstNameLabelFont.getFontName(), Font.PLAIN, 16));
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(0, 0, 0, 50);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(doctorLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JComboBox doctorComboBox = new JComboBox();
		doctorComboBox.setPreferredSize(new Dimension(150, 20));
		doctorComboBox.setMinimumSize(new Dimension(150, 20));
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.weighty = 0.5;
		mainPanel.add(doctorComboBox, constraints);
		
		constraints = new GridBagConstraints();
		
		JLabel patientLabel = new JLabel("Choose a Patient:");
		Font lastNameLabelFont = patientLabel.getFont();
		patientLabel.setFont(new Font(lastNameLabelFont.getFontName(), Font.PLAIN, 16));
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(0, 0, 0, 50);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(patientLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JComboBox patientComboBox = new JComboBox();
		patientComboBox.setPreferredSize(new Dimension(150, 20));
		patientComboBox.setMinimumSize(new Dimension(150, 20));
		constraints.gridy = 2;
		constraints.gridx = 1;
		constraints.weighty = 0.5;
		mainPanel.add(patientComboBox, constraints);
		
		constraints = new GridBagConstraints();
		
		JLabel dateLabel = new JLabel("Choose a Date:");
		Font doctorLabelFont = dateLabel.getFont();
		dateLabel.setFont(new Font(doctorLabelFont.getFontName(), Font.PLAIN, 16));
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.weighty = 0.5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(dateLabel, constraints);
		
		constraints = new GridBagConstraints();
		
		JComboBox date = new JComboBox();
		date.setPreferredSize(new Dimension(150, 20));
		date.setMinimumSize(new Dimension(150, 20));
		constraints.gridy = 3;
		constraints.gridx = 1;
		constraints.weighty = 0.5;
		//constraints.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(date, constraints);
		
		constraints = new GridBagConstraints();
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		
		JButton createDoctorButton = new JButton("<html>Create<br>Appointment</html>");
		createDoctorButton.setPreferredSize(new Dimension(130, 40));
		createDoctorButton.setToolTipText("Create an Appointment");
		buttonPanel.add(createDoctorButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.setPreferredSize(new Dimension(100, 40));
		goBackButton.setToolTipText("Go back to the Main Menu");
		buttonPanel.add(goBackButton);
		
		mainPanel.add(buttonPanel, constraints);
		
		return mainPanel;
	}
	
	public static void main(String[] args) {
		MakeAppointmentGUI gui = new MakeAppointmentGUI();
	}
}
