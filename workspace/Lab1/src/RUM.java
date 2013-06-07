import java.io.IOException;
import java.util.LinkedList;

public class RUM {
	public interface Node {
		public <T> T accept(Visitor<T> visitor);
	}
	public interface Visitor<T> {
		public T visit(Left node);
		public T visit(Right node);
		public T visit(Increment node);
		public T visit(Decrement node);
		public T visit(Input node);
		public T visit(Output node);
		public T visit(Loop node);
		public T visit(Program node);
		public T visit(Sequence node);
		public T visit(ProcedureDefinition node);
		public T visit(ProcedureInvocation node);
	}
	/* Define various node types */
	public static class ProcedureDefinition implements Node {
		Node child;
		public ProcedureDefinition (Node child) {
			this.child = child;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class ProcedureInvocation implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Left implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Right implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Increment implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Decrement implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Input implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Output implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Loop implements Node {
		Node child;
		public Loop (Node child) {
			this.child = child;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Program implements Node {
		Node child;
		public Program (Node child) {
			this.child = child;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Sequence implements Node {
		Node[] children;
		public Sequence(Node...children) {
			this.children = children;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	/* Define the visitors themselves */
	public static class Printer implements Visitor<Void> {

		@Override
		public Void visit(Left node) {
			System.out.print('<');
			return null;
		}

		@Override
		public Void visit(Right node) {
			System.out.print('>');
			return null;
		}

		@Override
		public Void visit(Increment node) {
			System.out.print('+');
			return null;
		}

		@Override
		public Void visit(Decrement node) {
			System.out.print('-');
			return null;
		}

		@Override
		public Void visit(Input node) {
			System.out.print(',');
			return null;
		}

		@Override
		public Void visit(Output node) {
			System.out.print('.');
			return null;
		}

		@Override
		public Void visit(Loop node) {
			System.out.print('[');
			node.child.accept(this);
			System.out.print(']');
			return null;
		}

		@Override
		public Void visit(Program node) {
			node.child.accept(this);			
			return null;
		}

		@Override
		public Void visit(Sequence node) {
			for (Node child : node.children) {
				child.accept(this);
			}
			return null;
		}

		@Override
		public Void visit(ProcedureDefinition node) {
			System.out.print('(');
			node.child.accept(this);
			System.out.print(')');
			return null;
		}

		@Override
		public Void visit(ProcedureInvocation node) {
			System.out.print(':');
			return null;
		}
		
	}
	public static class Interpreter implements Visitor<Void> {
		public interface Procedure {
		    void execute();
		}
		private Procedure[] procedures;
		byte[] cell;
		int pointer;
		@Override
		public Void visit(Left node) {
			pointer--;
			return null;
		}
		@Override
		public Void visit(Right node) {
			pointer++;
			return null;
		}
		@Override
		public Void visit(Increment node) {
			cell[pointer]++;
			return null;
		}
		@Override
		public Void visit(Decrement node) {
			cell[pointer]--;
			return null;
		}
		@Override
		public Void visit(Input node) {
			try {
				cell[pointer] = (byte) System.in.read();
			} catch (IOException e) {
				// Seriously, Java?!?
			}
			return null;
		}
		@Override
		public Void visit(Output node) {
			System.out.print((char)cell[pointer]);
			return null;
		}
		@Override
		public Void visit(Loop node) {
			while (cell[pointer] != 0) {
				node.child.accept(this);
			}
			return null;
		}
		@Override
		public Void visit(Program node) {
			cell = new byte[30000];
			pointer = 0;
			procedures = new Procedure[256];
			node.child.accept(this);
			return null;
		}
		@Override
		public Void visit(Sequence node) {
			for (Node child : node.children) {
				child.accept(this);
			}
			return null;
		}
		@Override
		public Void visit(final ProcedureDefinition node) {
			final Interpreter that = this;
			procedures[cell[pointer]] = new Procedure() {
				@Override
				public void execute() {
					// Continue traversing through here
					node.child.accept(that);
				}
			};
			return null;
		}
		@Override
		public Void visit(ProcedureInvocation node) {
			procedures[cell[pointer]].execute();
			return null;
		}
	}
	int i = 0;
	public Sequence parseSequence(String source) {
		LinkedList<Node> sequence = new LinkedList<Node>();
		/* Consume one character at a time */
		while (i < source.length()) {
			char command = source.charAt(i);
			i++;
			/*
			 * repetition: look ahead: look for digits (once you don't see a digit, stop
			 * convert teh string into a number
			 * 
			 * optimization: look ahead: if you see the same command over and over, keep looking.
			 * once you see something different stop.
			 * 
			 */
			/* Add the proper Node for each command to the sequence */
			switch (command) {
			case '>': sequence.add(new Right(/* repetition */)); break;
			case '<': sequence.add(new Left()); break;
			case '+': sequence.add(new Increment()); break;
			case '-': sequence.add(new Decrement()); break;
			case '.': sequence.add(new Output()); break;
			case ',': sequence.add(new Input()); break;
			case '[': sequence.add(new Loop(parseSequence(source))); break;
			case ']': return new Sequence(sequence.toArray(new Node[0]));
			case ':': sequence.add(new ProcedureInvocation(/* repetition */)); break;
			case '(': sequence.add(new ProcedureDefinition(parseSequence(source))); break;
			case ')': return new Sequence(sequence.toArray(new Node[0]));
			}
		}
		return new Sequence(sequence.toArray(new Node[0]));
	}
	public Program parse (String source) {
		return new Program(parseSequence(source));
	}
	public static void main(String[] args) {
//		Program program = new Program(new Sequence(
//				new Increment(), new Loop(new Sequence(
//						new Output(), new Increment()))));
		Program program = new RUM().parse("(++++++++++<[>+>+<<-]>>[<<+>>-])>::::::::::::::<<<<<<<--------.>>>---------.+++++++..>---------.<<<<<<<------.<--------.>>>>>---.>>>.+++.<.--------.<<<<<<<+.");
		Interpreter interpreter = new Interpreter();
		program.accept(interpreter);
//		Printer printer = new Printer();
//		printer.visit(program);
	}
	
}