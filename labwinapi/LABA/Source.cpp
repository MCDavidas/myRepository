#include <iostream>
#include <string>

#include "deck.h"
#include "view.h"
#include "resource.h"

INT_PTR CALLBACK Controller(HWND hDlg, UINT message, WPARAM wParam, LPARAM lParam)
{
	static Deck myDeck;
	static MyView view(hDlg, &myDeck);

	UNREFERENCED_PARAMETER(lParam);
	switch (message)
	{
		case WM_INITDIALOG:
			return (INT_PTR)TRUE;

		case WM_CLOSE:
		{
			DestroyWindow(hDlg);
			return 0;
		}

		case WM_COMMAND:
		{
			switch LOWORD(wParam) 
			{

			case IDC_PUSHFRONT: 
			{
				int num = GetDlgItemInt(hDlg, IDC_EDIT1, NULL, FALSE);
				myDeck.PushFront(num);
				view.Update(IDC_LIST1);
				break;
			}

			case IDC_PUSHBACK:
			{
				int num = GetDlgItemInt(hDlg, IDC_EDIT1, NULL, FALSE);
				myDeck.PushBack(num);
				view.Update(IDC_LIST1);
				break;
			}

			case IDC_POPBACK:
			{
				if (myDeck.Size() == 0)
					break;
				myDeck.PopBack();
				view.Update(IDC_LIST1);
				break;
			}

			case IDC_POPFRONT:
			{
				if (myDeck.Size() == 0)
					break;
				myDeck.PopFront();
				view.Update(IDC_LIST1);
				break;
			}

			case IDC_CLEAR: 
			{
				myDeck.Clear();
				view.Update(IDC_LIST1);
				break;
			}

			case IDOK: 
			{
				EndDialog(hDlg, LOWORD(wParam));
				break;
			}
			}
		}
	}

	return (INT_PTR)FALSE;
}

int APIENTRY wWinMain(_In_ HINSTANCE hInstance,
	_In_opt_ HINSTANCE hPrevInstance,
	_In_ LPWSTR    lpCmdLine,
	_In_ int       nCmdShow)
{
	DialogBox(hInstance, MAKEINTRESOURCE(IDD_DIALOG1), NULL, Controller);
	return 0;
}
