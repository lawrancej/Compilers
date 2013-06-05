
public class Regex {
	public interface Node {
		public <T> T accept(Visitor<T> visitor);
	}
	public interface Visitor<T> {
		T visit(EmptySet node);
		T visit(EmptyString node);
		T visit(Symbol node);
		T visit(Star node);
		T visit(Sequence node);
		T visit(Or node);
	}
	// EmptySet
	public static class EmptySet implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	// EmptyString
	public static class EmptyString implements Node {
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Symbol implements Node {
		char symbol;
		public Symbol (char symbol) {
			this.symbol = symbol;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Star implements Node {
		Node child;
		public Star(Node child) {
			this.child = child;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Sequence implements Node {
		Node a, b;
		public Sequence(Node a, Node b) {
			this.a = a; this.b = b;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Or implements Node {
		Node a, b;
		public Or(Node a, Node b) {
			this.a = a; this.b = b;
		}
		@Override
		public <T> T accept(Visitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	public static class Derivative implements Visitor<Node> {
		Nullable nullable = new Nullable();
		public char c; // Derive with respect to c
		@Override
		public Node visit(EmptySet node) {
			return node;
		}
		@Override
		public Node visit(EmptyString node) {
			return new EmptySet();
		}
		@Override
		public Node visit(Symbol node) {
			if (c == node.symbol)
				return new EmptyString();
			else
				return new EmptySet();
		}
		@Override
		public Node visit(Star node) {
			// Dc(a*) = Dc(a)a*
			return new Sequence(node.child.accept(this), node);
		}

		@Override
		public Node visit(Sequence node) {
			Node result = new Sequence(node.a.accept(this), node.b);
			// Dc(AB) = Dc(A)B if A does not contain the empty string
			if (!node.a.accept(nullable)) {
				return result;
			// Dc(AB) = Dc(A)B | Dc(B) if A contains the empty string
			} else {
				return new Or(
						result,
						node.b.accept(this)
						);
			}
		}
		@Override
		public Node visit(Or node) {
			// Dc(A | B) = Dc(A) | Dc(B)
			return new Or(node.a.accept(this), node.b.accept(this));
		}
	}
	public static class Nullable implements Visitor<Boolean> {
		@Override
		public Boolean visit(EmptySet node) {
			return false;
		}
		@Override
		public Boolean visit(EmptyString node) {
			return true;
		}
		@Override
		public Boolean visit(Symbol node) {
			return false;
		}
		@Override
		public Boolean visit(Star node) {
			return true;
		}
		@Override
		public Boolean visit(Sequence node) {
			return node.a.accept(this) && node.b.accept(this);
		}
		@Override
		public Boolean visit(Or node) {
			return node.a.accept(this) || node.b.accept(this);
		}
	}
	public static boolean match(Node regex, String string) {
		Derivative d = new Derivative();
		Nullable nullable = new Nullable();
		for (char c : string.toCharArray()) {
			d.c = c;
			regex = regex.accept(d);
		}
		return regex.accept(nullable);
	}
	public static void main(String[] args) {
		long then = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			Regex.match(new Star(new Or(
								new Sequence(new Symbol('a'),
											 new Symbol('b')
											),
								new Sequence(new Symbol('c'),new Symbol('d')))
								),"ababababc");
		}
		System.out.println(System.nanoTime() - then);
	}
}
