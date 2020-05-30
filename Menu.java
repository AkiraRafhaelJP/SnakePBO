import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenu menu;
	private JMenuItem i1, i2;
	
	public Menu() {
		menu = new JMenu("Option");
		i1 = new JMenuItem("High Score");
		i2 = new JMenuItem("Exit Game");
		i1.addActionListener(this);
		i2.addActionListener(this);
		
		menu.add(i1); menu.add(i2);
		add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == i1) {
			new ScoreBoard(new Score(0));
		}
		if(e.getSource() == i2) {
			System.exit(0);
		}
	}
}
