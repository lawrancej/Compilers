# Lab 3/Final project

Do one of the following by yourself or with a parter or two:

1. Implement the phases of a compiler for a language of your choosing or invention.

	* Scanner (lexer/tokenizer). Flex, JFlex, or roll your own.
	* Parser. Bison, Yacc, javacc, antlr, Boost::Spirit, PyParsing.
	* Abstract Syntax Tree
	* Type checking
	* Optimizer
	* Code generation.

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