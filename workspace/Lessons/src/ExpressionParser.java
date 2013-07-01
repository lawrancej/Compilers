
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
		char c = peek() ;
		eat(c) ;
		return c ;
	}
}
