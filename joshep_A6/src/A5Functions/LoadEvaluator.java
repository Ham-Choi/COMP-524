package A5Functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.interpreter.InterpreterModelSingleton;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.StringAtom;
import main.lisp.parser.terms.TAtomicExpressionFactory;

public class LoadEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		if (sExpression instanceof NilAtom || sExpression.getHead() instanceof NilAtom) {
			return NilAtomicExpressionFactory.newInstance();
		}
		String atomString = ((StringAtom) sExpression.getHead().eval(environment)).getValue();
		Path path = Paths.get("", atomString);
		SExpression loadResult = TAtomicExpressionFactory.newInstance();
		try {
			List<String> argfile = Files.readAllLines(path);
			for (String lines : argfile) {
				InterpreterModelSingleton.get().newInput(lines);
			}
		} catch(IOException e) {
			loadResult = NilAtomicExpressionFactory.newInstance();
		} catch(IllegalStateException e) {
			loadResult = NilAtomicExpressionFactory.newInstance();
		} catch(Exception e) {
			loadResult = NilAtomicExpressionFactory.newInstance();
		}
		return loadResult;
	}

}
