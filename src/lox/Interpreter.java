package lox;

class Interpreter implements Expr.Visitor<Object> {
	private Object evalutate(Expr expr) {
		return expr.accept(this);
	}

	@Override
	public object visitBinaryExpr(Expr.Binary expr) {
		Object left = evalutate(expr.left);
		Object right = evalutate(expr.right);

		switch (expr.operator.type) {
			case BANG_EQUAL: return !isEqual(left, right);
			case EQUAL_EQUAL: return isEqual(left, right);
			case GREATER:
				return (double)left > (double)right;
			case GREATER_EQUAL:
				return (double)left >= (double)right;
			case LESS:
				return (double)left < (double)right;
			case LESS_EQUAL:
				return (double)left <= (double)right;
			case MINUS:
				return (double)left - (double)right;
			case PLUS: // Overload operator for strings and numbers
				if (left instanceof Double && right instanceof Double) {
					return (double)left + (double)right;
				}

				if (left instanceof String && right instanceof String) {
					return (String)left + (String)right;
				}
			case SLASH:
				return (double)left / (double)right;
			case STAR:
				return (double)left * (double)right;
		}
	}

	@Override
	public Object visitGroupingExpr(Expr.Grouping expr) {
		return evalutate(expr.expression);
	}

	@Override
	public Object visitLiteralExpr(Expr.Literal expr) {
		return expr.value;
	}

	@Override
	public Object visitUnaryExpr(Expr.Unary expr) {
		Object right = evalutate(expr.right);

		switch (expr.operator.type) {
			case BANG:
				return !isTruthy(right);
			case MINUS:
				return -(double)right;
		}

		return null;
	}

	private boolean isTruthy(Object object) {
		if (object == null) return false;
		if (object instanceof Boolean) return (boolean)object;
		return true;
	}

	private boolean isEqual(Object a, Object b) {
		if (a == null && b == null) return true;
		if (a == null) return false;

		return a.equals(b);
	}
}