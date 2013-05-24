#!/usr/bin/env python2

# Everything that works is due to Sam Kline,
# Everything that's broken is due to the professor ;-)

from __future__ import print_function

class Printer(object):
    def visit(self, node):
        if isinstance(node, Left): print('<', end='')
        if isinstance(node, Right): print('>', end='')
        if isinstance(node, Increment): print('+', end='')
        if isinstance(node, Decrement): print('-', end='')
        if isinstance(node, Input): print(',', end='')
        if isinstance(node, Output): print('.', end='')
        if isinstance(node, Loop):
            print('[', end='')
            node.child.accept(self)
            print(']', end='')
        if isinstance(node, Program):
            node.child.accept(self)
            print('') # Print newline at end of Program
        if isinstance(node, Sequence):
            for child in node.children:
                child.accept(self)

class Interpreter(object):
    def __init__(self, *children):
        self.cell = bytearray(30000)
        self.pointer = 0
    def visit(self, node):
        if isinstance(node, Left): self.pointer -= 1
        if isinstance(node, Right): self.pointer += 1
        if isinstance(node, Increment):
            self.cell[self.pointer] = (self.cell[self.pointer] + 1) % 256
        if isinstance(node, Decrement):
            self.cell[self.pointer] -= 1
        if isinstance(node, Input): pass # Derp, ran out of time
        if isinstance(node, Output): print(str(self.cell[self.pointer]), end='')
        if isinstance(node, Loop):
            while self.cell[self.pointer] != 0:
                node.child.accept(self)
        if isinstance(node, Program):
            self.cell = bytearray(30000)
            self.pointer = 0
            node.child.accept(self)
        if isinstance(node, Sequence):
            for child in node.children:
                child.accept(self)

class Node(object):
    def accept(self, visitor):
        return visitor.visit(self)
class Sequence(Node):
    def __init__(self, *children):
        self.children = children
class Program(Node):
    def __init__(self, child):
        self.child = child
class Loop(Node):
    def __init__(self, child):
        self.child = child
class Output(Node): pass
class Input(Node): pass
class Decrement(Node): pass
class Increment(Node): pass
class Right(Node): pass
class Left(Node): pass

if __name__ == '__main__':
    rootNode = Program(Sequence(Increment(), Loop(Sequence(Output(), Increment()))))
    printer = Printer()
    rootNode.accept(printer)
    interpreter = Interpreter()
    rootNode.accept(interpreter)
