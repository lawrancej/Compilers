# Regex Lab

1. Write a visitor to print Regex

   `Printer` should implement `Visitor<String>`

   This is for debugging purposes.

2. Speed up the performance by constructing objects wisely.
	* Reduce:  Avoid constructing new objects
	* Reuse:   Use caching (memoization) to reuse existing objects

		Make **EmptySet** and **EmptyString** [singletons](http://en.wikipedia.org/wiki/Singleton_pattern).
		Make **Symbol**, **Star**, **Sequence**, **Or** into [Flyweights](http://en.wikipedia.org/wiki/Flyweight_pattern).
		Compaction: [Compaction (see here for details)](http://matt.might.net/articles/parsing-with-derivatives/).
	* Recycle: (Java/Python/C# do GC for you, in C++, a visitor would delete trees)

3. Make the interface a bit nicer.
	E.g., A sequence constructor that can take an arbitrary number of arguments and produce the right thing.
	E.g., Given a string, produce the regex that matches that string literally.


Extra credit: [parse a string to construct a Regex](http://matt.might.net/articles/parsing-regex-with-recursive-descent/)
