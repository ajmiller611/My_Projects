package scheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CalendarComponent {
	static Calendar calendar = new GregorianCalendar(new Locale(System.getProperty("user.language"), System.getProperty("user.country")));
	JTable table;
	DefaultTableModel tableModel;
	Dimension tableDimension;
	
	
	public CalendarComponent() {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(250, 220));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(createGUI());
		frame.setVisible(true);
	}
	
	private JPanel createGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(200, 120));
		
		String[] daysOfTheWeek = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
		
		/*
		 * Create a table model that overrides the method isCellEditable
		 * so all cells in the table can't be edited by the user.
		 */
		tableModel = new DefaultTableModel(null, daysOfTheWeek) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		//Setup JTable with certain JTable features turned off
		table = new JTable(tableModel);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.LIGHT_GRAY));
		table.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.LIGHT_GRAY));
		createMonth();
		//Dimension tableDimension = table.getSize();
		System.out.println(tableDimension.toString());
		
		DefaultTableCellRenderer centerAlignmentRenderer = new DefaultTableCellRenderer();
		centerAlignmentRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		//table.getColumnModel().getColumn(0).setCellRenderer(centerAlignmentRenderer);
		
		//table.setDefaultRenderer(Integer.class, centerAlignmentRenderer);
		
		
		
		JScrollPane pane = new JScrollPane(table);
		//pane.setPreferredSize(new Dimension(215, 120));
		pane.setPreferredSize(tableDimension);
		
		mainPanel.add(pane);
		
		return mainPanel;
	}
	
	private void createMonth() {
		
		System.out.println(calendar.getTime().toString());
		
		
		System.out.println(Calendar.DAY_OF_MONTH);
		System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		
		//Object[][] data = new Object[calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)][7];
		
		int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
		int numberOfDaysInTheMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(firstDayOfMonth);
		tableModel.setRowCount(calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
		
		DefaultTableCellRenderer centerAlignmentRenderer = new DefaultTableCellRenderer();
		centerAlignmentRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		int i = firstDayOfMonth - 1;
		for(int a = 1; a <= numberOfDaysInTheMonth; a++) {
			//data[i/7][i%7] = a;
			tableModel.setValueAt(new Integer(a), i/7, i%7);
			table.getColumnModel().getColumn(i%7).setCellRenderer(centerAlignmentRenderer);
			i = i + 1;
		}
		
		int rows = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		tableDimension = new Dimension(215, 21 * rows);
		//return data;
	}
	
	
	
	
	
	public static void main(String[] args) {
		CalendarComponent cal = new CalendarComponent();
		
		
	}
}
