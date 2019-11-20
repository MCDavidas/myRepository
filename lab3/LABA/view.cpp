#include "view.h"

//DeckView//
void MyView::Update(int IDC)
{
	string str = obj->ToString();

	SendDlgItemMessage(hDlg, IDC, LB_DELETESTRING, NULL, NULL);
	SendDlgItemMessage(hDlg, IDC, LB_ADDSTRING, NULL, (LPARAM)str.c_str());
};

MyView::MyView(HWND hDlg, Deck* newObj) : hDlg(hDlg), obj(newObj) 
{
};