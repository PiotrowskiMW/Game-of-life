
package pl.super_marcin.life.model;

/**
 * Klasa implementująca model
 * @author super_marcin
 *
 */
public class Model
{
	private final Plansza planszaGry;
	
	public Model(int size)
	{
		planszaGry = new Plansza(size);
		
	}
	/**
	 * Funkcja zwracająca stan komórki i j
	 * @param i wiersz
	 * @param j kolumna
	 * @return
	 */
	public boolean stan(int i, int j)
	{
		return planszaGry.sprawdzKomorka(i, j);
	}
	/**
	 * Funkcja zmieniająca stan komórki i j na przeciwny
	 * @param i wiersz
	 * @param j kolumna
	 */
	public void przelacz(int i, int j)
	{
		planszaGry.przelaczKomoroka(i, j);
	}
	/**
	 * Funkcja generująca losową planszę
	 */
	public void getRandomBoard()
	{
		planszaGry.randomBoard();;
	}
	/**
	 * Funkcja powodująca pojedyncze przejście po planszy
	 */
	public void iteracja()
	{
		planszaGry.iteruj();
	}
	/**
	 * Funkcja zerująca planszę
	 */
	public void erase()
	{
		planszaGry.zeruj();
	}
}
