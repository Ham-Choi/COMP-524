package a3;
import gradingTools.comp524f21.assignment3.F21Assignment3Suite;
//import trace.grader.basics.GraderBasicsTraceUtility;
//import util.trace.Tracer;

public class RunFA21A3Tests {
	static final String PROJECT_LOCATION =
			"C:\\Users\\User\\eclipse-workspace\\Prolog\\src";
	public static void main (String[] args) {
		// Tracer.showInfo(true);
		// GraderBasicsTraceUtility.setBufferTracedMessages(false);
		F21Assignment3Suite.setProjectLocation(PROJECT_LOCATION);
		F21Assignment3Suite.main(args);
	}
}
