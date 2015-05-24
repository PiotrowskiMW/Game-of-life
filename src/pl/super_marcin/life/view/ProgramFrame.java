package pl.super_marcin.life.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.super_marcin.life.actionMessage.ActionMessageErase;
import pl.super_marcin.life.actionMessage.ActionMessageExit;
import pl.super_marcin.life.actionMessage.ActionMessageMousePress;
import pl.super_marcin.life.actionMessage.ActionMessageRandom;
import pl.super_marcin.life.actionMessage.ActionMessageStartSimulation;
import pl.super_marcin.life.actionMessage.ActionMessageStep;
import pl.super_marcin.life.actionMessage.ActionMessageStopSimulation;
import pl.super_marcin.life.model.Model;

/**
 * 
 * @author super_marcin Klasa rysująca okno programu
 */
public class ProgramFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6569750258452523289L;
	private final int boardSize;
	private final int cellSize;
	public ProgramFrame(int size, int sizeCell, final View widok, Model model) {
		super("Game of life by Marcin Włodzimierz Piotrowski!");
		this.cellSize = sizeCell;
		this.setLayout(new BorderLayout());
		DrawBoard left = new DrawBoard(size,cellSize, model);
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		final int boardWidth = size * cellSize+2;
		this.boardSize = size;
		JButton buttonStart = new JButton("Rozpocznij symulację");
		JButton buttonInfStart = new JButton("Rozpocznij nieskończoną symulację");
		JButton buttonStop = new JButton("Stop!");
		JButton buttonErase = new JButton("Zeruj planszę!");
		JButton buttonExit = new JButton("Zamknij!");
		JButton buttonStep = new JButton("Krok");
		JButton buttonRand = new JButton("Losuj planszę");
		JLabel stepsLabel = new JLabel("Wprowadź liczbę kroków do wykonania:");
		JLabel timeOutLabel = new JLabel("Wprowadź opóźnienie kroku [ms]:");
		final JTextField stepsField = new JTextField("10000");
		final JTextField timeOutField = new JTextField("50");
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int timeout=0;
				int iterations=0;
				try{
					timeout = Integer.parseInt(timeOutField.getText());
					iterations = Integer.parseInt(stepsField.getText());
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null, "Wprowadziłeś złe dane. Popraw!", "Błąd!", 1);
				}
				ActionMessageStartSimulation startMsg = new ActionMessageStartSimulation(
						timeout, iterations);
				widok.insertQueue(startMsg);

			}
		});
		buttonInfStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int timeout=0;
				int iterations=-1;
				try{
					timeout = Integer.parseInt(timeOutField.getText());
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null, "Wprowadziłeś złe dane. Popraw!", "Błąd!", 1);
					iterations = 0;
				}
				ActionMessageStartSimulation startMsg = new ActionMessageStartSimulation(
						timeout, iterations);
				widok.insertQueue(startMsg);

			}
		});
		buttonStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionMessageStopSimulation stopMsg = new ActionMessageStopSimulation();
				widok.insertQueue(stopMsg);

			}
		});
		buttonErase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionMessageErase eraseMsg = new ActionMessageErase();
				widok.insertQueue(eraseMsg);

			}
		});
		buttonStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionMessageStep stepMsg = new ActionMessageStep();
				widok.insertQueue(stepMsg);
			}
		});
		buttonRand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionMessageRandom randMsg = new ActionMessageRandom();
				widok.insertQueue(randMsg);
			}
		});
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionMessageExit randMsg = new ActionMessageExit();
				widok.insertQueue(randMsg);
			}
		});
		left.setLayout(null);
		right.setPreferredSize(new Dimension(300, 200));
		right.add(timeOutLabel);
		right.add(timeOutField);
		right.add(stepsLabel);
		right.add(stepsField);
		right.add(buttonStart);
		right.add(buttonInfStart);
		right.add(buttonStop);
		right.add(buttonErase);
		right.add(buttonStep);
		right.add(buttonRand);
		right.add(buttonExit);
		timeOutField.setMaximumSize(new Dimension(300,30));
		stepsField.setMaximumSize(new Dimension(300,30));
		left.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				int i;
				int j;
				i = arg0.getX() / cellSize;
				j = arg0.getY() / cellSize;
				if(i<boardSize && j<boardSize)
				{
					ActionMessageMousePress mouseMsg = new ActionMessageMousePress(i,j);
					widok.insertQueue(mouseMsg);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		left.setPreferredSize(new Dimension(boardWidth, boardWidth));
		getContentPane().add(left, BorderLayout.CENTER);
		getContentPane().add(right, BorderLayout.EAST);
		pack();
		setVisible(true);
	}
}
