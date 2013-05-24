#include <vector>
#include <iostream>
#include <stdarg.h>

using namespace std;

/* C++ version of RUM starter code. No destructors (yet), so it leaks memory like a sieve. */

class RUM
{

};

class Visitor;

class Node
{
	public:
		virtual void accept(Visitor * visitor) = 0;
};

class Left : public Node
{
	public:
		void accept(Visitor * visitor);
};
class Right : public Node
{
	public:
		void accept(Visitor * visitor);
};
class Increment : public Node
{
	public:
		void accept(Visitor * visitor);
};
class Decrement : public Node
{
	public:
		void accept(Visitor * visitor);
};
class Input : public Node
{
	public:
		void accept(Visitor * visitor);
};
class Output : public Node
{
	public:
		void accept(Visitor * visitor);
};
class Loop : public Node
{
	public:
		Node * child;
		Loop(Node * child) : child (child) {}
		void accept(Visitor * visitor);
};
class Sequence : public Node {
	public:
		vector<Node *> children;
		Sequence(Node * child, ...);
		void accept(Visitor * visitor);
};
class Program : public Node {
	public:
		Node * child;
		Program(Node * child) : child (child) {}
		void accept(Visitor * visitor);
};

class Visitor
{
	public:
		virtual void visit(Left * node) = 0;
		virtual void visit(Right * node) = 0;
		virtual void visit(Increment * node) = 0;
		virtual void visit(Decrement * node) = 0;
		virtual void visit(Input * node) = 0;
		virtual void visit(Output * node) = 0;
		virtual void visit(Loop * node) = 0;
		virtual void visit(Sequence * node) = 0;
		virtual void visit(Program * node) = 0;
};

Sequence::Sequence(Node * child, ...)
{
	Node * next_child = NULL;
	va_list list;
	children.push_back(child);
    va_start(list, child);
    while ((next_child = va_arg(list, Node *)) != NULL)
    {
    	children.push_back(next_child);
    }
    va_end(list);
}

void Left::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Right::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Increment::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Decrement::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Input::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Output::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Loop::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Sequence::accept(Visitor * visitor)
{
	visitor->visit(this);
}

void Program::accept(Visitor * visitor)
{
	visitor->visit(this);
}

class Printer : public Visitor {
	public:
		void visit(Left * node);
		void visit(Right * node);
		void visit(Increment * node);
		void visit(Decrement * node);
		void visit(Input * node);
		void visit(Output * node);
		void visit(Loop * node);
		void visit(Sequence * node);
		void visit(Program * node);
};

void Printer::visit(Left * node)
{
	cout << '<';
}

void Printer::visit(Right * node)
{
	cout << '>';
}

void Printer::visit(Increment * node)
{
	cout << '+';
}
void Printer::visit(Decrement * node)
{
	cout << '-';
}
void Printer::visit(Input * node)
{
	cout << ',';
}
void Printer::visit(Output * node)
{
	cout << '.';
}
void Printer::visit(Loop * node)
{
	cout << '[';
	node->child->accept(this);
	cout << ']';
}
void Printer::visit(Sequence * node)
{
	for (vector<Node *>::iterator child = node->children.begin(); child != node->children.end(); ++child)
	{
		(*child)->accept(this);
	}
}
void Printer::visit(Program * node)
{
	node->child->accept(this);
}

class Interpreter : public Visitor {
	char cell[30000];
	unsigned short pointer;
	public:
		void visit(Left * node);
		void visit(Right * node);
		void visit(Increment * node);
		void visit(Decrement * node);
		void visit(Input * node);
		void visit(Output * node);
		void visit(Loop * node);
		void visit(Sequence * node);
		void visit(Program * node);
};

void Interpreter::visit(Left * node)
{
	pointer++;
}

void Interpreter::visit(Right * node)
{
	pointer--;
}

void Interpreter::visit(Increment * node)
{
	cell[pointer]++;
}
void Interpreter::visit(Decrement * node)
{
	cell[pointer]--;
}
void Interpreter::visit(Input * node)
{
	cin >> cell[pointer];
}
void Interpreter::visit(Output * node)
{
	cout << cell[pointer];
}
void Interpreter::visit(Loop * node)
{
	while (cell[pointer])
	{
		node->child->accept(this);
	}
}
void Interpreter::visit(Sequence * node)
{
	for (vector<Node *>::iterator child = node->children.begin(); child != node->children.end(); ++child)
	{
		(*child)->accept(this);
	}
}
void Interpreter::visit(Program * node)
{
	for (int i = 0; i < 30000; i++) cell[i] = 0;
	pointer = 0;
	node->child->accept(this);
}

int main(int argc, char** argv)
{
	Program * program = new Program(new Sequence(
		new Increment(), new Loop(
			new Sequence(new Output(), new Increment(), NULL)), NULL));
	Printer * printer = new Printer();
	printer->visit(program);
	Interpreter * interpreter = new Interpreter();
	interpreter->visit(program);
}