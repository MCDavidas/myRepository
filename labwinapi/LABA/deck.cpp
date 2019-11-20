#include "deck.h"

//Visitor//
void StrVisitor::VisitElement(Element &curr)
{
	str += to_string( curr.GetValue() ) + " ";
}

string StrVisitor::GetStr()
{
	return str;
}

//Element//
Element::Element(int curr)
{
	value = curr;
}

Element::Element()
{
	value = 0;
}

int Element::GetValue()
{
	return value;
}

istream& operator >> (istream &in, Element &curr)
{
	in >> curr.value;
	return in;
}

ostream& operator << (ostream &out, const Element &curr)
{
	out << curr.value;
	return out;
}

void Element::Accept(Visitor &v)
{
	v.VisitElement(*this);
}

//Deck//
void Deck::Extend()
{
	if (tail == (head + 1) % capacity)
	{
		Element *newArray = new Element[capacity * 2];

		int i = 0;
		while (tail != head)
		{
			newArray[i] = dArray[tail];
			tail++;
			tail %= capacity;
			i++;
		}

		delete[] dArray;
		dArray = newArray;
		tail = 0;
		head = i;
		capacity *= 2;
	}
}

void Deck::Reduce()
{
	if (Size() < capacity / 4)
	{
		Element *newArray = new Element[capacity / 2];

		int i = 0;
		while (tail != head)
		{
			newArray[i] = dArray[tail];
			tail++;
			tail %= capacity;
		}

		delete[] dArray;
		dArray = newArray;
		tail = 0;
		head = i;
		capacity /= 2;
	}
}

Deck::Deck(int newCapacity)
{
	dArray = new Element[newCapacity];
	head = tail = 0;
	capacity = newCapacity;
}

Deck::Deck(const std::initializer_list<int> newList) : Deck(newList.size())
{
	int counter = 0;
	for (auto item : newList) 
	{
		dArray[counter] = item;
		counter ++;
	}

	capacity = counter;
	tail = 0;
	head = counter;
}

Deck::~Deck()
{
	delete[] dArray;
}

int Deck::Size()
{
	if (tail > head)
		return capacity + head - tail;
	else
		return head - tail;
}

void Deck::Clear()
{
	delete[] dArray;
	dArray = new Element[2];
	head = tail = 0;
	capacity = 2;
}

Element Deck::Front()
{
	return dArray[head - 1];
}

Element Deck::Back()
{
	return dArray[tail];
}

void Deck::PushFront(int curr)
{
	Extend();
	Element newItem(curr);
	dArray[head] = newItem;
	head = (head + 1) % capacity;
}

void Deck::PopFront()
{
	Reduce();
	head = (head - 1 + capacity) % capacity;
}

void Deck::PushBack(int curr)
{
	Extend();
	tail = (tail - 1 + capacity) % capacity;
	Element newItem(curr);
	dArray[tail] = newItem;
}

void Deck::PopBack()
{
	Reduce();
	tail = (tail + 1) % capacity;
}

istream& operator >> (istream &in, Deck &curr)
{
	curr.Clear();

	int k;
	in >> k;

	curr.capacity = k * 2;
	curr.dArray = new Element[curr.capacity];
	curr.tail = 0;

	for (curr.head = 0; curr.head != k; curr.head++)
		in >> curr.dArray[curr.head];

	return in;
}

ostream& operator << (ostream &out, Deck &curr)
{
	DeckIterator it(&curr);
	for (it.First(); !it.IsDone(); it.Next())
		out << it.CurrentItem() << " ";

	return out;
}

Deck& Deck::operator = (const Deck& curr)
{
	if (this == &curr)
		return *this;

	delete[] dArray;
	dArray = new Element[curr.capacity];
	for (int i = 0; i != curr.capacity; i++)
		dArray[i] = curr.dArray[i];

	tail = curr.tail;
	capacity = curr.capacity;
	head = curr.head;

	return *this;
}

Deck& Deck::operator = (Deck&& curr)
{
	if (this == &curr)
		return *this;

	delete[] dArray;
	dArray = curr.dArray;
	tail = curr.tail;
	capacity = curr.capacity;
	head = curr.head;

	curr.head = curr.tail = curr.capacity = 0;
	curr.dArray = nullptr;
	return *this;
}

Deck::Deck(const Deck& curr) : capacity(curr.capacity), tail(curr.tail), head(curr.head)
{
	dArray = new Element[curr.capacity];
	for (int i = 0; i != curr.capacity; i++)
		dArray[i] = curr.dArray[i];
}

Deck::Deck(Deck&& curr) : capacity(curr.capacity), tail(curr.tail), head(curr.head), dArray(curr.dArray)
{
	curr.head = curr.tail = curr.capacity = 0;
	curr.dArray = nullptr;
}

string Deck::ToString()
{
	StrVisitor v;
	DeckIterator it(this);
	for (it.First(); !it.IsDone(); it.Next())
		it.CurrentItem().Accept(v);

	return v.GetStr();
}

//DeckIterator//
DeckIterator::DeckIterator(Deck *aDeck)
{
	currDeck = aDeck;
	current = currDeck->tail;
}

void DeckIterator::First()
{
	current = currDeck->tail;
}

void DeckIterator::Last()
{
	current = currDeck->head - 1;
}

void DeckIterator::Next()
{
	if (!IsDone())
		current = (current + 1) % currDeck->capacity;
}

bool DeckIterator::IsDone()
{
	return current == currDeck->head;
}

Element DeckIterator::CurrentItem()
{
	if (!IsDone())
		return currDeck->dArray[current];
	return 0;
}