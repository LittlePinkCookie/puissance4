package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IHM extends JFrame {
	
	public IHM() {
		this.setLayout(new BorderLayout());
		this.setTitle("PUISSANCE 4");
		
		//Bannière de titre
		JLabel banner = new JLabel("PUISSANCE 4");
		this.add(banner, BorderLayout.NORTH);
		
		//Zone de jeu total
		JPanel zoneJeuTotale = new JPanel(new BorderLayout());
		JLabel tourJoueur    = new JLabel("Tour : joueur 1");
		zoneJeuTotale.add(tourJoueur, BorderLayout.NORTH);
		
		//Zone de jeu
		zoneJeuTotale.add(new GrilleJeu());
		
		this.add(zoneJeuTotale);
		this.setVisible(true);
		
	}
}
