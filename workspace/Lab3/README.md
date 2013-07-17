# Lab 3/Final project

Do one of the following by yourself or with a parter or two:

1. Implement the phases of a compiler for a language of your choosing or invention.

	* Scanner (lexer/tokenizer). Use Flex, JFlex, or scanners included with the parser tools listed next.
	* Parser. Use a parser generator tool (e.g., [Bison](http://www.gnu.org/software/bison/), [javacc](https://javacc.java.net/), [antlr](http://www.antlr.org/), [Gold](http://goldparser.org/)) or use a parser combinator library (e.g., [JParsec](https://github.com/abailly/jparsec), [Boost::Spirit](http://boost-spirit.com/home/), [PyParsing](http://pyparsing.wikispaces.com/)).

	To use any parser you will need a grammar. For inspiration, look to the following in increasing order of complexity:
	* [Lisp grammar](http://ragnermagalhaes.blogspot.com/2007/08/bison-lisp-grammar.html)
	* [Pascal grammar](http://www.cs.utexas.edu/~novak/grammar.html)
	* [Python grammar](http://docs.python.org/2/reference/grammar.html)
	* [C grammar](http://www.lysator.liu.se/c/ANSI-C-grammar-y.html)
	* [C++ grammar](http://www.nongnu.org/hcb/)

	* Abstract Syntax Tree. See workspace/Lab3/src/compiler/AST.java for inspiration
	* Type checking (optional)
	* Optimizer (optional)
	* Code generation or Interpretation.

2. Write a parser combinator library in a language of your choosing.

	* It could work with an existing scanner, or work with characters, or a scanner could be part of the library.
	* Implement recursive descent parsing.
	* Or optionally, shift-reduce or derivative parsing.

		def parser():
			E = Nonterminal()
			T = Nonterminal()
			F = Nonterminal()
			# E -> T + E
			# E -> T - E
			E.derives(T, Symbol('+'), E)
			E.derives(T, Symbol('-'), E)

3. Create an open educational resource about compilers.

	* Saylor Foundation is interested in Creative Commons licensed textbooks and is offering prizes for them.

4. Propose your own lab (come see me first). Find a way to incorporate compilers into your senior project.

At the end of the semester, present your work. Please, no more than 10 minutes.