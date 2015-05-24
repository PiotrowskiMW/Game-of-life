package pl.super_marcin.life.actionMessage;

/**
 * Klasa przesyłająca informacje o wciśnięciu klawisza Rozpoczynającego symulację
 * @author super_marcin
 *
 */
public class ActionMessageStartSimulation extends ActionMessage{
	private final int timeout;
	private final int steps;
	public ActionMessageStartSimulation(int timeout, int steps)
	{
		this.timeout = timeout;
		this.steps = steps;
	}
	public int getTimeOut() {
		return timeout;
	}
	public int getSteps() {
		return steps;
	}

}
