package library;

public class Combinators {
	String input;
	int i = 0;
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
		if (!more()) return '\0';
		return input.charAt(0);
	}
	// Advance to the next character
	private char next() throws Exception {
		char c = peek() ;
		eat(c) ;
		return c ;
	}
	public static interface Visitor<T> {
		public T visit(Nonterminal p);
		public T visit(Terminal p);
	}
	public static interface Parser {
	}
	public static class Nonterminal implements Parser {

		public void derives(Parser...parsers) {
			
		}

	}
	public static class Terminal implements Parser {
	}
	private static class Symbol extends Terminal {
		char c;
		public Symbol (char c) {
			this.c = c;
		}
	}
	public static Parser symbol(char c) {
		return new Symbol(c);
	}
	public static Parser star(Parser p) {
		return p;
	}
	public static Parser seq(Parser...parsers) {
		return null;
	}
	public static Parser or(Parser a, Parser b) {
		return b;
		
	}
	public static Parser integer() {
		return null;
	}
}
