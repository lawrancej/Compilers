# Lab 3/Final project

Do one of the following by yourself or with a parter or two:

1. Implement the phases of a compiler or interpreter for a language of your choosing or invention. The language of your compiler isn't necessarily limited to programming languages (e.g., a Markdown translator would be acceptable).
	* Scanner (lexer/tokenizer). Use Flex, JFlex, or scanners included with the parser tools listed next.
	* Parser. Use a parser combinator library (e.g., [JParsec](https://github.com/abailly/jparsec), [Boost::Spirit](http://boost-spirit.com/home/), [PyParsing](http://pyparsing.wikispaces.com/)), or if you like more challenge, use a parser generator tool (e.g., [Bison](http://www.gnu.org/software/bison/), [javacc](https://javacc.java.net/), [antlr](http://www.antlr.org/), [Gold](http://goldparser.org/)).
	To use any parser you will need a grammar. For inspiration, look to the following in increasing order of complexity: [Lisp grammar](http://ragnermagalhaes.blogspot.com/2007/08/bison-lisp-grammar.html), [Pascal grammar](http://www.cs.utexas.edu/~novak/grammar.html), [Python grammar](http://docs.python.org/2/reference/grammar.html), [C grammar](http://www.lysator.liu.se/c/ANSI-C-grammar-y.html), [C++ grammar](http://www.nongnu.org/hcb/).
	* Abstract Syntax Tree. See workspace/Lab3/src/compiler/AST.java for inspiration.
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

3. Create an open educational resource about compilers. Alternatively, develop a slick 10-15 minute presentation about some compiler topic of your interest in depth in such a way that a freshman in computer science could understand it. Preferably in a way that it could be reused.
	* Saylor Foundation is interested in [Creative Commons licensed textbooks](http://www.saylor.org/otc/) and is offering prizes for them.
	* [This could be a good starting point](https://github.com/lawrancej/CompilerDesign), as are the course notes from this year and other resources on the web.

4. Work on an existing open source project that is either a compiler or translator or somewhat related. Fix some bugs. For example:
	* [Pandoc](http://johnmacfarlane.net/pandoc/) can translate among document formats, but the generated HTML slideshows can't have slides link to each other, which is not nice.
	* [Python implementation of derivative parsing](https://gist.github.com/pervognsen/815b208b86066f6d7a00) can match but cannot seem to generate parse trees.
	* [ReviewBoard](http://www.reviewboard.org/) doesn't have anonymous code review. But, it'd be nice to be able to have a group of students audit each other's code in such a way as they could assess each without knowing who is reviewing each other's code.

5. Propose your own lab (come see me first). Find a way to incorporate compilers into your senior project.

At the end of the semester, present your work. Please, no more than 10 minutes.