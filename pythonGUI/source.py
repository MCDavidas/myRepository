from PyQt5 import QtWidgets, QtGui, QtCore
from myGUI import Ui_MainWindow
import sys

data = []
data.append(('1', '2', '3'))
data.append(('4', '5', '6'))
data.append(('7', '8', '9'))

class mywindow(QtWidgets.QMainWindow):

    def keyPressEvent(self, e):
        if e.key() == QtCore.Qt.Key_F12:
            self.close()

    def __init__(self):
        super(mywindow, self).__init__()
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.tableWidget.setColumnCount(3)
        self.ui.tableWidget.setRowCount(3)
        self.ui.pushButton.clicked.connect(self.clear)

        row = 0
        for tup in data:
            col = 0
            for item in tup:
                cellinfo = QtWidgets.QTableWidgetItem(item)
                self.ui.tableWidget.setItem(row, col, cellinfo)
                col += 1
            row += 1

    def clear(self):
        self.ui.tableWidget.clear()

app = QtWidgets.QApplication([])
application = mywindow()
application.show()
 
sys.exit(app.exec())
