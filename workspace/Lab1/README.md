# RUM Lab

[RUM](http://esolangs.org/wiki/RUM) is an extension of [pbrain](http://esolangs.org/wiki/Pbrain), which in turn is an extension of [Brainfuck.](http://esolangs.org/wiki/Brainfuck)

In this lab, we will write a compiler (among other things) for RUM. Use RUM.java as a starting point for completing these steps; if you'd like to use a language other than Java, please let me know first and let's work something out.

Complete the following.

1. Add new classes implementing **Node** for the additional Pbrain and RUM constructs (e.g., **ProcedureDefinition**, **ProcedureInvocation**, **Repetition**, **Strings**, **Breakpoint**).
2. Extend the **Visitor** interface to handle the additional **Node** types.
3. Extend classes implementing **Visitor** to handle the new `visit` methods for each new **Node** type. In particular, extend the **Interpreter** and **Printer** for RUM. Inside the **Interpreter** class, you'll need this:

        public interface Procedure {
            void execute();
        }
        private Procedure[] procedures;

    Inside the visit method for **Program**, add this:

        procedures = new Procedure[256];

    It is NOT necessary to make multiple passes through the source code. Also: `byte` in Java is signed. To make `byte b` unsigned, do: `b & 0xFF`.

    **Hints:** When handling **ProcedureInvocation**, do this (in Java):

        procedures[cell[pointer]].execute();

    To implement **ProcedureDefinition**, use anonymous inner classes (Java) or anonymous functions (Python, C#/C++)

    When handling **Strings**, it also affects input, because a String in RUM is inserted at the beginning of the input buffer. Null terminated means the String has a zero `'\0'` character at the end.

    When handling **Repetition**, it's only necessary to worry about the primitive commands, not loops or procedure definitions.

4. Write a **Compiler** class (implementing Visitor) for RUM (it's just like the Printer class, except that it should print out equivalent code in a language of your choice such as Java, C++, Python, or Ruby).

    **Hint** Print out the boilerplate (e.g., imports and includes) when visiting the **Program** node.

5. Implement one (or more) of the following Visitor classes (or suggest something else to me):

   * **VisualInterpreter**. Render the contents of the array and pointer into a nice GUI.

   * **AnotherCompiler**. Output code for another language, or even *gasp* assembly code that works with `masm`, `as` or `nasm`.

   * **Optimizer**. Traverse through the AST and create a new AST that improves execution performance (e.g., replace runs of the same instruction with a single instruction, so ++++ becomes a single Increment node with a count of 4, or replace a Sequence pattern with a special Node). This would involve changing the classes implementing Node, or even implementing new Node types.

Be sure to test that all of this works for valid RUM programs. For example, this is "Hello, World!" in RUM:

        (++++++++++<[>+>+<<-]>>[<<+>>-])>::::::::::::::<<<<<<<--------.>>>---------.+++++++..>---------.<<<<<<<------.<--------.>>>>>---.>>>.+++.<.--------.<<<<<<<+.

Try to see if it works with one of [these programs](http://esoteric.sange.fi/brainfuck/bf-source/prog/), particularly one that accepts input from the user.