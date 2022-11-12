package Java.Code.Command.Login;

import java.util.Timer;

public class autoSave {
	public static Timer onLoginSuccessful() {
		Timer timer = new Timer();
		int autosave_timegap = 1000 * 60;
		timer.schedule(new autoSaveTask(), 0, autosave_timegap);
		return timer;
	}

	public static void onLogOutSuccessful(Timer timer) {
		timer.cancel();
		timer = null;
	}
}