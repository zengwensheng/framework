package com.zws.interpreter.cls;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 逆波兰算法
 */
public class RPN {

	private ArrayList<String> expression = new ArrayList<String>();// 存储中序表达式

	private ArrayList<String> right = new ArrayList<String>();// 存储右序表达式

	private AbstractExpresstion result;// 结果

	// 依据输入信息创建对象，将数值与操作符放入ArrayList中
	public RPN(String input) {
		StringTokenizer st = new StringTokenizer(input, "+-*/()", true);
		while (st.hasMoreElements()) {
			expression.add(st.nextToken());
		}
	}



	/**
	 * 将中序表达式转换为右序表达式
	 * 例子
	 *      中序表达式          右序表达式（后序表达式）       步骤
	 * 1，    a+b                ab+                    ⑧  ①②  ⑧
	 * 2，   (a+b)*c            ab+c*                 ①②  ⑧  ①⑨⑤⑥ ⑧ ①⑨③④ ①② ⑧
	 * 3， (a+b)*c-(a+b)/e     ab+c*ab+e/-
	 * @Todo   (a+b*c)*d 这个公式不能运算 有待改进 现在只支持+-* / （） 的运算 后续在改
	 */
	private void toRight() {
		Stacks aStack = new Stacks();  //存储的是运算符
		String operator;
		int position = 0;
		while (true) {
			/**
			 * expression.get(position) 为运算符是进入
			 * 否则 将expression.get(position) 添加到right中
			 */
			if (Calculate.isOperator((String) expression.get(position))) { //步骤①
				/**
				 * aStack的元素数量等于0 或者 expression.get(position) 等于（  进入
				 */
				if (aStack.top == -1
						|| ((String) expression.get(position)).equals("(")) {//步骤②
					aStack.push(expression.get(position));
				} else {//步骤⑨
					/**
					 * expression.get(position) 为 ） 进入
					 */
					if (((String) expression.get(position)).equals(")")) {//步骤③
						/**
						 *  aStack 中第一个值不等于（ 进入
						 */
						if (!((String) aStack.top()).equals("(")) {//步骤④
							operator = (String) aStack.pop();
							right.add(operator);
						}
					} else {//步骤⑤
						/**
						 * expression.get(position) 的优先级小于等于aStack的第一个元素的优先级 并且aStack的元素数量大于0 进入
						 */
						if (Calculate.priority((String) expression
								.get(position)) <= Calculate
								.priority((String) aStack.top())
								&& aStack.top != -1) {//步骤⑥
							operator = (String) aStack.pop();
							if (!operator.equals("("))//步骤⑦
								right.add(operator);
						}
						aStack.push(expression.get(position));
					}
				}
			} else//步骤⑧
				right.add(expression.get(position));
			position++;
			if (position >= expression.size())
				break;
		}
		while (aStack.top != -1) {
			operator = (String) aStack.pop();
			right.add(operator);
		}
	}

	// 对右序表达式进行求值
	public void getResult(HashMap<String, Float> var) {
		this.toRight();
		Stack<AbstractExpresstion> stack = new Stack<AbstractExpresstion>();
		AbstractExpresstion op1, op2;
		String is = null;
		Iterator it = right.iterator();

		while (it.hasNext()) {
			is = (String) it.next();
			if (Calculate.isOperator(is)) {
				op2 = stack.pop();
				op1 = stack.pop();
				stack.push(Calculate.twoResult(is, op1, op2));
			} else
				stack.push(new VarExpresstion(is));
		}
		result = stack.pop();
		it = expression.iterator();
		while (it.hasNext()) {
			System.out.print((String) it.next());
		}
		System.out.println("=" + result.interpreter(var));
	}

	public static class Calculate {
		// 判断是否为操作符号
		public static boolean isOperator(String operator) {
			if (operator.equals("+") || operator.equals("-")
					|| operator.equals("*") || operator.equals("/")
					|| operator.equals("(") || operator.equals(")"))
				return true;
			else
				return false;
		}

		// 设置操作符号的优先级别
		public static int priority(String operator) {
			if (operator.equals("+") || operator.equals("-")
					|| operator.equals("("))
				return 1;
			else if (operator.equals("*") || operator.equals("/"))
				return 2;
			else
				return 0;
		}

		// 做2值之间的计算
		public static AbstractExpresstion twoResult(String op,
				AbstractExpresstion a, AbstractExpresstion b) {
			try {

				AbstractExpresstion result = null;
				if (op.equals("+"))
					result = new AddExpresstion(a, b);
				else if (op.equals("-"))
					result = new SubExpresstion(a, b);
				else if (op.equals("*"))
					result = new MultiExpresstion(a, b);
				else if (op.equals("/"))
					result = new DivExpresstion(a, b);
				else
					;
				return result;
			} catch (NumberFormatException e) {
				System.out.println("input has something wrong!");
				return null;
			}
		}
	}

	// 栈类  FIFO 数据结构
	public class Stacks {
		private LinkedList list = new LinkedList();
		int top = -1;

		public void push(Object value) {
			top++;
			list.addFirst(value);
		}

		public Object pop() {
			Object temp = list.getFirst();
			top--;
			list.removeFirst();
			return temp;

		}

		public Object top() {
			return list.getFirst();
		}
	}
}
