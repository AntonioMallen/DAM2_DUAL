"""Listing 10-7 to Listing 10-14
Written by Joshua Willman
Featured in "Beginning PyQt - A Hands-on Approach to GUI Programming, 2nd Ed."
"""

# Import necessary modules
from math import sqrt
import sys
from PyQt6.QtWidgets import (QApplication, QMainWindow,
    QTableWidget, QTableWidgetItem, QMenu, QInputDialog)
from PyQt6.QtGui import QAction

class MainWindow(QMainWindow):
    
    def __init__(self):
        super().__init__()
        self.initializeUI()

    def initializeUI(self):
        """Set up the application's GUI."""
        self.setMinimumSize(1000, 500)
        self.setWindowTitle("Spreadsheet - QTableWidget Example")

        # Used for copy and paste actions
        self.item_text = None

        self.setUpMainWindow()
        self.createActions()
        self.createMenu()
        self.show()

    def setUpMainWindow(self):
        """Create and arrange widgets in the main window."""
        self.table_widget = QTableWidget()

        # Set initial row and column values
        self.table_widget.setRowCount(12)
        self.table_widget.setColumnCount(12)

        # Set focus on cell in the table 
        #self.table_widget.setCurrentCell(0, 0)

        # When the horizontal headers are double-clicked, emit a signal
        h_header = self.table_widget.horizontalHeader()
        h_header.sectionDoubleClicked.connect(self.changeHeader)

        self.setCentralWidget(self.table_widget)

    def createActions(self):
        """Create the application's menu actions."""
        # Create actions for File menu
        self.quit_act = QAction("Quit", self)
        self.quit_act.setShortcut("Ctrl+Q")
        self.quit_act.triggered.connect(self.close)

        # Create actions for Table menu
        self.sumarA = QAction("Sumar", self)
        self.sumarA.triggered.connect(self.sumar)
        
        self.mediaA = QAction("Media", self)
        self.mediaA.triggered.connect(self.media)

        self.desvA = QAction("Desviacion", self)
        self.desvA.triggered.connect(self.desv)


    def createMenu(self):
        """Create the application's menu bar."""
        self.menuBar().setNativeMenuBar(False)

        # Create file menu and add actions
        file_menu = self.menuBar().addMenu('File')
        file_menu.addAction(self.quit_act)


    def contextMenuEvent(self, event):
        """Create context menu and additional actions."""
        context_menu = QMenu(self)
        context_menu.addAction(self.sumarA)
        context_menu.addAction(self.mediaA)
        context_menu.addAction(self.desvA)
       
        context_menu.addSeparator()
        # Create actions specific to the context menu
        copy_act = context_menu.addAction("Copy")
        paste_act = context_menu.addAction("Paste")
        

        # Execute the context_menu and return the action 
        # selected. mapToGlobal() translates the position 
        # of the window coordinates to the global screen 
        # coordinates. This way we can detect if a right-click 
        # occurred inside of the GUI and display the context menu
        action = context_menu.exec(self.mapToGlobal(event.pos()))

        # Check for actions selected in the context menu that were not
        # created in the menu bar
        if action == copy_act:
            self.copyItem()
        if action == paste_act:
            self.pasteItem()
    
    def changeHeader(self):
        """Change horizontal headers by returning the text 
        from input dialog."""
        col = self.table_widget.currentColumn()

        text, ok = QInputDialog.getText(
            self, "Enter Header", "Header text:")
        if ok and text != "":
            self.table_widget.setHorizontalHeaderItem(
                col, QTableWidgetItem(text)) 

    def copyItem(self):
        """If the current cell selected is not empty, 
        store the text."""
        if self.table_widget.currentItem() != None:
            self.item_text = self.table_widget.currentItem().text()

    def pasteItem(self):
        """Set item for selected cell."""
        if self.item_text != None:
            row = self.table_widget.currentRow()
            column = self.table_widget.currentColumn()
            self.table_widget.setItem(
                row, column, QTableWidgetItem(self.item_text))

    def sumar(self):
        current_column = self.table_widget.currentColumn()
        current_row = self.table_widget.currentRow()
        cantidad=0
        
        for i in range(current_row):
            try:
                self.num=float(self.table_widget.item(i,current_column).text())
                cantidad=self.num+cantidad
            except:
                self.num=0

        
        item=self.table_widget.item(current_row,current_column)
        if item is None:
            item = QTableWidgetItem(str(cantidad))
            self.table_widget.setItem(current_row,
                              current_column,
                               item)
        else:
            item.setText(str(cantidad))

        
        

    def media(self):
        current_column = self.table_widget.currentColumn()
        current_row = self.table_widget.currentRow()
        cantidad=0
        cantidadNumeros=0
        
        for i in range(current_row):
            try:
                self.num=float(self.table_widget.item(i,current_column).text())
                cantidad=self.num+cantidad
                cantidadNumeros=cantidadNumeros+1
            except:
                self.num=0

        
        item=self.table_widget.item(current_row,current_column)
        if item is None:
            item = QTableWidgetItem(str(cantidad/cantidadNumeros))
            self.table_widget.setItem(current_row,
                              current_column,
                               item)
        else:
            item.setText(str(cantidad/cantidadNumeros))

    def desv(self):
        import statistics
        current_column = self.table_widget.currentColumn()
        current_row = self.table_widget.currentRow()
        array=[]
        for i in range(current_row):
            try:
                self.num=float(self.table_widget.item(i,current_column).text())
                array.append(self.num)
            except:
                self.num=0
                array.append(self.num)
        
        item=self.table_widget.item(current_row,current_column)
        if item is None:
            item = QTableWidgetItem(str(sqrt(statistics.pvariance(array))))
            self.table_widget.setItem(current_row,
                              current_column,
                               item)
        else:
            item.setText(str(sqrt(statistics.pvariance(array))))
        


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    sys.exit(app.exec())