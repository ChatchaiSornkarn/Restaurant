package ResultPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Blocks extends JPanel {

	/**
	 * Create the panel.
	 */
	public Blocks() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.EAST);

	}

}
