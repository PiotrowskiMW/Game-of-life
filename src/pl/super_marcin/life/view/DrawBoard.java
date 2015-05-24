package pl.super_marcin.life.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import pl.super_marcin.life.model.Model;
/**
 * Klasa rysująca plansze gry
 * @author super_marcin
 *
 */
class DrawBoard  extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9087977403111895342L;
	private final int boardSize;
	private final int cellSize;
	private final Model modelLink;
	/**
	 * Konstruktor
	 * @param size wielkość boku planszy
	 * @param model odnośnik na Model
	 */
	public DrawBoard(int size,int cellSize, Model model) {
		boardSize = size;
		modelLink=model;
		this.cellSize = cellSize;
	}
	/**
	 * Funkcja rysująca planszę
	 */
	@Override
    public void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0,0,boardSize*cellSize,boardSize*cellSize);
		int j=0;
		int i=0;
		for(i=0;i<boardSize;i++)
		for(j=0;j<boardSize;j++)
		{
			if(modelLink.stan(i,j) == true)
			{
				g.setColor(Color.YELLOW);
			}
			else
			{
				g.setColor(Color.LIGHT_GRAY);
			}
			g.fillRect(i*cellSize,j*cellSize, cellSize-1, cellSize-1);
		}
    }
}