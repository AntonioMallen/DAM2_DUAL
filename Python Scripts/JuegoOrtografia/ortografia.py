# Import necessary modules
import sys
from PyQt6.QtWidgets import (QApplication, QWidget, QLabel
, QGridLayout,QPushButton,QComboBox,QMainWindow,QDialogButtonBox,QVBoxLayout) 
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QFont,QAction


		
class MainWindow(QMainWindow):

	push1 =""
	push2= ""

	def __init__(self):
		super().__init__() 
		self.initializeUI()

	def initializeUI(self):
		"""Set up the application's GUI."""
		
		self.setWindowTitle("Juego de Ortografia AntonioMallen")

		self.setFixedSize(800,800)

		self.UiComponents()
		self.createActions()
		self.createMenu()
		self.show()

	def UiComponents(self):
		message = QLabel("Something happened, is that OK?")
		message.setGeometry(50,50,200,200)
		# creating a label
		Resultado=self.label = QLabel(self)
 
		# creating label multi line
		self.label.setWordWrap(True)
 
		# setting style sheet to the label
		self.label.setStyleSheet("QLabel"
								 "{"
								 "border : 1px solid black;"
								 "background : white;"
								 "}")
 		


		# setting font
		self.label.setFont(QFont('Arial', 15))
		Resultado.setGeometry(100,160,600,100)
 
		# adding number button to the screen
		# creating a push button		
		self.push1 = QPushButton("BIEN",self)
		self.push1.setGeometry(100,320,250,100)
		self.push1.setStyleSheet("background-color: rgb(0,255,0);")

		
		
		# creating a push button
		self.push2 = QPushButton("MAL",self)
		self.push2.setGeometry(450,320,250,100)
		self.push2.setStyleSheet("background-color: rgb(255,0,0);")
		
		self.push1.setEnabled(False)
		self.push2.setEnabled(False)


		# creating a push button
		push3 = QPushButton("3", self)
		push3.setGeometry(450,450,250,100)

 


		#push1.clicked.connect(self.action_del)

	
	def action_equal(self):
 
		# get the label text
		equation = self.label.text()
 
		try:
			# getting the ans
			ans = eval(equation)
 
			# setting text to the label
			self.label.setText(str(ans))
 
		except:
			# setting text to the label
			self.label.setText("Error")

 
	def salir(self):
		# clearing a single digit
		sys.exit()

	def play(self):
		self.push1.setEnabled(True)
		self.push2.setEnabled(True)

	def createActions(self):
		"""Create the application's menu actions."""
		# Create actions for File menu
		self.jugar = QAction("Jugar")
		self.jugar.setShortcut("Ctrl+W")
		self.jugar.triggered.connect(self.play)

		self.sal = QAction("Salir")
		self.sal.setShortcut("Ctrl+Q")
		self.sal.triggered.connect(self.salir)
		

	def createMenu(self):
		"""Create the application's menu bar."""
		self.menuBar().setNativeMenuBar(False)

		# Create file menu and add actions 
		file_menu = self.menuBar().addMenu("Menu")
		file_menu.addAction(self.jugar)
		file_menu.addAction(self.sal)
	
	

if __name__ == '__main__':
	app = QApplication(sys.argv)
	window = MainWindow()
	sys.exit(app.exec())