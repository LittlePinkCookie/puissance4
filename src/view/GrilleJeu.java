package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GrilleJeu extends JPanel {

	public GrilleJeu() {
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(Color.RED);
		
		this.setVisible(true);
	}
}
