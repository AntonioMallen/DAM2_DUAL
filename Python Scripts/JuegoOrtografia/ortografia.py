import sys
import random

from PyQt6.QtCore import Qt
from PyQt6.QtWidgets import (
                QApplication,
                QGridLayout,
                QLineEdit,
                QMainWindow,
                QPushButton,
                QVBoxLayout,
                QHBoxLayout,
                QComboBox,
                QWidget,
                QLabel,
                QDialog,
                QMessageBox
)
from PyQt6.QtGui import QAction


                                
class Ortografia(QMainWindow):
    """PyCalc's main window (GUI or view)."""
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ortografía de Antonio")
        self.palabrastotales=10
        self.numeroPred = self.palabrastotales-1 
        self.numDificultad=5
        self.acertado = 0
        self.fallado = 0
        
        
        self.popup= QMessageBox()
        self.popup.setWindowTitle("Guia")
        self.popup.setText("""Bienvenido al juego de la ortografia,para jugar 
puedes seleccionar en la parte del menu arriba, la cantidad
de palabras que iran saliendo a lo largo del juego.
Con la dificultad ajustaras el numero de palabras que tienes que acertar para aprobar
(en caso de tener mas palabras se duplicara la dificultad acorde ,
un ejemplo seria que de 10 palabras para aprobar pasara 
a ser 20 palabras para aprobar y asi con el resto), tras esto darle a jugar

Te saldra una palabra en la pantalla y tendras que elegir si esta bien o mal,
al final del programa te mostrara si has aprobado o no, aciertos y fallos y una nota.

Mucha suerte
    """)
        self.popup.exec()
        self.jugando=False
        self.mal = ["aférrimo", "adversión", "beneficiencia", "cojer", "computerizar", "consanguineidad", "consciencia", "contricción", "convalescencia", "costipado","desición","disglosia","disgresión","dixlesia","escéntrico","espectativa","esplanada","exalar","exausto","excéptico"]
        self.bien = ["acérrimo", "aversión", "beneficencia", "coger", "computarizar", "consanguinidad", "conciencia", "contrición", "convalecencia", "constipado","decisión","diglosia","digresión","dislexia","excéntrico","expectativa","explanada","exhalar","exhausto","escéptico"]
        self.bienb = True 
        
        self.setFixedSize(400, 400)
        self.generalLayout = QVBoxLayout()
        self.generalLayout.setContentsMargins(25,25,25,25)
        centralWidget = QWidget(self)
        centralWidget.setLayout(self.generalLayout)
        centralWidget.setStyleSheet("background: #32E5DA;")
        self.setCentralWidget(centralWidget)
        self.pregunta = QLabel("¿Está esta palabra bien escrita?")
        self.palabra = QLabel(self._palabra())
        self.palabra.setStyleSheet("QLabel"
                                        "{"
                                        "border : 1px solid black;"
                                        "background : yellow;"
                                        "color : black;"
                                        "}")
        self.palabra.setFixedHeight(50)
        self.palabra.setVisible(False)
        self.pregunta.setFixedHeight(50)
        
        self.generalLayout.addWidget(self.pregunta)
        self.generalLayout.addWidget(self.palabra)
        self.layh = QHBoxLayout()
        self.createButtons()
        
        self.generalLayout.addLayout(self.layh)
        self.laygrid= QGridLayout()
        self.createGrid()
        
        self.generalLayout.addLayout(self.laygrid)
        self.botonBien.clicked.connect(self.pulsaBien)
        self.botonMal.clicked.connect(self.pulsaMal)
        self.createActions()
        self.createMenu()
        self.diez.setChecked(True)
        self.dif_cinco.setChecked(True)
        
        
    
    
    def _palabra(self):
        numero = random.randint(1, 2)
        if self.numeroPred  != 0:
            numero2 = random.randint(0, self.numeroPred)
        else:
            numero2 = 0 # Nos aseguramos que en el 0 no invoque un numero aleatorio


        if numero == 1:
            self.bienb = False
            palabra = self.mal[numero2]
            self.mal.pop(numero2)
            self.bien.pop(numero2) 
            self.numeroPred -= 1
            return palabra
        else:
            self.bienb = True
            palabra = self.bien[numero2]
            self.mal.pop(numero2)
            self.bien.pop(numero2)
            self.numeroPred -= 1
            return palabra
                    
    
    def pulsaBien(self):
        if(self.bienb == True):
            self.acertado += 1
            self.aciertos.setText(str(self.acertado))
            self.division.setText(str(self.acertado) + "/" +str(self.palabrastotales))
        else:
            self.fallado += 1
            self.fallos.setText(str(self.fallado))
        if(self.numeroPred >= 0):
            pal= self._palabra()
            self.palabra.setText(pal)
        else:
            self.botonBien.blockSignals(True)
            self.botonMal.blockSignals(True)
            self.nota.setText(str(self.acertado))

        if(self.acertado>=(self.acertado+self.fallado)*(self.numDificultad/10)):
            self.estado.setText("APROBADO")
        else:
            self.estado.setText("SUSPENDIDO")
    def pulsaMal(self):
        if(self.bienb != True):
            self.acertado += 1
            self.aciertos.setText(str(self.acertado))
            self.division.setText(str(self.acertado) + "/"+str(self.palabrastotales))
        else:
                self.fallado += 1
                self.fallos.setText(str(self.fallado))
        if(self.numeroPred >= 0):
                pal= self._palabra()
                self.palabra.setText(pal)
        else:
            self.botonBien.blockSignals(True)
            self.botonMal.blockSignals(True)
            self.nota.setText(str(self.acertado))
        
        if(self.acertado>=((self.acertado+self.fallado)*(self.numDificultad/10))):
                self.estado.setText("APROBADO")
        else:
                self.estado.setText("SUSPENDIDO")
    def createButtons(self):
        self.botonBien = QPushButton("Bien")
        self.botonBien.setEnabled(False)
        self.botonBien.setStyleSheet("QPushButton"
                                        "{"
                                        "border : 1px solid black;"
                                        "background : grey;"
                                        "color : black;"
                                        "border-radius: 15px;"
                                        "}")
        self.botonBien.setFixedHeight(50)
        self.botonMal = QPushButton("Mal")
        self.botonMal.setEnabled(False)
        self.botonMal.setStyleSheet("QPushButton"
                                        "{"
                                        "border : 1px solid black;"
                                        "background : grey;"
                                        "color : black;"
                                        "border-radius: 15px;"
                                        "}")
        self.botonMal.setFixedHeight(50)
        self.layh.addWidget(self.botonBien)
        self.layh.addWidget(self.botonMal)
    def createGrid(self):
        self.a = QLabel("Aciertos")
        self.a.setFixedHeight(50)
        self.b = QLabel("Fallos")
        self.b.setFixedHeight(50)
        self.c = QLabel("")
        self.c.setFixedHeight(50)
        self.d = QLabel("Nota")
        self.d.setFixedHeight(50)
        self.laygrid.addWidget(self.a, 0, 0)
        self.laygrid.addWidget(self.b, 0, 1)
        self.laygrid.addWidget(self.c, 0, 2)
        self.laygrid.addWidget(self.d, 0, 3)
        self.aciertos = QLabel("")
        self.aciertos.setFixedHeight(50)
        self.aciertos.setStyleSheet("QLabel"
                                        "{"
                                        "border : 1px solid black;"
                                        "background : yellow;"
                                        "color : black;"
                                        "}")
        self.fallos = QLabel("")
        self.fallos.setFixedHeight(50)
        self.fallos.setStyleSheet("QLabel"
                                    "{"
                                    "border : 1px solid black;"
                                    "background : yellow;"
                                    "color : black;"
                                    "}")
        self.nota = QLabel("")
        self.nota.setFixedHeight(50)
        self.nota.setStyleSheet("QLabel"
                                    "{"
                                    "border : 1px solid black;"
                                    "background : yellow;"
                                    "color : black;"
                                    "}")
        self.laygrid.addWidget(self.aciertos, 1, 0)
        self.laygrid.addWidget(self.fallos, 1, 1)
        self.laygrid.addWidget(QLabel(""), 1, 2)
        self.laygrid.addWidget(self.nota, 1, 3)
        self.division = QLabel(str(self.acertado) + "/"+str(self.palabrastotales))
        self.division.setStyleSheet("QLabel"
                                        "{"
                                        "border : 1px solid black;"
                                        "background : #0B7ABD;"
                                        "color : white;"
                                        "}")
        self.division.setFixedHeight(50)
        self.estado = QLabel("") 
        self.estado.setStyleSheet("QLabel"
                                    "{"
                                    "border : 1px solid black;"
                                    "background : #0B7ABD;"
                                    "color : white;"
                                    "}")
        self.estado.setFixedHeight(50)
        self.laygrid.addWidget(self.division, 2, 0)
        self.laygrid.addWidget(self.estado, 2, 1, 1, 3)
    def salir(self):
                    # clearing a single digit
                    sys.exit()
                    
    def play(self):
        self.botonBien.setEnabled(True)
        self.botonBien.setStyleSheet("QPushButton"
                                        "{"
                                        "border : 1px solid black;"
                                        "background : green;"
                                        "color : white;"
                                        "border-radius: 15px;"
                                        "}")
        self.botonMal.setEnabled(True)
        self.botonMal.setStyleSheet("QPushButton"
                                        "{"
                                        "border : 1px solid black;"
                                        "background : red;"
                                        "color : white;"
                                        "border-radius: 15px;"
                                        "}")
        self.jugando=True
        self.palabra.setVisible(True)

    def numDiez(self):
            if(self.jugando==False):
                self.palabrastotales=10
                self.numeroPred = self.palabrastotales-2 
                self.division.setText(str(self.acertado) + "/" + str(self.palabrastotales))
                self.diez.setChecked(True)
                self.veinte.setChecked(False)
            else:
                self.popup.setText("No puedes cambiar los ajustes una vez iniciado el juego") 
                self.popup.exec()
    def numVeinte(self):
        if(self.jugando==False):
            self.palabrastotales=20
            self.numeroPred = self.palabrastotales-2 
            self.division.setText(str(self.acertado) + "/" + str(self.palabrastotales))
            self.veinte.setChecked(True)
            self.diez.setChecked(False)
        else:
            self.popup.setText("No puedes cambiar los ajustes una vez iniciado el juego") 
            self.popup.exec()

    def dificultadCinco(self):
        if(self.jugando==False):
            self.dif_cinco.setChecked(True)
            self.dif_ocho.setChecked(False)
            self.dif_diez.setChecked(False)
            if(self.palabrastotales==10):
                    self.numDificultad=5
            else:
                    self.numDificultad=10
        else:
            self.popup.setText("No puedes cambiar los ajustes una vez iniciado el juego") 
            self.popup.exec()

    def dificultadOcho(self):
        if(self.jugando==False):
            self.dif_cinco.setChecked(False)
            self.dif_ocho.setChecked(True)
            self.dif_diez.setChecked(False)
            if(self.palabrastotales==10):
                    self.numDificultad=8
            else:
                    self.numDificultad=16
        else:
            self.popup.setText("No puedes cambiar los ajustes una vez iniciado el juego") 
            self.popup.exec()

    def dificultadDiez(self):
        if(self.jugando==False):
            self.dif_cinco.setChecked(False)
            self.dif_ocho.setChecked(False)
            self.dif_diez.setChecked(True)
            if(self.palabrastotales==10):
                    self.numDificultad=10
            else:
                    self.numDificultad=20
        else:
            self.popup.setText("No puedes cambiar los ajustes una vez iniciado el juego") 
            self.popup.exec()

    def createActions(self):
        """Create the application's menu actions."""
        # Create actions for File menu
        self.jugar = QAction("Jugar")
        self.jugar.setShortcut("Ctrl+W")
        self.jugar.triggered.connect(self.play)
        self.sal = QAction("Salir")
        self.sal.setShortcut("Ctrl+Q")
        self.sal.triggered.connect(self.salir)
        self.diez =QAction("10")
        self.diez.triggered.connect(self.numDiez)     
        self.diez.setCheckable(True)   
        self.veinte =QAction("20")
        self.veinte.triggered.connect(self.numVeinte)
        self.veinte.setCheckable(True) 
        self.dif_cinco =QAction("5")
        self.dif_cinco.triggered.connect(self.dificultadCinco)
        self.dif_cinco.setCheckable(True) 
        self.dif_ocho =QAction("8")
        self.dif_ocho.triggered.connect(self.dificultadOcho)
        self.dif_ocho.setCheckable(True) 
        self.dif_diez =QAction("10")
        self.dif_diez.triggered.connect(self.dificultadDiez)
        self.dif_diez.setCheckable(True) 
                    
    def createMenu(self):
        """Create the application's menu bar."""
        self.menuBar().setNativeMenuBar(False)
        # Create file menu and add actions 
        file_menu = self.menuBar().addMenu("Menu")
        file_menu.addAction(self.jugar)
        file_menu.addAction(self.sal)
        file_nums = self.menuBar().addMenu("Núm palabras")
        file_nums.addAction(self.diez)
        file_nums.addAction(self.veinte)
        file_diff = self.menuBar().addMenu("Dificultad")
        file_diff.addAction(self.dif_cinco)
        file_diff.addAction(self.dif_ocho)
        file_diff.addAction(self.dif_diez)
                                                
                                                

                                

def main():
    """PyCalc's main function."""
    pycalcApp = QApplication([])
    pycalcWindow = Ortografia()
    pycalcWindow.show()
    sys.exit(pycalcApp.exec())


if __name__ == "__main__":
    main()
