package a5;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;

public class EvalEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		if (sExpression instanceof NilAtom || sExpression.getHead() instanceof NilAtom) {
			return NilAtomicExpressionFactory.newInstance();
		}
		return sExpression.getHead().eval(environment).eval(environment);
	}
}
