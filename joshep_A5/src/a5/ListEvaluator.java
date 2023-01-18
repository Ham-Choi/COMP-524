package a5;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.ExpressionFactory;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;

public class ListEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		if (sExpression instanceof NilAtom) {
			return NilAtomicExpressionFactory.newInstance();
		}
		if (sExpression.getTail() instanceof NilAtom) {
			return sExpression.getHead().eval(environment);
		}
		SExpression expressionCopy = sExpression;
		int sExpressionCount = 1;
		while (!(expressionCopy.getTail() instanceof NilAtom)) {
			sExpressionCount++;
			expressionCopy = expressionCopy.getTail();
		}
		SExpression[] expressionFollow = new SExpression[sExpressionCount];
		int i = 0;
		while (!(sExpression.getTail() instanceof NilAtom)) {
			expressionFollow[i] = sExpression.getHead().eval(environment);
			i++;
			sExpression = sExpression.getTail();
		}
		expressionFollow[sExpressionCount-1] = sExpression.getHead().eval(environment);
		SExpression expressionList = sExpression.getTail();
		for (int j = sExpressionCount-1; j >= 0; j--) {
			expressionList = ExpressionFactory.newInstance(expressionFollow[j],expressionList);
		}
		return expressionList;
	}

}
