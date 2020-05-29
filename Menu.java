import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenu menu;
	private JMenuItem i1, i2, i3;
	
	public Menu() {
		menu = new JMenu("Option");
		i1 = new JMenuItem("Start Game");
		i2 = new JMenuItem("High Score");
		i3 = new JMenuItem("Exit Game");
		i1.addActionListener(this);
		i2.addActionListener(this);
		i3.addActionListener(this);
		
		menu.add(i1); menu.add(i2); menu.add(i3);
		add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == i1) {
			
		}
		if(e.getSource() == i2) {
			
		}
		if(e.getSource() == i3) {
			System.exit(0);
		}

	}
	
}
