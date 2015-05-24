package pl.super_marcin.life.actionMessage;

/**
 * Klasa przesyłająca informacje o kliknięciu na planszę
 * @author super_marcin
 *
 */
public class ActionMessageMousePress extends ActionMessage{
	int i;
	int j;
	public ActionMessageMousePress(int i, int j)
	{
		this.i = i;
		this.j = j;
	}
	/**
	 * Funkcja zwracająca wiersz
	 * @return
	 */
	public int geti()
	{
		return i;
	}
	/**
	 * Funkcja zwracająca kolumnę
	 * @return
	 */
	public int getj()
	{
		return j;
	}

}
