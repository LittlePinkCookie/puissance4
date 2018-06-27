package controler;

import game.Puissance4;
import view.GrilleJeu;
import view.IHM;

public class Controler {

	private Puissance4 p;
	private IHM        ihm;
	
	public Controler() {
		
		this.p   = new Puissance4(); 
		this.ihm = new IHM(this);
	}
	
	public void placer(int x) {
		if (this.p.placer(x)) {
			this.ihm.refreshGame();
			
			if (this.p.isWon())
				this.ihm.victory(this.p.getRound()+1);
			else
				this.ihm.setRound(this.p.getRound()+1);
		}
	}
	
	public Puissance4 getJeu() { return this.p; }
	
	
	public static void main(String[] args) {
		new Controler();
	}
}
