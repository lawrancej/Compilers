# Essential questions

1. What is a compiler?

	A program that translates a (human readable) source language into a (machine readable) target language.

	**Examples:** LLVM, gcc, javac, msvc, LaTeX, Markdown processors, VHDL/Verilog hardware compilers.

1. C compilers are written in C. How can that be?

	[Ken Thompson offers an explanation](http://cm.bell-labs.com/who/ken/trust.html). Got that? Good. [Now, here's how to counteract this problem Ken discovered.](http://www.dwheeler.com/trusting-trust/)

2. What is an interpeter, and how does it differ from a compiler?

	An interpreter runs source code, whereas a compiler translates source code into machine code to run.

	**Examples:** Python, Ruby, Perl.

3. What is a language?

	A language is way of communicating information; it is the concept that words can concatenate syntactically to create meaning.

	Mathematically, a language is a set of sequences of words (i.e., a set of strings). A set is an unordered collection of unique objects. A sequence is an ordered collection.

	**Example:** This is a subset of Spanish, but not a subset of English:

	{"Yo queiro Taco Bell.", "Donde esta el bano?", ...}

	Many languages (sets) are infinite in size, meaning that it is impossible to enumerate all strings in the set. Instead, we typically use rules called grammars to construct languages.

4. What is a formal grammar?

5. What is the Chomsky hierarchy?

6. What is a regular expression?

	Conceptually, a regular expression is a way to match patterns.

	**Example:** *[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\.edu* sort of matches .edu email addresses.

	Matematically, a regular expression matches a regular language using these constructs.

	1. A character (e.g., 'g') is a regular expression.
	2. The empty string is a regular expression.
	3. The empty set is a regular expression.
	4. If *a* is a regular expression, then *a** is a regular expression that matches *a* zero or more times (Kleene closure (star)).
	5. If *a* and *b* are regular expressions, then:

	    * Regular expression *ab* matches *a* followed by *b* (Concatenation).
	    * Regular expression *a|b* matches *a* or *b* (Alternation).

7. What is a finite automaton?
8. What is an NFA and a DFA? What's the difference?
9. What is Brzozowski's derivative of a language?
10. What is a context-free grammar?
11. What is a pushdown automaton?

	A finite state machine with a stack.

12. What is an LL grammar?

	LL stands for left-right leftmost derivation. An LL grammar is a context-free grammar with no left recursion, no common prefixes for the productions of a nonterminal, and the first and follow sets for a nonterminal are disjoint.

13. What is a recursive descent parser?

	A recursive descent parser parses from the top of an LL grammar downward as it consumes input.

14. What is an LR (shift-reduce) parser?

	LR stands for left-right, rightmost derivation. Shift reduce parsing works by building the parse tree from the bottom up of an LR grammar. The parser shifts (advances) the pointer one position to the right in the input token stream. The left side of the pointer is a partially-constructed parse tree. If the left side of the pointer matches a production, the parser reduces the matched production to the appropriate nonterminal.

14. What is a visitor?

	A visitor is a class for object-oriented tree traversal. The methods of a visitor are specific for each type of node.

15. What is an abstract syntax tree?

	An abstract syntax tree is a high-level tree representation of source code in which nodes consist of language constructs.

16. What is a symbol table?

	A symbol table maps identifiers to type information for declarations of classes, methods, variables, etc. The scanner recognizes identifiers, and the parser connects identifiers to their types. The type checker uses the symbol table to assess consistency among declarations and uses.

17. How does type checking work?
18. What is optimization?
19. How does code generation work?
20. How do compiler construction techniques apply to "normal" programs?

	Parsing configuration files, or anything that deals with string input, will need to interpret it.

