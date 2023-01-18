package A5Functions;

import main.lisp.evaluator.Environment;
import main.lisp.evaluator.Evaluator;
import main.lisp.parser.terms.DecimalAtom;
import main.lisp.parser.terms.IntegerAtom;
import main.lisp.parser.terms.NilAtom;
import main.lisp.parser.terms.NilAtomicExpressionFactory;
import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.TAtom;

public class LTEEvaluator implements Evaluator{

	@Override
	public SExpression eval(SExpression sExpression, Environment environment) {
		// TODO Auto-generated method stub
		sExpression = sExpression.getTail();
		if (sExpression instanceof NilAtom || sExpression.getHead() instanceof NilAtom || sExpression.getTail() instanceof NilAtom) {
			return NilAtomicExpressionFactory.newInstance();
		}
		if (!(sExpression.getTail().getTail() instanceof NilAtom)) {
			return NilAtomicExpressionFactory.newInstance();
		}
		SExpression firstEvaluation = sExpression.getHead().eval(environment);
		SExpression secondEvaluation = sExpression.getTail().getHead().eval(environment);
		IntegerAtom aInteger = null;
		IntegerAtom bInteger = null;
		DecimalAtom aDecimal = null;
		DecimalAtom bDecimal = null;
		int correctForm = 0;
	
		if (firstEvaluation instanceof IntegerAtom) {
			aInteger = (IntegerAtom)firstEvaluation;
			correctForm++;
		}
		if (firstEvaluation instanceof DecimalAtom) {
			aDecimal = (DecimalAtom)firstEvaluation;
			correctForm++;
		}
		if (secondEvaluation instanceof IntegerAtom) {
			bInteger = (IntegerAtom)secondEvaluation;
			correctForm++;
		}
		if (secondEvaluation instanceof DecimalAtom) {
			bDecimal = (DecimalAtom)secondEvaluation;
			correctForm++;
		}
		if (correctForm != 2) {
			return NilAtomicExpressionFactory.newInstance();
		}
		if (aInteger != null && bInteger != null) {
			if (aInteger.getValue() <= bInteger.getValue()) {
				return new TAtom();
			} else {
				return new NilAtom();
			}
		} else {
			double initialValue = 0;
			if (aInteger != null) {
				initialValue = aInteger.getValue();
			} else if (aDecimal != null) {
				initialValue = aDecimal.getValue();
			}
			if (bInteger != null) {
				if (initialValue <= bInteger.getValue()) {
					return new TAtom();
				} 
			} else if (bDecimal != null) {
				if (initialValue <= bDecimal.getValue()) {
					return new TAtom();
				}
			}
			return new NilAtom();
		}
	}

}
