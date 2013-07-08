
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
		if (more())
			return input.charAt(0);
		else {
			return '\0';
		}
	}
	// Advance to the next character
	private char next() throws Exception {
		char c = peek() ;
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
	//	  E     --> T Etail
	public float E() throws Exception {
		System.out.println();
		System.out.print("E: [");
		float term = T();
		System.out.print(", ");
		float result = Etail(term);
		System.out.print("]");
		return result;
	}
	//	  Etail --> + T Etail | - T Etail | epsilon
	private float Etail(float term) throws Exception {
		System.out.println();
		System.out.print("Etail: [");
		float result = term;
		if (peek() == '+'|| peek() == '-') {
			System.out.print(peek());
			char op = next();
			System.out.print(", ");
			float term2 = T();
			System.out.print(", ");
			result = Etail((op == '+') ? term + term2 : term - term2);
		}
		System.out.print("]");
		return result;
	}
	//	  T     --> F Ttail
	private float T() throws Exception {
		System.out.println();
		System.out.print("T: [");
		float factor = F();
		System.out.print(", ");
		float result = Ttail(factor);
		System.out.print("]");
		return result;
	}
	//	  Ttail --> * F Ttail | / F Ttail | epsilon
	private float Ttail(float term) throws Exception {
		System.out.println();
		System.out.print("Ttail: [");
		float result = 0;
		if (peek() == '*'|| peek() == '/') {
			System.out.print(peek());
			char op = next();
			System.out.print(", ");
			float term2 = F();
			System.out.print(", ");
			result = Ttail((op == '*') ? term * term2 : term / term2);
		}
		System.out.print("]");
		return result;
	}
	//	  F     --> ( E ) | num
	private float F() throws Exception {
		System.out.println();
		System.out.print("F: [");
		float result = 0;
		if (peek() == '(') {
			System.out.print(peek());
			next();
			System.out.print(", ");
			result = E();
			System.out.print(", ");
			eat(')');
			System.out.print(")");
		} else if (Character.isDigit(peek())) {
			System.out.print(peek());
			result = Float.parseFloat(""+next());
		}
		System.out.print("]");
		return result;
	}
	public static void main(String[] args) throws Exception {
		ExpressionParser parser = new ExpressionParser("(2+2+7)");
		System.out.println(parser.E());
	}
}
