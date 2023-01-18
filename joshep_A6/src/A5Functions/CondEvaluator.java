package A5Functions;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;

public class CondEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		if (sExpression instanceof NilAtom || sExpression.getHead() instanceof NilAtom) {
			return NilAtomicExpressionFactory.newInstance();
		}
		while ((sExpression.getHead().getHead().eval(environment) instanceof NilAtom) && !(sExpression.getTail() instanceof NilAtom)) {
			sExpression = sExpression.getTail();
		}
		if (!(sExpression.getHead().getHead().eval(environment) instanceof NilAtom)) {
			return sExpression.getHead().getTail().getHead().eval(environment);
		}
		return NilAtomicExpressionFactory.newInstance();
	}

}
