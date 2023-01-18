package a6;

import grader.basics.execution.BasicProjectExecution;
import gradingTools.comp524f21.assignment6.F21Assignment6Suite;
import trace.grader.basics.GraderBasicsTraceUtility;
//import util.trace.Tracer;

public class A6Tests {
	static int printedTrace = 600;
	static int maxTrace = 2000;
	static int processTimeOut= 5;
	public static void main(String[] args) {
		// if you set this to false, grader steps will not be traced
		GraderBasicsTraceUtility.setTracerShowInfo(true);
		// if you set this to false, all grader steps will be traced,
		// not just the ones that failed
		GraderBasicsTraceUtility.setBufferTracedMessages(true);
		// Change this number if a test trace gets longer than 600 and is clipped
		GraderBasicsTraceUtility.setMaxPrintedTraces(printedTrace);
		// Change this number if all traces together are longer than 2000
		GraderBasicsTraceUtility.setMaxTraces(maxTrace);
		// Change this number if your process times out prematurely
		BasicProjectExecution.setProcessTimeOut(processTimeOut);
		// You need to always call such a method
		F21Assignment6Suite.main(args);
	}
}