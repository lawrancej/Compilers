import java.util.ArrayList;
import java.util.List;


// http://matt.might.net/articles/parsing-regex-with-recursive-descent/
// http://matt.might.net/articles/grammars-bnf-ebnf/
public class ExpressionParser {
	String input;
	int i = 0;
	public ExpressionParser(String input) {
		this.input = input;
	}
	// Consume a character (this assumes we don't have a scanner/lexer)
	private void eat(char c) throws Exception 
	{
		// If the characters match, advance
		if (input.charAt(0) == c) {
			input = input.substring(1);
			i++;
		}
		// If not, then we just throw up
		else {
			throw new Exception (String.format("Expected %c but got %c at position %d", c, input.charAt(0), i));
		}
	}
	// Is there anything left?
	private boolean more () {
		return input.length() > 0;
	}
	// Look ahead
	private char peek() {
		return input.charAt(0);
	}
	// Advance to the next character
	private char next() throws Exception {
		char c = peek();
		eat(c) ;
		return c ;
	}
	/* LL(1) grammar for expressions
  E     --> T Etail
  Etail --> + T Etail | - T Etail | epsilon
  T     --> F Ttail
  Ttail --> * F Ttail | / F Ttail | epsilon
  F     --> ( E ) | num
	 */
	public static class Node {
		public List<Node> children = new ArrayList<Node>();
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			for (Node child : children) {
				sb.append(child.toString());
				sb.append(", ");
			}
			sb.append(']');
			return sb.toString();
		}
	}
	public static class CharacterNode extends Node {
		public char c;
		public String toString() {
			return ""+c;
		}
	}
	// E     --> T Etail
	public Node E() throws Exception {
		Node tree = new Node();
		tree.children.add(T());
		tree.children.add(Etail());
		return tree;
	}
	// Etail --> + T Etail | - T Etail | epsilon
	private Node Etail() throws Exception {
		Node tree = new Node();
		switch (peek()) {
		case '+':
		case '-':
			CharacterNode c = new CharacterNode();
			c.c = peek();
			tree.children.add(c);
			next();
			tree.children.add(T());
			tree.children.add(Etail());
			break;
		default:
			break;
		}
		return tree;
	}
	// T     --> F Ttail
	private Node T() throws Exception {
		Node tree = new Node();
		tree.children.add(F());
		tree.children.add(Ttail());
		return tree;
	}
	// Ttail --> * F Ttail | / F Ttail | epsilon
	private Node Ttail() throws Exception {
		Node tree = new Node();
		switch (peek()) {
		case '*':
		case '/':
			CharacterNode c = new CharacterNode();
			c.c = peek();
			tree.children.add(c);
			next();
			tree.children.add(F());
			tree.children.add(Ttail());
			break;
		default:
			break;
		}
		return tree;
	}
	// F     --> ( E ) | num
	private Node F() throws Exception {
		Node tree = new Node();
		CharacterNode c = new CharacterNode();
		if (peek() == '(') {
			c.c = next();
			tree.children.add(c);
			tree.children.add(E());
//			eat(')');
//			CharacterNode end = new CharacterNode();
//			end.c = ')';
//			tree.children.add(end);
		} else if (Character.isDigit(peek())) {
			c.c = next();
			tree.children.add(c);
		}
		return tree;
	}
	public static void main(String[] args) {
		ExpressionParser parser = new ExpressionParser("(5+2)");
		try {
			Node result = parser.E();
			System.out.println(result);
		} catch (Exception e) {
			System.err.println("Something's broken");
			e.printStackTrace();
		}
	}
}
