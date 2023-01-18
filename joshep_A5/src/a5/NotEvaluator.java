package a5;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtomicExpressionFactory;

public class NotEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		if (sExpression instanceof NilAtom) {
			return NilAtomicExpressionFactory.newInstance();
		}
		if (sExpression.getHead().eval(environment) instanceof NilAtom) {
			return TAtomicExpressionFactory.newInstance();
		}
		return NilAtomicExpressionFactory.newInstance();
	}

}
