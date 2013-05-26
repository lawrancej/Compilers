#!/usr/bin/env python2

# Everything that works is due to Sam Kline,
# Everything that's broken is due to the professor ;-)

from __future__ import print_function
import sys

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
            self.cell[self.pointer] = (self.cell[self.pointer] - 1) % 256
        if isinstance(node, Input): sys.stdin.read(1) # Beware: untested
        if isinstance(node, Output): print(chr(self.cell[self.pointer]), end='')
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

i = 0

def parse(string):
    sequence = []
    global i
    while i < len(string):
        ch = string[i]
        i += 1
        if ch == '<': sequence.append(Left())
        if ch == '>': sequence.append(Right())
        if ch == '+': sequence.append(Increment())
        if ch == '-': sequence.append(Decrement())
        if ch == '.': sequence.append(Output())
        if ch == ',': sequence.append(Input())
        if ch == '[': sequence.append(Loop(parse(string)))
        if ch == ']': return Sequence(*sequence)
    return Sequence(*sequence)

def parseProgram(string):
    global i
    i = 0
    return Program(parse(string))

if __name__ == '__main__':
#    rootNode = Program(Sequence(Increment(), Loop(Sequence(Output(), Increment()))))
    rootNode = parseProgram("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.")
    printer = Printer()
    rootNode.accept(printer)
    interpreter = Interpreter()
    rootNode.accept(interpreter)
