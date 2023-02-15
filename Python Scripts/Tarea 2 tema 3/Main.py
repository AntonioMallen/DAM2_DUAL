import sys

from PyQt6.QtWidgets import (
    QApplication, QDialog, QMainWindow, QMessageBox,
)
from PyQt6.QtGui import QAction
from random import randint
from Designer2 import Ui_MainWindow

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

        self.ui.actionAyuda_2.triggered.connect(self.ayuda)
        self.ui.actionSalir_2.triggered.connect(self.salir)
        self.ui.actionSalir_2.setShortcut("Ctrl+Q")

        self.darNorte=False
        self.darSur=False
        self.darEste=False
        self.darOeste=False

        self.estado=""
        self.estado=""

    def initializeUI(self):
        self.comenzar=False
        self.ui.TextGrande.setText("Bienvenido a la Mazmorra de Antonio.\nPara jugar tienes que escoger una habitacion y\ndarle a Jugar,si necesitas mas ayuda puedes dirigirte a\nel apartado de menu y 'Ayuda'")
        self.ui.pushButton.setEnabled(False)
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.textosAux.setVisible(False) 
        self.ui.pushButton_7.setVisible(False)  

        self.ui.pushButton.clicked.connect(self.seleccionNorte)
        self.ui.pushButton_5.clicked.connect(self.seleccionSur)
        self.ui.pushButton_4.clicked.connect(self.seleccionEste)
        self.ui.pushButton_2.clicked.connect(self.seleccionOeste)
        self.ui.pushButton_7.clicked.connect(self.salirMedio)
        self.ui.pushButton_6.clicked.connect(self.botonJugar)
        
       



    def seleccionNorte(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Norte")
        
        self.ui.pushButton.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(120,200,120)\n")
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.estado="N"


    def seleccionSur(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Sur")
        self.estado="S"
        self.ui.pushButton_5.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(120,200,120)\n")
        
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton.setEnabled(False)

    def seleccionEste(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Este")
        self.estado="E"
        self.ui.pushButton_4.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(120,200,120)\n")
        
        self.ui.pushButton_2.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.pushButton.setEnabled(False)

    def seleccionOeste(self):
        self.ui.TextGrande.setText("Vas a entrar en la sala Oeste")
        self.estado="O"
        self.ui.pushButton_2.setStyleSheet("border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(120,200,120)\n")
        
        self.ui.pushButton_4.setEnabled(False)
        self.ui.pushButton_3.setEnabled(False)
        self.ui.pushButton_5.setEnabled(False)
        self.ui.pushButton.setEnabled(False)

    def salaNorte(self):
        self.ui.pushButton_6.setText("Si")
        self.ui.pushButton_7.setText("No")
        ene=randint(0,100)
        if(ene>=90):
            self.ui.TextGrande.setText("Ha aparecido un enemigo en tu camino.\nEl enemigo te ha hecho "+str(ene)+ " de daño.\nHas muerto\n¿Quieres seguir?")
            self.estado="muerto"
        else:
            self.ui.TextGrande.setText("Ha aparecido un enemigo en tu camino.\nEl enemigo te ha hecho "+str(ene)+ " de daño.\nHas sobrevivido\n¿Quieres defenderte?")   
            self.estado="vivo"
            
    def salaSur(self):
        dado=randint(0,100)
        if(dado<63):
            self.ui.TextGrande.setText("Vaya, has sacado un "+ str(dado) +",\nHas perdido, la proxima vez tendras mas suerte")
        else:
            self.ui.TextGrande.setText("Vaya, has sacado un "+ str(dado) +", Has ganado!")
            self.ui.pushButton_7.setVisible(False)
            self.ui.pushButton_6.setText("Vale")
            self.estado="ganarS"


    
    def salaEste(self):
        pregunta=["Hay algo que, aunque te pertenezca,\n la gente siempre lo utiliza más que tú. ¿Qué es?",
                "Crezco a pesar de no estar vivo.\nNo tengo pulmones, pero para vivir necesito el aire.\nEl agua, aunque no tenga boca, me mata. ¿Qué soy?",
                "Estando roto es más útil que sin romperse.\n¿Qué es?",
                "Aparato que vibra y gira, te metes en la boca unas 3 veces al día\ny mide unos 15 cm. ¿Qué es?",
                "Las personas siempre duermen menos en un mes del año.\n¿Cuál es este mes?",
                "Estoy en todo pese a estar en nada.\n¿Qué soy?",
                "Te paras cuando está verde\ny continúas cuando está rojo. ¿Qué es?",
                "¿Qué monte era el más alto del mundo\nantes de descubrir el Everest?",
                "La señora y el señor Sánchez tienen 6 hijos.\nCada hijo tiene una hermana.\n¿Cuántas personas hay en la familia Sánchez?",
                "Soy alto siendo joven y corto cuando soy viejo.\nResplandezco con la vida y el viento es mi mayor enemigo.\n¿Qué soy?"]
        respuesta=["nombre",
                "Fuego",
                "Huevo",
                "Cepillo de\ndientes",
                "Febrero",
                "letra D",
                "Sandia",
                "Monte\nEverest",
                "9",
                "Vela"]
        posResp=[]
        numPregunta=randint(0,9)
        self.ui.TextGrande.setText(pregunta[numPregunta])
        if(numPregunta==0):
            posResp.append(respuesta[numPregunta])
            posResp.append(respuesta[numPregunta+1])
            posResp.append(respuesta[numPregunta+2])
        elif(numPregunta==9):
            posResp.append(respuesta[numPregunta])
            posResp.append(respuesta[numPregunta-1])
            posResp.append(respuesta[numPregunta-2])
        else:
            posResp.append(respuesta[numPregunta])
            posResp.append(respuesta[numPregunta+1])
            posResp.append(respuesta[numPregunta-1])
        
        #Ahora colocamos las respuestas
        self.colocar=randint(1,3)
        if(self.colocar==1):
            self.ui.radioButton.setText(posResp[0])
            self.ui.radioButton_2.setText(posResp[1])
            self.ui.radioButton_3.setText(posResp[2])
        elif(self.colocar==2):
            self.ui.radioButton.setText(posResp[2])
            self.ui.radioButton_2.setText(posResp[0])
            self.ui.radioButton_3.setText(posResp[1])
        elif(self.colocar==3):
            self.ui.radioButton.setText(posResp[1])
            self.ui.radioButton_2.setText(posResp[2])
            self.ui.radioButton_3.setText(posResp[0])
        self.ui.textosAux.setVisible(True)
        self.estado="comprobarE"


    def salaOeste(self):
        pregunta=["¿Cuál es el río más largo de España?",
                "¿Cuál es el río más largo de la península ibérica?",
                "¿Cuál es el río más largo del mundo?",
                "¿Cuál es la montaña más alta de España?",
                "¿Cuál es la montaña más alta del mundo?",
                "¿Cuál es el océano más grande?",
                "¿Cuál es el país con más extensión?",
                "¿Cuál es el país más poblado?"]
        respuesta=["Ebro",
                "Tajo",
                "Amazonas",
                "Teide",
                "Everest",
                "Pacifico",
                "Rusia",
                "India"]
        posResp=[]
        numPregunta=randint(0,7)
        self.ui.TextGrande.setText(pregunta[numPregunta])
        if(numPregunta==0):
            posResp.append(respuesta[numPregunta])
            posResp.append(respuesta[numPregunta+1])
            posResp.append(respuesta[numPregunta+2])
        elif(numPregunta==7):
            posResp.append(respuesta[numPregunta])
            posResp.append(respuesta[numPregunta-1])
            posResp.append(respuesta[numPregunta-2])
        else:
            posResp.append(respuesta[numPregunta])
            posResp.append(respuesta[numPregunta+1])
            posResp.append(respuesta[numPregunta-1])
        
        #Ahora colocamos las respuestas
        self.colocar=randint(1,3)
        if(self.colocar==1):
            self.ui.radioButton.setText(posResp[0])
            self.ui.radioButton_2.setText(posResp[1])
            self.ui.radioButton_3.setText(posResp[2])
        elif(self.colocar==2):
            self.ui.radioButton.setText(posResp[2])
            self.ui.radioButton_2.setText(posResp[0])
            self.ui.radioButton_3.setText(posResp[1])
        elif(self.colocar==3):
            self.ui.radioButton.setText(posResp[1])
            self.ui.radioButton_2.setText(posResp[2])
            self.ui.radioButton_3.setText(posResp[0])
        self.ui.textosAux.setVisible(True)
        self.estado="comprobarO"


    def defenderse(self):
        per=randint(0,100)
        if(per>60):
            self.ui.TextGrande.setText("Le has hecho "+str(per)+" de daño\n¡Has vencido la sala norte!")
            self.ui.pushButton_6.setText("Vale")
            self.ui.pushButton_7.setVisible(False)
            self.estado="ganarN"
        else:
            self.ui.TextGrande.setText("Le has hecho "+str(per)+" de daño, no es suficiente\n¿Quieres seguir?")
            self.estado="perderN"
            
    def comprobarEste(self):
        if(self.colocar==1):
            if(self.ui.radioButton.isChecked()):
                self.ui.label_3.setText("Has acertado!")
                self.estado="ganarE"
                self.ui.pushButton_7.setVisible(False)
               
            else:
                self.ui.label_3.setText("Has fallado!")
                self.estado="perderE"
        if(self.colocar==2):
            if(self.ui.radioButton_2.isChecked()):
                self.ui.label_3.setText("Has acertado!")
                self.estado="ganarE"
                self.ui.pushButton_7.setVisible(False)

            else:
                self.ui.label_3.setText("Has fallado!")
                self.estado="perderE"
        if(self.colocar==3):
            if(self.ui.radioButton_3.isChecked()):
                self.ui.label_3.setText("Has acertado!")
                self.estado="ganarE"
                self.ui.pushButton_7.setVisible(False)
            else:
                self.ui.label_3.setText("Has fallado!")
                self.estado="perderE"
        self.ui.pushButton_6.setText("Vale")

    def comprobarOeste(self):
        if(self.colocar==1):
            if(self.ui.radioButton.isChecked()):
                self.ui.label_3.setText("Has acertado!")
                self.estado="ganarO"
                self.ui.pushButton_7.setVisible(False)
               
            else:
                self.ui.label_3.setText("Has fallado!")
                self.estado="perderO"
        if(self.colocar==2):
            if(self.ui.radioButton_2.isChecked()):
                self.ui.label_3.setText("Has acertado!")
                self.estado="ganarO"
                self.ui.pushButton_7.setVisible(False)

            else:
                self.ui.label_3.setText("Has fallado!")
                self.estado="perderO"
        if(self.colocar==3):
            if(self.ui.radioButton_3.isChecked()):
                self.ui.label_3.setText("Has acertado!")
                self.estado="ganarO"
                self.ui.pushButton_7.setVisible(False)
            else:
                self.ui.label_3.setText("Has fallado!")
                self.estado="perderO"
        self.ui.pushButton_6.setText("Vale")




    def salirMedio(self):
        self.ui.pushButton_6.setText("Jugar")
        self.ui.pushButton_7.setText("Salir")
        self.ui.textosAux.setVisible(False)
        if(self.darNorte==True):
            self.ui.pushButton.setEnabled(False)
        else:
            self.ui.pushButton.setEnabled(True)
            self.ui.pushButton.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255);\n"
            "color: black\n")

        if(self.darOeste==True):
            self.ui.pushButton_2.setEnabled(False)
        else:
            self.ui.pushButton_2.setEnabled(True)
            self.ui.pushButton_2.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255);\n"
             "color: black\n")

        if(self.darEste==True):
            self.ui.pushButton_4.setEnabled(False)
        else:
            self.ui.pushButton_4.setEnabled(True)
            self.ui.pushButton_4.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255);\n"
             "color: black\n")

        if(self.darSur==True):
            self.ui.pushButton_5.setEnabled(False)
        else:
            self.ui.pushButton_5.setEnabled(True)
            self.ui.pushButton_5.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,255,255);\n"
             "color: black\n")
        self.estado=""
        self.ui.TextGrande.setText("Bienvenido a la Mazmorra de Antonio, para jugar tienes que escoger una \nhabitacion y darle a Jugar, si necesitas mas ayuda puedes \ndirigirte a el apartado de menu y 'Ayuda'")
   

    def ganarNorte(self):
        self.ui.pushButton.setEnabled(False)
        self.ui.pushButton_6.setVisible(True) 
        self.ui.pushButton_7.setVisible(True)
        self.ui.pushButton.setStyleSheet("border-radius: 10px;\n"
            "border: 1px solid black;\n"
            "background-color: rgb(255,110,110)\n")
        self.salirMedio()
    
    def botonJugar(self):
        if (self.darEste and self.darNorte and self.darOeste and self.darSur):
            self.ui.pushButton_6.setVisible(False)
            self.ui.pushButton_7.setVisible(False)
            self.ui.textosAux.setVisible(False)
            self.ui.TextGrande.setText("Has Ganadoo!!!")
        else:
            if(self.comenzar==False):
                self.play()
                self.comenzar=True
            else:
                if(self.estado=="N"):
                    self.salaNorte()
                elif(self.estado=="S"):
                    self.salaSur()
                elif(self.estado=="E"):
                    self.salaEste()
                elif(self.estado=="O"):
                    self.salaOeste()
                elif(self.estado=="vivo"):
                    self.defenderse()
                elif(self.estado=="muerto"):
                    self.botonJugar()
                elif(self.estado=="ganarN"):
                        self.darNorte=True
                        self.estado=""
                        self.ganarNorte()
                elif(self.estado=="perderN"):
                    self.salaNorte()
                elif(self.estado=="ganarS"):
                    self.darSur=True
                    self.ui.pushButton_5.setEnabled(False)
                    self.ui.pushButton_6.setVisible(True) 
                    self.ui.pushButton_7.setVisible(True)
                    self.ui.pushButton_5.setStyleSheet("border-radius: 10px;\n"
                        "border: 1px solid black;\n"
                        "background-color: rgb(255,110,110)\n")
                    self.salirMedio()
                elif(self.estado=="comprobarE"):
                    self.comprobarEste()
                elif(self.estado=="ganarE"):
                    self.ui.label_3.setText("")
                    self.darEste=True
                    self.ui.pushButton_4.setEnabled(False)
                    self.ui.pushButton_6.setVisible(True) 
                    self.ui.pushButton_7.setVisible(True)
                    self.ui.pushButton_4.setStyleSheet("border-radius: 10px;\n"
                        "border: 1px solid black;\n"
                        "background-color: rgb(255,110,110)\n")
                    self.ui.textosAux.setVisible(False)
                    self.salirMedio()
                elif(self.estado=="perderE"):
                    self.ui.pushButton_6.setText("Jugar")
                    self.ui.label_3.setText("")
                    self.salaEste()
                elif(self.estado=="comprobarO"):
                    self.comprobarOeste()
                elif(self.estado=="ganarO"):
                    self.ui.label_3.setText("")
                    self.darOeste=True
                    self.ui.pushButton_2.setEnabled(False)
                    self.ui.pushButton_6.setVisible(True) 
                    self.ui.pushButton_7.setVisible(True)
                    self.ui.pushButton_2.setStyleSheet("border-radius: 10px;\n"
                        "border: 1px solid black;\n"
                        "background-color: rgb(255,110,110)\n")
                    self.ui.textosAux.setVisible(False)
                    self.salirMedio()
                elif(self.estado=="perderO"):
                    self.ui.pushButton_6.setText("Jugar")
                    self.ui.label_3.setText("")
                    self.salaOeste()
            





       
    def ayuda(self):
        self.popup= QMessageBox()
        self.popup.setWindowTitle("Guia")
        self.popup.setText("""
        Bienvenido a mi mazmorra, esta mazmorra consta de 5 salas, 
        la del medio sera la por defecto, desde ella podras escoger una sala 
        y darle al boton "Jugar" para adentrarte en ella.
        El boton "Salir" servira para poder salir de las salas en cualquier momento 
        y una vez la sala se ha completado cambiara de color y se desactivara.

        Sala Norte: te aparecera un enemigo el cual te ataca
        puede matarte o no, en caso de muerte se reiniciarian
        todas las salas, en caso de sobrevivir puedes contratacar
        si le haces mas de 63 de daño habras ganado.

        Sala Sur: Tiraras un dado y a no ser que saques
        mas de un 63 en el, no ganaras esta sala.

        Sala Oeste: Te aparecera 1 de 7 preguntas de cultura general
        en ellas tendras 3 opciones puestas aleatoriamente,
        tendras que escoger una y darle al boton Jugar,
        el cuadro de debajo te dira si has acertado o no.

        Sala Este: Te aparecera 1 de 10 acertijos,
        tendras que escoger entre sus 3 opciones y darle al boton Jugar
        con el recuadro de debajo sabras si has acertado o no.

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