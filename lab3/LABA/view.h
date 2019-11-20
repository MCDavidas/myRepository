#pragma once

#include <Windows.h>
#include <string>
#include "deck.h"

using namespace std;

class MyView
{
private:
	HWND hDlg;
	Deck* obj;

public:
	void Update(int IDC);
	MyView(HWND hDlg, Deck* newObj);

};