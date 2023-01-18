package a4;
import gradingTools.comp524f21.assignment4.F21Assignment4Suite;

public class RunFA21A4Tests {
	static final String PROJECT_LOCATION =
			"C:\\Users\\User\\eclipse-workspace\\SML\\src";
	public static void main (String[] args) {
		// Tracer.showInfo(true);
		// GraderBasicsTraceUtility.setBufferTracedMessages(false);
		F21Assignment4Suite.setProjectLocation(PROJECT_LOCATION);
		F21Assignment4Suite.main(args);
	}
}
