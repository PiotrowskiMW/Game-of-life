
package pl.super_marcin.life;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

import javax.swing.JOptionPane;

import pl.super_marcin.life.actionMessage.ActionMessage;
import pl.super_marcin.life.controller.Controller;
import pl.super_marcin.life.model.Model;
import pl.super_marcin.life.view.View;


/**
 * Klasa Uruchamiejąca program
 * @author super_marcin
 *
 */
public class Uruchom {

	/**
	 * Funkcja main programu
	 * @param args
	 */
	public static void main(String[] args)
	{
		BlockingQueue<ActionMessage> kolejka = new LinkedTransferQueue<ActionMessage>();
		/**
		 * Pobieranie wielkości planszy od Urzytkownika
		 */
		String strsize = (String) JOptionPane.showInputDialog(null,"Podaj wielkość planszy","Zacznij nową grę!",JOptionPane.PLAIN_MESSAGE);
		String strCellSize = (String) JOptionPane.showInputDialog(null,"Podaj wielkość komórki w pikselach","Zacznij nową grę!",JOptionPane.PLAIN_MESSAGE);
		int size;
		int cellSize;
		try{
			size = Integer.parseInt(strsize);
			cellSize = Integer.parseInt(strCellSize);
		}catch(Exception e)
		{
			size = 100;
			cellSize = 5;
			JOptionPane.showMessageDialog(null, "Wprowadziłeś złe dane, dostaniesz domyślną planszę!", "Błąd", 1);
		}
		Model model = new Model(size);
		View widok = new View(size,cellSize,kolejka,model);
		Controller ctrl = new Controller(kolejka,model,widok);
	}
}
