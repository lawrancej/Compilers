
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
		return '\0';
	}
	// Advance to the next character
	private char next() throws Exception {
		char c = peek();
		eat(c);
		return c;
	}
	/* LL(1) grammar for expressions
  E     --> T Etail
  Etail --> + T Etail | - T Etail | epsilon
  T     --> F Ttail
  Ttail --> * F Ttail | / F Ttail | epsilon
  F     --> ( E ) | num
	 */
//	  E     --> T Etail
	public void E() throws Exception {
		System.out.print("E: [");
		T();
		System.out.print(", ");
		Etail();
		System.out.println("]");
	}
	//	  Etail --> + T Etail | - T Etail | epsilon
	private void Etail() throws Exception {
		System.out.print("Etail: [");
		switch(peek()) {
		case '+':
		case '-':
			System.out.print(next());
			System.out.print(", ");
			T();
			System.out.print(", ");
			Etail();
		}
		System.out.println("]");
	}
//	  T     --> F Ttail
	private void T() throws Exception {
		System.out.print("T: [");
		F();
		System.out.print(", ");
		Ttail();
		System.out.println("]");
	}
	//	  Ttail --> * F Ttail | / F Ttail | epsilon
	private void Ttail() throws Exception {
		System.out.print("Ttail: [");
		switch(peek()) {
		case '*':
		case '/':
			System.out.print(next()); // Consume the symbol
			System.out.print(", ");
			F();
			System.out.print(", ");
			Ttail();
		}
		System.out.println("]");
	}
//	  F     --> ( E ) | num
	private void F() throws Exception {
		System.out.print("F: [");
		if (peek() == '(') {
			System.out.print(next());
			System.out.print(", ");
			E();
			System.out.print(", ");
			eat(')');
			System.out.print(")");
		} else if (Character.isDigit(peek())) {
			System.out.print(next());
		} else {
			throw new Exception (String.format("Expected ( or number, but got: %c at position %d", peek(), i));
		}
		System.out.print("]");
	}
	public static void main(String[] args) throws Exception 
	{
		ExpressionParser parser = new ExpressionParser("(2+2+2)*9");
		parser.E();
	}
}
