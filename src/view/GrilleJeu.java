package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import controler.Controler;
import game.Puissance4;

public class GrilleJeu extends JPanel implements MouseListener, MouseMotionListener{

	public final static int WIDTH_CASE  = 100;
	public final static int HEIGHT_CASE = 100;
	public final static int BORDER      = 100;
	
	private Controler ctrl;
	
	private int pawnPreviewX;
	
	public GrilleJeu(Controler ctrl) {
		this.ctrl = ctrl;
		this.setPreferredSize(new Dimension(GrilleJeu.WIDTH_CASE * Puissance4.WIDTH + GrilleJeu.BORDER , 
				                            GrilleJeu.HEIGHT_CASE * Puissance4.HEIGHT + GrilleJeu.BORDER ));
		
		this.pawnPreviewX = GrilleJeu.BORDER/2;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.red);
		this.setVisible(true);
	}
	
	//Dessine le panel
	public void paint(Graphics g) {
		drawBackground(g);
		drawBlank(g);
		drawPawns(g);
		drawPawnPreview(g);

		this.setBackground(Color.BLUE);
	}
	
	
	/*
	 * Dessine la partie bleue du jeu
	 */
	private void drawBackground(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	/*
	 * Dessine les trous
	 */
	private void drawBlank(Graphics g) {
		g.setColor(Color.WHITE);
		for (int i = 0; i < Puissance4.HEIGHT; i++)
			for (int j = 0; j < Puissance4.WIDTH; j++)
				g.fillOval(j * GrilleJeu.HEIGHT_CASE + GrilleJeu.BORDER/2, 
						   i * GrilleJeu.WIDTH_CASE + GrilleJeu.BORDER, 
						   GrilleJeu.WIDTH_CASE, 
						   GrilleJeu.HEIGHT_CASE);
	}
	
	/*
	 * Dessine les pions
	 */
	private void drawPawns(Graphics g) {
		if (this.ctrl.getJeu().getGrille() == null) return;
		
		for (int i = 1; i < this.ctrl.getJeu().getGrille().length-1; i++) {
			for (int j = 1; j < this.ctrl.getJeu().getGrille()[0].length-1; j++) {
				if (this.ctrl.getJeu().getGrille()[i][j] == ' ') continue;
				if (this.ctrl.getJeu().getGrille()[i][j] == Puissance4.PAWN_1) g.setColor(Color.RED);
				if (this.ctrl.getJeu().getGrille()[i][j] == Puissance4.PAWN_2) g.setColor(Color.YELLOW);
				
				g.fillOval((j-1) * GrilleJeu.HEIGHT_CASE + GrilleJeu.BORDER/2, 
						   (i-1) * GrilleJeu.WIDTH_CASE + GrilleJeu.BORDER, 
						   GrilleJeu.WIDTH_CASE, 
						   GrilleJeu.HEIGHT_CASE);
			}
		}	
	}
	
	
	/*
	 * Dessine le pion au dessus de la grille
	 */
	private void drawPawnPreview(Graphics g) {
		if (this.ctrl.getJeu().getRound() == 0) g.setColor(Color.RED);
		else                                    g.setColor(Color.YELLOW);
		
		g.fillOval(this.pawnPreviewX - GrilleJeu.WIDTH_CASE/2, 0, GrilleJeu.WIDTH_CASE, GrilleJeu.HEIGHT_CASE);
	}
	


	public void mouseClicked(MouseEvent e) {
		int x = (e.getX()-GrilleJeu.BORDER/2) / GrilleJeu.WIDTH_CASE;
		
		if (!this.ctrl.getJeu().isWon())
			this.ctrl.placer(x);
	}
	
	public void mouseEntered  (MouseEvent e) {}
	public void mouseExited   (MouseEvent e) {}
	public void mousePressed  (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	public void mouseDragged  (MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		if (!this.ctrl.getJeu().isWon()) {
			this.pawnPreviewX = e.getX();
			repaint();
		}		
	}
}
