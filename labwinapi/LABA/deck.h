#pragma once

#include <string>
#include <iostream>

using namespace std;

class Deck;
class Element;

class Visitor
{
public:
	virtual void VisitElement(Element &curr) = 0;
};

class StrVisitor : public Visitor
{
private:
	string str = "";

public:
	void VisitElement(Element &curr);
	string GetStr();

};

class Element
{
private:
	int value;

public:
	Element(int curr);
	Element();
	int GetValue();
	friend istream& operator >> (istream &in, Element &curr);
	friend ostream& operator << (ostream &out, const Element &curr);
	void Accept(Visitor &v);

};

class DeckIterator
{
private:
	Deck *currDeck;
	int current;

public:
	DeckIterator(Deck *aDeck);
	void First();
	void Last();
	void Next();
	bool IsDone();
	Element CurrentItem();

};

class Deck
{
private:

	int capacity;
	Element *dArray;
	int head;
	int tail;

	void Extend();
	void Reduce();

public:

	friend class DeckIterator;

	Deck(int newCapacity = 2);
	Deck(const Deck& curr);
	Deck(Deck&& curr);
	Deck(const std::initializer_list<int> newList);
	Deck& operator = (const Deck& curr);
	Deck& operator = (Deck&& curr);
	~Deck();
	int Size();
	void Clear();
	Element Front();
	Element Back();
	void PushFront(int curr);
	void PopFront();
	void PushBack(int curr);
	void PopBack();
	friend istream& operator >> (istream &in, Deck &curr);
	string ToString();

};

ostream& operator << (ostream &out, Deck &curr);
