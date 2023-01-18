package A5Functions;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;

public class QuoteEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		if (sExpression.isNIL() || !(sExpression.getTail().isNIL())) {
			return NilAtomicExpressionFactory.newInstance();
		}
		return sExpression.getHead();
	}
}
