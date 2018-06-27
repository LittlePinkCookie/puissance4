package game;

public class Puissance4 {

	//Attributs
	private char[][] grid;
	private char     winner;
	private boolean  win;
	
	
	//Constructeur
	public Puissance4() {
		this.grid = new char[][] {{ '#', '#', '#', '#', '#', '#', '#', '#', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
	}
	
	
	/**
	 * Place un pion dans la grille
	 * @param pawn type de pion
	 * @param col colonne cible
	 * @return Possibilité de le placer
	 */
	public boolean placer(char pawn, int col) {
		
		int indLig = 1;
		col++;
		
		// Trouve le dernier pion de la colonne
		for (indLig = 1; indLig < this.grid.length; indLig++)
			if (this.grid[indLig][col] != ' ')
				break;
		
		indLig--;
		
		if (indLig >= 1) {
			this.grid[indLig][col] = pawn;
			if (testVictory(pawn, indLig, col)) {
				win(pawn);
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Termine le jeu
	 * @param winner 
	 */
	private void win(char winner) {
		this.winner = winner;
		this.win    = true;
	}
	
	
	
	/**
	 * Compte le nombre de pions dans la direction donnée
	 * @param pawn type de pion
	 * @param lig position en ordonnée du pion posé
	 * @param col position en abscisse du pion posé
	 * @param dirLig direction en ordonnée
	 * @param dirCol direction en abscisse
	 * @return Nombre de pions identiques
	 */
	private int nbPawnDirection(char pawn, int lig, int col, int dirLig, int dirCol) {
		int l, c;
		int nbPawn = 0;
		
		//Sens 
		l = lig + dirLig;
		c = col + dirCol;
		while (this.grid[l][c] == pawn) {
			nbPawn++;
			l = l + dirLig;
			c = c + dirCol;
		}
		
		//Sens inverse
		l = lig - dirLig;
		c = col - dirCol;
		while (this.grid[l][c] == pawn) {
			nbPawn++;
			l = l - dirLig;
			c = c - dirCol;
		}
		
		return nbPawn;
	}
	
	
	/**
	 * Compare deux valeurs
	 * @param a valeur 1
	 * @param b valeur 2
	 * @return La plus grande des deux
	 */
	private int maxPawn(int a, int b) {
		if (a > b) return a;
		else       return b;
	}

	
	/**
	 * Test si le placement du pion entraîne la victoire
	 * @param pawn type de pion
	 * @param lig position en ordonnée du pion
	 * @param col position en abscisse du pion
	 * @return Victoire
	 */
	public boolean testVictory(char pawn, int lig, int col) {
		
		return (maxPawn(nbPawnDirection(pawn, lig, col, 0, 1), //Test Horizontal
					maxPawn(nbPawnDirection(pawn, lig, col, 1, 0), //Test Vertical
						maxPawn(nbPawnDirection(pawn, lig, col, 1, 1), //Diagonal bottom left to top right
								nbPawnDirection(pawn, lig, col, -1, -1)))) == 3); //Diagonal bottom right to top left 
	}
	
	
	
	//=====================//
	// ACCESSEURS          //
	//=====================//
	public boolean isWon()     { return this.win;    }
	public char    getWinner() { return this.winner; }
	
	
	
	public String toString() {
		String s = "";
		
		for (int i = 1; i < this.grid.length-1; i++) {
			s += "|";
			for (int j = 1; j < this.grid[0].length-1; j++)
				s += this.grid[i][j] + "|";
			
			s += "\n";
		}
		
		return s;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		Puissance4 p = new Puissance4();
		
		p.placer('Y', 0);
		p.placer('Y', 0);
		p.placer('Y', 1);
		p.placer('Y', 1);
		p.placer('Y', 1);
		p.placer('Y', 1);
		
		
		if (p.isWon())
			System.out.println("Le joueur " + p.getWinner() + " a gagné");

		System.out.println(p);
	}
}