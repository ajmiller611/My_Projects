package scheduler;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI {
	JFrame frame;
	
	public GUI() {
		initializeUI();
	}
	
	public void initializeUI() {
		frame = new JFrame("Appointment Scheduler");
		frame.setSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		
		JPanel homePanel = new JPanel();
		homePanel.setPreferredSize(new Dimension(400, 400));
		homePanel.setBackground(Color.LIGHT_GRAY);
		
		JPanel doctorPanel = new JPanel();
		BoxLayout layout = new BoxLayout(doctorPanel, BoxLayout.PAGE_AXIS);
		doctorPanel.setLayout(layout);
		doctorPanel.setPreferredSize(new Dimension(170, 150));
		doctorPanel.setBackground(Color.CYAN);
		
		JLabel createDoctorLabel = new JLabel("<html><p style=\"font-size:13px\">Create a new doctor object and add it to the list of doctors.</p></html>");
		createDoctorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		doctorPanel.add(createDoctorLabel);
		
		doctorPanel.add(Box.createVerticalGlue());
		
		JButton createDoctorButton = new JButton("Create Doctor");
		createDoctorButton.setToolTipText("Create a new Doctor");
		createDoctorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		doctorPanel.add(createDoctorButton);
		doctorPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		homePanel.add(doctorPanel);
		
		JPanel patientPanel = new JPanel();
		layout = new BoxLayout(patientPanel, BoxLayout.PAGE_AXIS);
		patientPanel.setLayout(layout);
		patientPanel.setPreferredSize(new Dimension(170, 150));
		patientPanel.setBackground(Color.GREEN);
		
		JLabel createPatientLabel = new JLabel("<html><p style=\"font-size:13px\">Create a new patient object.</p></html>");
		createPatientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		patientPanel.add(createPatientLabel);
		
		patientPanel.add(Box.createVerticalGlue());
		
		JButton createPatientButton = new JButton("Create Patient");
		createPatientButton.setToolTipText("Create a new Patient");
		createPatientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		patientPanel.add(createPatientButton);
		patientPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		homePanel.add(patientPanel);
		
		JPanel appointmentPanel = new JPanel();
		layout = new BoxLayout(appointmentPanel, BoxLayout.PAGE_AXIS);
		appointmentPanel.setLayout(layout);
		appointmentPanel.setPreferredSize(new Dimension(170, 150));
		appointmentPanel.setBackground(Color.MAGENTA);
		
		JLabel makeAppointmentLabel = new JLabel("<html><p style=\"font-size:13px\">Make an appointment with a doctor.</p></html>");
		makeAppointmentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		appointmentPanel.add(makeAppointmentLabel);
		
		appointmentPanel.add(Box.createVerticalGlue());
		
		JButton makeAppointmentButton = new JButton("Make An Appointment");
		makeAppointmentButton.setToolTipText("Make an appointment with a Doctor");
		makeAppointmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		appointmentPanel.add(makeAppointmentButton);
		appointmentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		homePanel.add(appointmentPanel);
		
		mainPanel.add(homePanel);
		
		frame.add(mainPanel);
	}
	
	public void showGUI() {
		frame.setVisible(true);
	}
	
}
