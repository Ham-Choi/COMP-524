package a5;
import main.Main;
import main.lisp.parser.terms.ExpressionFactory;

public class A5Main{
	public static void main(String[] args) {
		ExpressionFactory.setClass(Functions.class);
		CustomOperationRegisterer.registerAll();;
		Main.main(args);
	}
}
