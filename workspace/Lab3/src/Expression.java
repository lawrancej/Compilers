
import static library.Combinators.*;

public class Expression {
	public static Parser grammar() {
		Nonterminal group = new Nonterminal("group");
		Nonterminal factor = new Nonterminal("factor");
		Nonterminal term = new Nonterminal("term");
		Nonterminal expression = new Nonterminal("expression");
		
		// group       ::= '(' expression ')'
		group.derives(symbol('('), expression, symbol(')'));
		// factor      ::= integer | group
		factor.derives(or (integer(), group));
		// term        ::= factor (('*' factor) | ('/' factor))*
		term.derives(factor, star(or(seq(symbol('*'), factor), seq(symbol('/'), factor))));
		// expression  ::= term (('+' term) | ('-' term))*
		expression.derives(term, star(or(seq(symbol('+'), term), seq(symbol('-'), term))));
		return new Grammar(expression);
	}
	public static void main(String[] args) {
		Printer printer = new Printer();
		System.out.println(grammar().accept(printer));
	}
}
