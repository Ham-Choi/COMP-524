package a5;

import main.lisp.evaluator.OperationRegisterer;
import main.lisp.evaluator.BuiltinOperationManagerSingleton;

public class CustomOperationRegisterer implements OperationRegisterer{

	@Override
	public void registerOperations() {
		// TODO Auto-generated method stub
		registerAll();
	}
	public static void registerAll() {
		BuiltinOperationManagerSingleton.get().registerEvaluator("quote", new QuoteEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("list", new ListEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("eval", new EvalEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("load", new LoadEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("cond", new CondEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("<", new LTEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("<=", new LTEEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator(">", new GTEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator(">=", new GTEEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("and", new AndEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("or", new OrEvaluator());
		BuiltinOperationManagerSingleton.get().registerEvaluator("not", new NotEvaluator());
	}
}