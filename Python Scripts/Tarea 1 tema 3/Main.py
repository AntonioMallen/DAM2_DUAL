# Import necessary modules
import sys
from PyQt6.QtWidgets import (QApplication, QFrame, QSizePolicy
,QMainWindow,QLabel,QGridLayout,QWidget) 
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QFont,QAction

		
class MainWindow(QMainWindow):

	norte =""
	sur= ""

	def __init__(self):
		super().__init__() 
		self.initializeUI()

	def initializeUI(self):
		"""Set up the application's GUI."""
		
		self.setWindowTitle("Ejercicio 1 Tema 3")

		self.setFixedSize(400,400)

		self.UiComponents()
		self.show()

	def UiComponents(self):
		"""
		a
		"""
		main=QWidget()
		gridMain = QGridLayout()

		self.setCentralWidget(main)

		main.setLayout(gridMain)

		#Ventana Box
		label = QLabel("Antonio")
		grid1 = QGridLayout()
		grid1.addWidget(label, 0, 0)

		frame1 = QFrame(main) #
		frame1.setFrameShape(QFrame.Shape.Box)
		size_policy = QSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Preferred)
		frame1.setSizePolicy(size_policy) # expansión
		frame1.setFrameShadow(QFrame.Shadow.Raised)
		frame1.setLineWidth(3)
		frame1.setMidLineWidth(5)

		frame1.setLayout(grid1)
		
		gridMain.addWidget(frame1,0,0,1,1)

		# Ventana Panel
		label2 = QLabel("Mallen")
		grid2 = QGridLayout()
		grid2.addWidget(label2, 0, 0)

		frame2 = QFrame(main) #
		frame2.setFrameShape(QFrame.Shape.Panel)
		size_policy = QSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Preferred)
		frame2.setSizePolicy(size_policy) # expansión
		frame2.setFrameShadow(QFrame.Shadow.Plain)
		frame2.setLineWidth(8)
		frame2.setMidLineWidth(1)

		frame2.setLayout(grid2)
		
		gridMain.addWidget(frame2,0,1,1,1)

		# Ventana StyledPanel
		label3 = QLabel("Gimeno")
		grid3 = QGridLayout()
		grid3.addWidget(label3, 0, 0)

		frame3 = QFrame(main) #
		frame3.setFrameShape(QFrame.Shape.StyledPanel)
		size_policy = QSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Preferred)
		frame3.setSizePolicy(size_policy) # expansión
		frame3.setFrameShadow(QFrame.Shadow.Sunken)
		frame3.setLineWidth(1)
		frame3.setMidLineWidth(7)

		frame3.setLayout(grid3)
		gridMain.addWidget(frame3,1,0,1,1)

		# Ventana NoFrame
		label4 = QLabel("2ºDAM-DUAL")
		grid4 = QGridLayout()
		grid4.addWidget(label4, 0, 0)

		frame4 = QFrame(main) 
		frame4.setFrameShape(QFrame.Shape.NoFrame)
		size_policy = QSizePolicy(
			QSizePolicy.Policy.Expanding,
			QSizePolicy.Policy.Preferred)
		frame4.setSizePolicy(size_policy) # expansión
		frame4.setFrameShadow(QFrame.Shadow.Plain)
		frame4.setLineWidth(5)
		frame4.setMidLineWidth(2)

		frame4.setLayout(grid4)

		gridMain.addWidget(frame4,1,1,1,1)

	
	

if __name__ == '__main__':
	app = QApplication(sys.argv)
	window = MainWindow()
	sys.exit(app.exec())