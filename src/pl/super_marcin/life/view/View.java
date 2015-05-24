package pl.super_marcin.life.view;

import java.util.concurrent.BlockingQueue;





import pl.super_marcin.life.Logger;
import pl.super_marcin.life.actionMessage.ActionMessage;
import pl.super_marcin.life.model.Model;

/**
 * Klasa implementująca Widok
 * @author super_marcin
 *
 */
public class View {

	Model modelLink;
	BlockingQueue<ActionMessage> messageQueue;
	ProgramFrame progFrame;
	public View(int sizeC,int cellSize, BlockingQueue<ActionMessage> kolejka,Model model)
	{
		modelLink = model;
		messageQueue = kolejka;
		progFrame = new ProgramFrame(sizeC,cellSize,this,modelLink);

	}
	/**
	 * Funkcja wstawiająca wiadomość o zdarzeniu na planszy do kolejki
	 */
	void insertQueue(ActionMessage message)
	{
		try {
			messageQueue.put(message);
		} catch (InterruptedException e) {
			Logger.log("Błąd Wstawiania elementu do kolejki!");
			e.printStackTrace();
		}
	}
	/**
	 * Funkcja odświerzająca planszę
	 */
	public void repaintBoard()
	{
		progFrame.repaint();
	}
}
