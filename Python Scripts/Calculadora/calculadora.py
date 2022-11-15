# importing libraries
from PyQt6.QtWidgets import *
from PyQt6 import QtCore, QtGui
from PyQt6.QtGui import *
from PyQt6.QtCore import *

import sys


class Window(QMainWindow):

	def __init__(self):
		
		super().__init__()
		self.setWindowTitle("Calculadora")
		
		# setting geometry
		self.setGeometry(0, 0, 380, 440)

		# calling method
		self.UiComponents()

		# showing all the widgets
		self.show()

		# method for widgets
	def UiComponents(self):
		layoutPrincipal = QGridLayout()
		self.setLayout(layoutPrincipal)
		layoutNums= QGridLayout()
		layoutOps=QGridLayout()
		layoutEq =QGridLayout()
		layoutResu=QGridLayout()
		layoutPrincipal.addLayout(layoutNums,0,0)
		layoutPrincipal.addLayout(layoutOps,0,1)
		layoutPrincipal.addLayout(layoutResu,1,0)
		layoutPrincipal.addLayout(layoutEq,1,1)

		# creating a label
		self.label = QLabel(self)

		# setting geometry to the label

		# creating label multi line
		self.label.setWordWrap(True)

		# setting style sheet to the label
		self.label.setStyleSheet("QLabel"
								"{"
								"border : 4px solid black;"
								"background : white;"
								"}")

		# setting alignment to the label
		self.label.setAlignment(Qt.AlignmentFlag.AlignRight)

		# setting font
		self.label.setFont(QFont('Arial', 15))

		


		# adding number button to the screen
		# creating a push button

		push1 = QPushButton("1", self)
		layoutNums.addWidget(push1,0,0)
		push1.setFixedSize(58,58)
		
		# setting geometry
		#push1.setGeometry(16, 150, 58, 58)
		
		# creating a push button
		push2 = QPushButton("2", self)
		layoutNums.addWidget(push2,0,1)
		push2.setFixedSize(58,58)
		# setting geometry	 layoutNums.addWidget(push2)



		# creating a push button
		push3 = QPushButton("3", self)
		layoutNums.addWidget(push3,0,2)
		push3.setFixedSize(58,58)
		# creating a push button

		push4 = QPushButton("4", self)
		layoutNums.addWidget(push4,1,0)
		push4.setFixedSize(58,58)
		# setting geometry


		# creating a push button
		push5 = QPushButton("5", self)

		# setting geometry
	

		# creating a push button
		push6 = QPushButton("6", self)

		# setting geometry


		# creating a push button
		push7 = QPushButton("7", self)

		# setting geometry

		# creating a push button
		push8 = QPushButton("8", self)

		# setting geometry

		# creating a push button
		push9 = QPushButton("9", self)

		# setting geometry

		# creating a push button
		push0 = QPushButton("0", self)

		# setting geometry

		# adding operator push button
		# creating push button
		push_equal = QPushButton("=", self)

		# setting geometry

		## adding equal button a color effect
		#c_effect = QGraphicsColorizeEffect()
		#c_effect.setColor(Qt.)
		#push_equal.setGraphicsEffect(c_effect)

		# creating push button
		push_plus = QPushButton("+", self)

		# setting geometry

		# creating push button
		push_minus = QPushButton("-", self)

		# setting geometry
		
		# creating push button
		push_mul = QPushButton("*", self)

		# setting geometry
	
		# creating push button
		push_div = QPushButton("/", self)

		# setting geometry
	


		# clear button
		push_clear = QPushButton("Supr", self)


		# del one character button
		push_del = QPushButton("Del", self)


		# adding action to each of the button
		push_minus.clicked.connect(self.action_minus)
		push_equal.clicked.connect(self.action_equal)
		push0.clicked.connect(self.action0)
		push1.clicked.connect(self.action1)
		push2.clicked.connect(self.action2)
		push3.clicked.connect(self.action3)
		push4.clicked.connect(self.action4)
		push5.clicked.connect(self.action5)
		push6.clicked.connect(self.action6)
		push7.clicked.connect(self.action7)
		push8.clicked.connect(self.action8)
		push9.clicked.connect(self.action9)
		push_div.clicked.connect(self.action_div)
		push_mul.clicked.connect(self.action_mul)
		push_plus.clicked.connect(self.action_plus)
		push_clear.clicked.connect(self.action_clear)
		push_del.clicked.connect(self.action_del)


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
			self.label.setText("Wrong Input")

	def action_plus(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + " + ")

	def action_minus(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + " - ")

	def action_div(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + " / ")

	def action_mul(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + " * ")


	def action0(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "0")

	def action1(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "1")

	def action2(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "2")

	def action3(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "3")

	def action4(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "4")

	def action5(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "5")

	def action6(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "6")

	def action7(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "7")

	def action8(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "8")

	def action9(self):
		# appending label text
		text = self.label.text()
		self.label.setText(text + "9")

	def action_clear(self):
		# clearing the label text
		self.label.setText("")

	def action_del(self):
		# clearing a single digit
		text = self.label.text()
		print(text[:len(text)-1])
		self.label.setText(text[:len(text)-1])


# create pyqt5 app
App = QApplication(sys.argv)

# create the instance of our Window
window = Window()

# start the app
sys.exit(App.exec())
