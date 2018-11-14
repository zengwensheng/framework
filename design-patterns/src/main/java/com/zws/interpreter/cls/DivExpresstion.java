package com.zws.interpreter.cls;

import java.util.HashMap;

public class DivExpresstion extends SymbolExpresstion {

	public DivExpresstion(AbstractExpresstion _left, AbstractExpresstion _right) {
		super(_left, _right);
	}

	@Override
	public Float interpreter(HashMap<String, Float> var) {
		return super.left.interpreter(var) / super.right.interpreter(var);
	}

}
