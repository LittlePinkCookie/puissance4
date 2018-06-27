package game;

public class Puissance4 {

	//Attributs
	public final static int WIDTH  = 7;
	public final static int HEIGHT = 6;
	
	public final static char PAWN_1 = 'X';
	public final static char PAWN_2 = 'Y';
	
	private char[][] grid;
	private char     winner;
	private boolean  win;
	private int      round;
	
	
	//Constructeur
	public Puissance4() {
		init();
	}
	
	
	/*
	 * Place un pion dans la grille
	 */
	public boolean placer(int col) {
		
		char pawn;
		if (this.round == 0) pawn = Puissance4.PAWN_1;
		else                 pawn = Puissance4.PAWN_2;
		
		int indLig = 1;
		col++;
		
		// Trouve le dernier pion de la colonne
		for (indLig = 1; indLig < this.grid.length; indLig++)
			if (this.grid[indLig][col] != ' ')
				break;
		
		indLig--;
		
		if (indLig >= 1) {
			this.grid[indLig][col] = pawn;
			if (testVictory(pawn, indLig, col))
				win(pawn);
			
			this.round = (this.round+1) % 2;
			return true;
		} else {
			return false;
		}
	}
	
	
	/*
	 * Termine le jeu
	 */
	private void win(char winner) {
		this.winner = winner;
		this.win    = true;
	}
	
	
	
	/*
	 * Compte le nombre de pions dans la direction donnée
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
	
	
	/*
	 * Compare deux valeurs
	 */
	private int maxPawn(int a, int b) {
		if (a > b) return a;
		else       return b;
	}

	
	/*
	 * Test si le placement du pion entraîne la victoire
	 */
	public boolean testVictory(char pawn, int lig, int col) {
		
		return (maxPawn(nbPawnDirection(pawn, lig, col, 0, 1), //Test Horizontal
					maxPawn(nbPawnDirection(pawn, lig, col, 1, 0), //Test Vertical
						maxPawn(nbPawnDirection(pawn, lig, col, 1, 1), //Diagonal bottom left to top right
								nbPawnDirection(pawn, lig, col, -1, -1)))) == 3); //Diagonal bottom right to top left 
	}
	
	
	/*
	 * (ré)initialise le jeu
	 */
	public void init() {
		this.grid = new char[][] {{ '#', '#', '#', '#', '#', '#', '#', '#', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
								  { '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
								  
		this.round  = 0;
		this.win    = false;
		
	}
	
	
	
	//=====================//
	// ACCESSEURS          //
	//=====================//
	public boolean isWon()      { return this.win;    }
	public char    getWinner()  { return this.winner; }
	public int     getRound()   { return this.round;  }
	public char[][] getGrille() { return this.grid;   }
	
	
	
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
}