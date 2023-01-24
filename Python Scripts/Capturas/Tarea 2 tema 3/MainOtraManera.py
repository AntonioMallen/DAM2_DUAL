import sys

from PyQt6.QtWidgets import (
    QApplication, QDialog, QMainWindow, QMessageBox,
)
from PyQt6.QtGui import QAction
from random import randint
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
    
    def createMenu(self):
        self.ui.actionJugar.triggered.connect(self.play)
        self.ui.actionJugar.setShortcut("Ctrl+W")
        self.ui.actionAyuda_2.triggered.connect(self.ayuda)
        self.ui.actionSalir_2.triggered.connect(self.salir)
        self.ui.actionSalir_2.setShortcut("Ctrl+Q")

        self.darNorte=False
        self.darSur=False
        self.darEste=False
        self.darOeste=False

        self.salaSeleccionada=""
        self.estado=""

    def initializeUI(self):
        self.ui.TextGrande.setText("Bienvenido al laberinto, para jugar tienes que escoger una \nhabitacion y darle a Jugar, si necesitas mas ayuda puedes \ndirigirte a el apartado de menu y 'Ayuda'")
        self.ui.pushButton.setEnabled(False)
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.textosAux.setVisible(False)
        self.ui.pushButton_6.setVisible(False)  
        self.ui.pushButton_7.setVisible(False)  

        self.ui.pushButton.clicked.connect(self.seleccionNorte)
        self.ui.pushButton_5.clicked.connect(self.seleccionSur)
        self.ui.pushButton_4.clicked.connect(self.seleccionEste)
        self.ui.pushButton_2.clicked.connect(self.seleccionOeste)
        self.ui.pushButton_7.clicked.connect(self.salirMedio)
        self.ui.pushButton_6.clicked.connect(self.botonJugar)
        
       



    def seleccionNorte(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Norte")
        self.salaSeleccionada="N"
        self.ui.pushButton.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(100,255,100)\n")
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.pushButton_6.clicked.connect(self.salaNorte)
        print("Seleccion")


    def seleccionSur(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Sur")
        self.salaSeleccionada="S"
        self.ui.pushButton_5.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(100,255,100)\n")
        
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton.setEnabled(False)
        self.ui.pushButton_6.clicked.connect(self.salaSur)

    def seleccionEste(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Este")
        self.salaSeleccionada="E"
        self.ui.pushButton_4.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(100,255,100)\n")
        
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.pushButton.setEnabled(False)
        self.ui.pushButton_6.clicked.connect(self.salaEste)

    def seleccionOeste(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Oeste")
        self.salaSeleccionada="O"
        self.ui.pushButton_2.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(100,255,100)\n")
        
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.pushButton.setEnabled(False)
        self.ui.pushButton_6.clicked.connect(self.salaOeste)

    def salaNorte(self):
        self.ui.pushButton_6.setText("Si")
        self.ui.pushButton_7.setText("No")

        ene=randint(0,100)
        if(ene>=90):
            self.estado="muerto"
            self.ui.TextGrande.setText("Ha aparecido un enemigo en tu camino.\nEl enemigo te ha hecho "+str(ene)+ " de daño.\nHas muerto\n¿Quieres seguir?")
        else:
            self.estado="vivo"
            
            self.ui.TextGrande.setText("Ha aparecido un enemigo en tu camino.\nEl enemigo te ha hecho "+str(ene)+ " de daño.\nHas sobrevivido\n¿Quieres defenderte?")       
            
    def defenderse(self):
        print("He entrado a Cosa chunga")
        per=randint(0,100)
        if(per>60):
            self.ui.pushButton_7.setVisible(False)
            self.ui.TextGrande.setText("Le has hecho "+str(per)+" de daño\n¡Has vencido la sala norte!")
            self.darNorte=True
            self.ui.pushButton_6.setText("Vale")
            self.ui.pushButton_6.clicked.connect(self.ganarNorte)
        else:
            self.ui.pushButton_6.clicked.connect(self.salaNorte)
            self.ui.pushButton_7.clicked.connect(self.salirMedio)
            self.ui.TextGrande.setText("Le has hecho "+str(per)+" de daño, no es suficiente\n¿Quieres seguir?")
            


    def salaSur(self):
        pass
    
    def salaEste(self):
        pass

    def salaOeste(self):
        pass

    def salirMedio(self):
        self.ControlNorte=1
        self.ui.pushButton_6.setText("Jugar")
        self.ui.pushButton_7.setText("Salir")

        if(self.darNorte==True):
            self.ui.pushButton.setEnabled(False)
        else:
            self.ui.pushButton.setEnabled(True)
            self.ui.pushButton.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255)\n")

        if(self.darOeste==True):
            self.ui.pushButton_2.setEnabled(False)
        else:
            self.ui.pushButton_2.setEnabled(True)
            self.ui.pushButton_2.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255)\n")

        if(self.darEste==True):
            self.ui.pushButton_4.setEnabled(False)
        else:
            self.ui.pushButton_4.setEnabled(True)
            self.ui.pushButton_4.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255)\n")

        if(self.darSur==True):
            self.ui.pushButton_5.setEnabled(False)
        else:
            self.ui.pushButton_5.setEnabled(True)
            self.ui.pushButton_5.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255)\n")
        
        self.ui.TextGrande.setText("Bienvenido al laberinto, para jugar tienes que escoger una \nhabitacion y darle a Jugar, si necesitas mas ayuda puedes \ndirigirte a el apartado de menu y 'Ayuda'")
   
    
    def botonJugar(self):
        if(self.salaSeleccionada=="N"):
            resp=self.salaNorte()
            if(resp=="muerto"):
                self.botonJugar()
            elif(resp=="vivo"):
                self.defenderse()
        elif(self.salaSeleccionada=="S"):
            self.salaSur()
        elif(self.salaSeleccionada=="E"):
            self.salaEste()
        elif(self.salaSeleccionada=="O"):
            self.salaOeste()


       
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



    def salir(self):
        sys.exit()

    def play(self):
        if(self.darEste or self.darNorte or self.darOeste or self.darSur):
            self.popup= QMessageBox()
            self.popup.setText("El juego ya esta comenzado")
            self.popup.exec()
        else:
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

    def jugarPrin(self):
        self.ui.TextGrande.setText("Elige una sala")    

    


if __name__ == "__main__":
    app = QApplication(sys.argv)
    win = Window()
    win.show()
    sys.exit(app.exec())