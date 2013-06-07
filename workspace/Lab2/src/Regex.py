#!/usr/bin/env python2

#boiler plate code for the Regex lab by mcgeep
class Nullable(object):
    def visit(self, node):
        if isinstance(node, EmptySet): return False 
        if isinstance(node, EmptyString): return True 
        if isinstance(node, Symbol): return False
        if isinstance(node, Star): return True
        if isinstance(node, Sequence): return node.a.accept(self) and node.b.accept(self)
        if isinstance(node, Or): return node.a.accept(self) or node.b.accept(self)
class Derivative:
    def __init__ (self):
        self.nullable = Nullable()
        self.c = ''
    def visit(self, node):
        if isinstance(node, EmptySet): return node
        if isinstance(node, EmptyString): return EmptySet()
        if isinstance(node, Symbol):
            if self.c == node.symbol:
                return EmptyString()
            else: 
                return EmptySet()
        if isinstance(node, Star): return Sequence(node.child.accept(self), node)
        if isinstance(node, Sequence):
            result = Sequence(node.a.accept(self), node.b)
            if not node.a.accept(self.nullable):
                return result
            else:
                return Or(result,node.b.accept(self))
        if isinstance(node, Or): return Or(node.a.accept(self), node.b.accept(self))
class Node(object):
    def accept(self, visitor):
        return visitor.visit(self)
class EmptySet(Node): pass 
class EmptyString(Node): pass
class Symbol(Node):
    def __init__(self,symbol):
        self.symbol = symbol
class Star(Node):
    def __init__(self,child):
        self.child = child
class Sequence(Node):
    def __init__(self,a,b):
        self.a = a
        self.b = b
class Or(Node):
    def __init__(self,a,b):
        self.a = a
        self.b = b

def match(regex, string):
    d = Derivative()
    nullable = Nullable()
    for c in list(string):
        d.c = c
        regex = regex.accept(d)
    return regex.accept(nullable)

if __name__ == '__main__':
    print (match(Or(Symbol('a'),Symbol('b')),'a'))
    print (match(Star(Or(Symbol('a'),Symbol('b'))),'a'))