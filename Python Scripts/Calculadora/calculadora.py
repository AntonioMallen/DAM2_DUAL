# Import necessary modules
import sys
from PyQt6.QtWidgets import (QApplication, QWidget, QLabel
, QGridLayout,QPushButton,QComboBox,QSizePolicy) 
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QFont

class MainWindow(QWidget):

	def __init__(self):
		super().__init__() 
		self.initializeUI()
		

	def initializeUI(self):
		"""Set up the application's GUI."""
		
		self.setWindowTitle("La Calculadora de Antonio")


		self.setFixedSize(380,440)
		self.setMaximumSize(800,800)
		self.setMinimumSize(180,180)

		self.UiComponents()
		self.show()

	def UiComponents(self):
		
		layoutPrincipal = QGridLayout()
		layoutNumeros=QGridLayout()
		layoutOp=QGridLayout()

		combo = QComboBox(self)


		layoutPrincipal.addWidget(combo,0,1)
		layoutPrincipal.addLayout(layoutNumeros,1,0)
		layoutPrincipal.addLayout(layoutOp,1,1)

 
		layoutNumeros.addLayout(layoutNumeros,0,0)

	
		combo.addItem("100%")
		combo.addItem("50%")
		combo.addItem("200%")
		
		combo.currentTextChanged.connect(self.accion)
		
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
 		
		# setting alignment to the label
		self.label.setAlignment(Qt.AlignmentFlag.AlignRight)

 
		# setting font
		self.label.setFont(QFont('Arial', 15))
		Resultado.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
		# adding number button to the screen
		# creating a push button
		push1 = QPushButton("1", self)
		push1.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)

		
		
		# creating a push button
		push2 = QPushButton("2", self)
		push2.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)

		
		# creating a push button
		push3 = QPushButton("3", self)
		push3.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)

 
		# creating a push button
		push4 = QPushButton("4", self)
		push4.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
		# creating a push button
		push5 = QPushButton("5", self)
		push5.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
		# creating a push button
		push6 = QPushButton("6", self)
		push6.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
		# creating a push button
		push7 = QPushButton("7", self)
		push7.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
		# creating a push button
		push8 = QPushButton("8", self)
		push8.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)

		# creating a push button
		push9 = QPushButton("9", self)
		push9.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
		# creating a push button
		push0 = QPushButton("0", self)
		push0.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)

 
		# adding operator push button
		# creating push button
		push_equal = QPushButton("=", self)
		push_equal.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
	
		# creating push button
		push_plus = QPushButton("+", self)
		push_plus.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
		# creating push button
		push_minus = QPushButton("-", self)
		push_minus.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
		# creating push button
		push_mul = QPushButton("*", self)
		push_mul.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)
 
		# creating push button
		push_div = QPushButton("/", self)
		push_div.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)

		# del one character button
		push_del = QPushButton("Sup", self)
		push_del.setSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Expanding
		)

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


		push_del.clicked.connect(self.action_del)


		#asigno al layout de numeros los botones de numeros
		layoutNumeros.addWidget(push7,0,0)
		layoutNumeros.addWidget(push8,0,1)
		layoutNumeros.addWidget(push9,0,2)
		layoutNumeros.addWidget(push4,1,0)
		layoutNumeros.addWidget(push5,1,1)
		layoutNumeros.addWidget(push6,1,2)
		layoutNumeros.addWidget(push1,2,0)
		layoutNumeros.addWidget(push2,2,1)
		layoutNumeros.addWidget(push3,2,2)

		layoutNumeros.addWidget(push0,3,0,1,2)
		layoutNumeros.addWidget(push_del,3,2)


		layoutOp.addWidget(push_plus,0,0)
		layoutOp.addWidget(push_minus,1,0)
		layoutOp.addWidget(push_div,2,0)
		layoutOp.addWidget(push_mul,3,0)

		layoutOp.addWidget(push_equal,4,0)

		layoutNumeros.addWidget(Resultado,4,0,1,3)

		self.setLayout(layoutPrincipal)
	
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
 

	def accion(self, text):
		if(text=="50%"):
			self.setGeometry(150,150,int(380/2), int(440/2))
		if(text=="100%"):
			self.setGeometry(150,150,380,440)
		if(text=="200%"):
			self.setGeometry(150,150,380*2, 440*2)
		
			

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

 
	def action_del(self):
		# clearing a single digit
		text = self.label.text()
		print(text[:len(text)-1])
		self.label.setText(text[:len(text)-1])
	
	

if __name__ == '__main__':
	app = QApplication(sys.argv)
	window = MainWindow()
	sys.exit(app.exec())