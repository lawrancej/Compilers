
import static library.Combinators.*;

public class Expression {
	public static Parser grammar() {
		Nonterminal group = new Nonterminal();
		Nonterminal factor = new Nonterminal();
		Nonterminal term = new Nonterminal();
		Nonterminal expression = new Nonterminal();
		
		// group       ::= '(' expression ')'
		group.derives(symbol('('), expression, symbol(')'));
		// factor      ::= integer | group
		factor.derives(or (integer(), group));
		// term        ::= factor (('*' factor) | ('/' factor))*
		term.derives(factor, star(or(seq(symbol('*'), factor), seq(symbol('/'), factor))));
		// expression  ::= term (('+' term) | ('-' term))*
		expression.derives(term, star(or(seq(symbol('+'), term), seq(symbol('-'), term))));
		return expression;
	}
}
