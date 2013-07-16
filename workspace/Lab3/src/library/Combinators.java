package library;

import java.util.HashSet;
import java.util.Set;

public class Combinators {
	String input;
	int i = 0;
	/*
	 * Primitive parser operations:
	 * next, eat, more, and peek
	 */
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
		return c;
	}
	// Parser combinators
	public static interface Visitor<T> {
		public T visit(Grammar g);
		public T visit(Nonterminal n);
		public T visit(Terminal t);
		public T visit(Sequence s);
		public T visit(Or o);
		public T visit(Star s);
	}
	// This is like the Node class in the other labs
	public static interface Parser {
		public <T> T accept(Visitor<T> visitor);
	}
	/*
	 * Grammar construction classes
	 */
	// Nonterminal represents the productions of a nonterminal.
	public static class Nonterminal implements Parser {
		String name;
		Parser rhs;
		public Nonterminal() {
			
		}
		public Nonterminal(String name) {
			this.name = name;
		}
		public void derives(Parser...parsers) {
			rhs = new Sequence(parsers);
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}

	}
	// Represe
	public static class Terminal implements Parser {

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	private static class Symbol extends Terminal {
		char c;
		public Symbol (char c) {
			this.c = c;
		}
	}
	private static class Sequence implements Parser {
		Parser[] parsers;
		public Sequence(Parser...parsers) {
			this.parsers = parsers;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	private static class Or implements Parser {
		Parser[] parsers;
		public Or(Parser...parsers) {
			this.parsers = parsers;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	private static class Star implements Parser {
		Parser child;
		public Star(Parser child) {
			this.child = child;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	/*
	 * Grammar construction convenience methods
	 */
	public static Parser symbol(char c) {
		return new Symbol(c);
	}
	public static Parser star(Parser t) {
		return new Star(t);
	}
	public static Parser seq(Parser...parsers) {
		return new Sequence(parsers);
	}
	public static Parser or(Parser...parsers) {
		return new Or(parsers);
		
	}
	public static Parser integer() {
		return new Terminal();
	}
	public static class Grammar implements Parser {
		Set<Nonterminal> nonterminals;
		public Grammar(Nonterminal start) {
			Builder builder = new Builder();
			start.accept(builder);
			nonterminals = builder.set;
		}
		// This builder exists just to create the set of nonterminals
		// without asking the user of the class to supply one.
		private static class Builder implements Visitor<Void> {
			public Set<Nonterminal> set;
			public Builder() {
				set = new HashSet<Nonterminal>();
			}
			@Override
			public Void visit(Nonterminal n) {
				if (!set.contains(n)) {
					set.add(n);
					n.rhs.accept(this);
				}
				return null;
			}
			@Override
			public Void visit(Terminal t) {
				return null;
			}
			@Override
			public Void visit(Sequence s) {
				for (Parser p : s.parsers) {
					p.accept(this);
				}
				return null;
			}
			@Override
			public Void visit(Or o) {
				for (Parser p : o.parsers) {
					p.accept(this);
				}
				return null;
			}
			@Override
			public Void visit(Star s) {
				// TODO Auto-generated method stub
				s.child.accept(this);
				return null;
			}
			@Override
			public Void visit(Grammar g) {
				return null;
			}
		}

		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Printer implements Visitor<String> {

		@Override
		public String visit(Nonterminal n) {
			return n.name;
		}

		@Override
		public String visit(Terminal t) {
			if (t instanceof Symbol) {
				Symbol s = (Symbol) t;
				return "'" + s.c + "'";
			}
			return "";
		}

		@Override
		public String visit(Sequence s) {
			StringBuilder builder = new StringBuilder();
			for (Parser p : s.parsers) {
				builder.append(" ");
				builder.append(p.accept(this));
			}
			return builder.toString();
		}

		@Override
		public String visit(Or o) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < o.parsers.length; i++) {
				builder.append("(");
				builder.append(o.parsers[i].accept(this));
				builder.append(")");
				if (i + 1 != o.parsers.length)
					builder.append(" | ");
			}
			return builder.toString();
		}

		@Override
		public String visit(Star s) {
			return "(" + s.child.accept(this) + ")*";
		}

		@Override
		public String visit(Grammar g) {
			StringBuilder builder = new StringBuilder();
			for (Nonterminal n : g.nonterminals) {
				builder.append(n.name);
				builder.append(" ::= ");
				builder.append(n.rhs.accept(this));
				builder.append("\n");
			}
			return builder.toString();
		}
		
	}
}
