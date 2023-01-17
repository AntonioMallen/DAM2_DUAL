import sys

from PyQt6.QtWidgets import (
    QApplication, QDialog, QMainWindow, QMessageBox,
)
from PyQt6.QtGui import QAction

from MD2 import Ui_MainWindow

class Window(QMainWindow):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.setupUi()
    
    def setupUi(self):
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.createMenu()
        
        
        self.initializeUI()
        self.show()
        

    def initializeUI(self):
        self.ui.TextGrande.setText("")
        self.ui.pushButton.setEnabled(False)
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.textosAux.setVisible(False)
        self.ui.pushButton_6.setVisible(False)  
        self.ui.pushButton_7.setVisible(False)  

        self.ui.pushButton.clicked.connect(self.salaNorte)
       

    def ayuda(self):
        self.popup= QMessageBox()
        self.popup.setWindowTitle("Guia")
        self.popup.setText("""Bienvenido a mi Mazmorra,para jugar 
puedes seleccionar en la parte del menu arriba la opcion "Jugar",
una vez haya sido dado este boton, se habilitaran las distintas 
habitaciones. Puedes ir seleccionando una a una las distintas 
habitaciones y para entrar tendras que darle al boton de abajo 
"jugar", ademas podras salir de una habitacion con el boton "salir".

Mucha suerte
    """)
        self.popup.exec()


    def salaNorte(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Norte")
        self.ui.pushButton.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(100,255,100)\n")
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)

    def salir(self):
        sys.exit()

    def play(self):
        self.ui.pushButton.setEnabled(True)
        self.ui.pushButton_2.setEnabled(True)
        self.ui.pushButton_4.setEnabled(True)
        self.ui.pushButton_5.setEnabled(True)
        self.ui.pushButton_6.setVisible(True)  
        self.ui.pushButton_7.setVisible(True)  
        self.ui.pushButton.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(255,255,255)\n")

        self.ui.pushButton_2.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(255,255,255)\n")
        self.ui.pushButton_4.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(255,255,255)\n")
        self.ui.pushButton_5.setStyleSheet("border-radius: 10px;\n"
        "border: 1px solid black;\n"
        "background-color: rgb(255,255,255)\n") 
             

    def createMenu(self):
        self.ui.actionJugar.triggered.connect(self.play)
        self.ui.actionJugar.setShortcut("Ctrl+W")
        self.ui.actionAyuda_2.triggered.connect(self.ayuda)
        self.ui.actionSalir_2.triggered.connect(self.salir)
        self.ui.actionSalir_2.setShortcut("Ctrl+Q")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    win = Window()
    win.show()
    sys.exit(app.exec())