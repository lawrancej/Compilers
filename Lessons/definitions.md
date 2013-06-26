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
12. What is an LL (recursive descent) parser?
13. What is an LR (shift-reduce) parser?
14. What is a visitor?
15. What is an abstract syntax tree?
16. What is optimization?
17. How do compiler construction techniques apply to "normal" programs?

	Parsing configuration files, or anything that deals with string input, will need to interpret it.