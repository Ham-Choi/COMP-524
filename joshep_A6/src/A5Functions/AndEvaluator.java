package A5Functions;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;

public class AndEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		while (!(sExpression.getTail() instanceof NilAtom)) {
			if (sExpression.getHead().eval(environment) instanceof NilAtom) {
				return NilAtomicExpressionFactory.newInstance();
			}
			sExpression = sExpression.getTail();
		}
		return sExpression.getHead().eval(environment);
	}
}
