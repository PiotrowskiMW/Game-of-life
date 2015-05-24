package pl.super_marcin.life.model;

import java.util.Random;

/**
 * klasa przechowójąca plansze
 * 
 * @author super_marcin
 *
 */
public class Plansza {
	/**
	 * Stała przechowująca wielkość planszy
	 */
	private final int size;
	/**
	 * tablica przechowująca stan planszy
	 */
	Komorka[][] plansza;

	/**
	 * Tworzy plansze o zadanej wielkosci
	 * 
	 * @param size
	 *            wielkość planszy
	 */
	Plansza(final int size) {
		this.size = size;
		this.plansza = new Komorka[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				plansza[i][j] = new Komorka();
			}
		randomBoard();
	}

	void iteruj() {
		/**
		 * kopia tablicy stanu planszy planszy do policzenia nowych wartości
		 */
		Komorka[][] p = new Komorka[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				p[i][j] = new Komorka();
			}
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				p[i][j].stan = this.plansza[i][j].stan;
			}

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				int liczbaZywychSasiadow = zliczZywychSasiadow(i, j, p);
				if (p[i][j].stan == false) {
					if (liczbaZywychSasiadow == 3)
						plansza[i][j].stan = true;
					else
						plansza[i][j].stan = false;
				} else {
					if (liczbaZywychSasiadow == 2 || liczbaZywychSasiadow == 3)
						plansza[i][j].stan = true;
					else
						plansza[i][j].stan = false;
				}

			}
	}

	private int zliczZywychSasiadow(int i, int j, Komorka[][] p) {
		i += size;
		j += size;
		int liczbaZywychSasiadow = 0;
		if (p[(i - 1) % size][(j - 1) % size].stan)
			liczbaZywychSasiadow++;
		if (p[(i - 1) % size][j % size].stan)
			liczbaZywychSasiadow++;
		if (p[(i - 1) % size][(j + 1) % size].stan)
			liczbaZywychSasiadow++;
		if (p[i % size][(j - 1) % size].stan)
			liczbaZywychSasiadow++;
		if (p[i % size][(j + 1) % size].stan)
			liczbaZywychSasiadow++;
		if (p[(i + 1) % size][(j - 1) % size].stan)
			liczbaZywychSasiadow++;
		if (p[(i + 1) % size][j % size].stan)
			liczbaZywychSasiadow++;
		if (p[(i + 1) % size][(j + 1) % size].stan)
			liczbaZywychSasiadow++;

		return liczbaZywychSasiadow;
	}

	/**
	 * Zmienia stan komórki na przeciwny
	 * 
	 * @param i
	 *            współrzędna wierszowa komórki
	 * @param j
	 *            współrzędna kolumnowa komórki
	 */
	public void przelaczKomoroka(int i, int j) {
		plansza[i][j].stan = !plansza[i][j].stan;
	}

	/**
	 * Sprawdza stan komórki
	 * 
	 * @param i
	 *            współrzędna wierszowa komórki
	 * @param j
	 *            współrzędna kolumnowa komórki
	 * @return
	 */
	public boolean sprawdzKomorka(int i, int j) {
		return plansza[i][j].stan;
	}
	/**
	 * Funkcja losująca planszę
	 */
	void randomBoard() {
		Random rand = new Random();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				this.plansza[i][j].stan = rand.nextBoolean();
			}
	}
	/**
	 * Funkcja zerująca planszę
	 */
	void zeruj() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				this.plansza[i][j].stan = false;
			}
	}

}
