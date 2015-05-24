package pl.super_marcin.life.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.super_marcin.life.Logger;
import pl.super_marcin.life.actionMessage.ActionMessage;
import pl.super_marcin.life.actionMessage.ActionMessageErase;
import pl.super_marcin.life.actionMessage.ActionMessageExit;
import pl.super_marcin.life.actionMessage.ActionMessageMousePress;
import pl.super_marcin.life.actionMessage.ActionMessageRandom;
import pl.super_marcin.life.actionMessage.ActionMessageStartSimulation;
import pl.super_marcin.life.actionMessage.ActionMessageStep;
import pl.super_marcin.life.actionMessage.ActionMessageStopSimulation;
import pl.super_marcin.life.model.Model;
import pl.super_marcin.life.view.View;

/**
 * Klasa implementująca Kontroler
 * 
 * @author super_marcin
 *
 */
public class Controller {

	BlockingQueue<ActionMessage> messageQueue;
	Simulation simulationThread;
	private final Map<Class<? extends ActionMessage>, Strategy> strategyMap;
	Model modelLink;
	View viewLink;

	/**
	 * Konstruktor kontrolera
	 * @param kolejka odnośnik do kolejki zdarzeń do obsłóżenia
	 * @param model odnośnik do modelu
	 * @param view odnośnik do widoku
	 */
	public Controller(BlockingQueue<ActionMessage> kolejka, Model model,
			View view) {
		messageQueue = kolejka;
		modelLink = model;
		viewLink = view;
		this.strategyMap = new HashMap<Class<? extends ActionMessage>, Strategy>();
		addStategies();
		run();
	}

	/**
	 * Funkcja obsługująca pracę programu
	 */
	void run() {
		while (true) {
			try {

				ActionMessage msg = messageQueue.take();
				strategyMap.get(msg.getClass()).executeMessage(msg);
			} catch (InterruptedException e) {
				Logger.log("Błąd pobierania zadania!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Dodaje potrzebne srategie do mapy
	 */
	private void addStategies() {
		strategyMap.put(ActionMessageMousePress.class, new MouseStrategy());
		strategyMap.put(ActionMessageStep.class, new StepStrategy());
		strategyMap.put(ActionMessageStartSimulation.class,
				new StartSimulationStrategy());
		strategyMap.put(ActionMessageRandom.class, new RandStrategy());
		strategyMap.put(ActionMessageStopSimulation.class, new StopStrategy());
		strategyMap.put(ActionMessageExit.class, new ExitStrategy());
		strategyMap.put(ActionMessageErase.class, new EraseStrategy());
	}

	/**
	 * Funkcja zerująca planszę
	 * @param msg
	 */
	private void doErase(ActionMessageErase msg) {
		modelLink.erase();
		viewLink.repaintBoard();
	}
	
	private void doMouse(ActionMessageMousePress msg) {
		int i, j;
		i = msg.geti();
		j = msg.getj();
		modelLink.przelacz(i, j);
		viewLink.repaintBoard();
	}

	private void doStop(ActionMessageStopSimulation msg) {
		if (simulationThread != null) {
			simulationThread.changeJazda();
			simulationThread = null;
		}
	}

	private void doExit(ActionMessageExit msg) {
		System.exit(0);
	}

	private void doStep(ActionMessageStep msg) {
		modelLink.iteracja();
		viewLink.repaintBoard();
	}

	private void startSymulation(ActionMessageStartSimulation msg) {
		if (simulationThread == null) {
			simulationThread = new Simulation(msg.getTimeOut(), msg.getSteps());
			new Thread(simulationThread).start();
		}
	}

	/**
	 * Klasa implementująca nowy wontek do obsługi symulacji
	 * @author super_marcin
	 *
	 */
	private class Simulation implements Runnable {
		boolean jazda = true;
		int timeOut;
		int steps;

		Simulation(int timeOut, int steps) {
			this.timeOut = timeOut;
			this.steps = steps;
		}

		@Override
		public void run() {
			while (steps != 0 && jazda) {
				modelLink.iteracja();
				viewLink.repaintBoard();
				try {
					Thread.sleep(timeOut);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				steps--;
			}
			simulationThread =null;
		}

		void changeJazda() {
			jazda = false;
		}
	}

	private void doRand(ActionMessageRandom msg) {
		modelLink.getRandomBoard();
		viewLink.repaintBoard();
	}

	private abstract class Strategy {
		abstract void executeMessage(ActionMessage msg);
	}

	private class StartSimulationStrategy extends Strategy {
		@Override
		void executeMessage(final ActionMessage msg) {
			startSymulation((ActionMessageStartSimulation) msg);
		}
	}

	private class StepStrategy extends Strategy {
		@Override
		void executeMessage(final ActionMessage msg) {
			doStep((ActionMessageStep) msg);
		}
	}

	private class MouseStrategy extends Strategy {
		@Override
		void executeMessage(final ActionMessage msg) {
			doMouse((ActionMessageMousePress) msg);
		}
	}

	private class RandStrategy extends Strategy {
		@Override
		void executeMessage(final ActionMessage msg) {
			doRand((ActionMessageRandom) msg);
		}
	}

	private class StopStrategy extends Strategy {
		@Override
		void executeMessage(final ActionMessage msg) {
			doStop((ActionMessageStopSimulation) msg);
		}
	}

	private class ExitStrategy extends Strategy {
		@Override
		void executeMessage(final ActionMessage msg) {
			doExit((ActionMessageExit) msg);
		}
	}

	private class EraseStrategy extends Strategy {
		@Override
		void executeMessage(final ActionMessage msg) {
			doErase((ActionMessageErase) msg);
		}
	}
}
