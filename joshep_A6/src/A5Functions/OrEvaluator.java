package A5Functions;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;

public class OrEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		while (!(sExpression.getTail() instanceof NilAtom)) {
			if (!(sExpression.getHead().eval(environment) instanceof NilAtom)) {
				return sExpression.getHead().eval(environment);
			}
			sExpression = sExpression.getTail();
		}
		if (!(sExpression.getHead().eval(environment) instanceof NilAtom)) {
			return sExpression.getHead().eval(environment);
		}
		return NilAtomicExpressionFactory.newInstance();
	}
}