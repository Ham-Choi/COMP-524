package a6;

import main.lisp.parser.terms.SExpression;
import main.lisp.parser.terms.AbstractSExpression;
import main.lisp.parser.terms.BasicExpression;

public class Functions extends BasicExpression{

	public Functions(SExpression head, SExpression tail) {
		super(head, tail);
		// TODO Auto-generated constructor stub
	}
	//recursive
	@Override
	public boolean isList() {
		// TODO Auto-generated method stub
		if (this.getTail().isNIL()) {
			return true;
		} else {
			return this.getTail().isList();
		}
	}

	@Override
	public String toStringAsList() {
		// TODO Auto-generated method stub
		return "(" + ((AbstractSExpression) this).toStringAsListHelperPublic() + ")";
	}
	
	@Override
	public String toStringAsSExpression() {
		// TODO Auto-generated method stub
		return "(" + this.getHead().toString() + " " + "." + " " + this.getTail().toString() + ")";
	}

	@Override
	public String toStringAsSExpressionDeep() {
		// TODO Auto-generated method stub
		return "(" + this.getHead().toStringAsSExpressionDeep() + " " + "." + " " + this.getTail().toStringAsSExpressionDeep() + ")";
	}
	
	//helper
	@Override
	public String toString() {
		if (isList()) {
			return toStringAsList();
		} else {
			return toStringAsSExpression();
		}
	}
	
	//helper
	@Override
	public String toStringAsListHelperPublic() {
		// TODO Auto-generated method stub
		if (getTail().isNIL()) {return (getHead().toString());}
		return getHead().toString() +" "+ ((AbstractSExpression) getTail()).toStringAsListHelperPublic();
	}

}
