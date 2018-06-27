package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controler.Controler;
import game.Puissance4;

public class IHM extends JFrame implements ActionListener {
	
	//Attributs
	private GrilleJeu grid;
	private Controler ctrl;
	
	private JLabel lblRound;
	private JButton btRestart;
	
	public IHM(Controler ctrl) {
		this.ctrl = ctrl;
		
		this.setLayout(new BorderLayout(0, 15));
		this.setTitle("PUISSANCE 4");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Bannière de titre
		JLabel banner = new JLabel(new ImageIcon("images/logo.png"));
		this.add(banner, BorderLayout.NORTH);
		
		//Zone de jeu total
		JPanel zoneJeuTotale = new JPanel(new BorderLayout());
		lblRound = new JLabel("Tour : joueur 1", SwingConstants.CENTER);
		zoneJeuTotale.add(lblRound, BorderLayout.NORTH);
		
		//Zone de jeu
		this.grid = new GrilleJeu(this.ctrl);
		zoneJeuTotale.add(this.grid);
		this.add(zoneJeuTotale);
		
		
		//Zone du bas
		//JPanel zoneBoutons = new JPanel()
		this.btRestart = new JButton("Recommencer");
		this.btRestart.addActionListener(this);
		this.btRestart.setPreferredSize(new Dimension(GrilleJeu.WIDTH_CASE*Puissance4.WIDTH+GrilleJeu.BORDER, 75));
		this.add(this.btRestart, BorderLayout.SOUTH);
		
		
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}
	
	/*
	 * Actualise la grille de jeu
	 */
	public void refreshGame() { this.grid.repaint(); }
	
	
	/*
	 * Actualise le tour du joueur
	 */
	public void setRound(int round) {
		this.lblRound.setText("Tour : joueur " + round);
	}
	
	/*
	 * Ouvre un pop-up de victoire
	 */
	public void victory(int round) {
		JOptionPane.showMessageDialog(
				null, 
				"Le joueur " + round + " remporte la partie !", 
				"Partie terminée",
				JOptionPane.INFORMATION_MESSAGE
			);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btRestart) {
			this.ctrl.getJeu().init();
			this.grid.repaint();
		}
			
	}
}
