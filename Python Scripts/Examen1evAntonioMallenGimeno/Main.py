# Import necessary modules
import sys
from PyQt6.QtWidgets import (QApplication, QWidget, QLabel
, QGridLayout,QPushButton,QMessageBox,QMainWindow,QDialogButtonBox,QVBoxLayout,QAbstractButton) 
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QFont,QAction
from random import randint
import time

		
class MainWindow(QMainWindow):

	norte =""
	sur= ""

	def __init__(self):
		super().__init__() 
		self.initializeUI()

	def initializeUI(self):
		"""Set up the application's GUI."""
		
		self.setWindowTitle("La mazmorra de Antonio Mallen Gimeno")

		self.setFixedSize(400,400)

		self.UiComponents()
		self.createActions()
		self.createMenu()
		self.show()

	def UiComponents(self):

		self.CNorte=False
		self.CSur=False
		self.CEste=False
		self.COeste=False
		
		self.norte = QPushButton("Norte",self)
		self.norte.setGeometry(150,50,100,75)
		self.norte.setStyleSheet(
			"border: 1px solid black;"
			"border-radius: 15px;"
		)


		self.oeste = QPushButton("Oeste",self)
		self.oeste.setGeometry(25,150,100,75)
		self.oeste.setStyleSheet(
			"border: 1px solid black;"
			"border-radius: 15px;"
		)


		self.central = QPushButton("Central",self)
		self.central.setGeometry(150,150,100,75)
		self.central.setStyleSheet(
			"border: 1px solid black;"
			"border-radius: 15px;"
		)


		self.este = QPushButton("Este",self)
		self.este.setGeometry(275,150,100,75)
		self.este.setStyleSheet(
			"border: 1px solid black;"
			"border-radius: 15px;"
		)

		# creating a push button
		self.sur = QPushButton("Sur",self)
		self.sur.setGeometry(150,250,100,75)
		self.sur.setStyleSheet(
			"border: 1px solid black;"
			"border-radius: 15px;"
		)

		self.norte.setStyleSheet("background-color: rgb(230,0,0);")
		self.sur.setStyleSheet("background-color: rgb(230,0,0);")
		self.este.setStyleSheet("background-color: rgb(230,0,0);")
		self.oeste.setStyleSheet("background-color: rgb(230,0,0);")
		self.central.setStyleSheet("background-color: rgb(230,0,0);")
		
		self.norte.setEnabled(False)
		self.sur.setEnabled(False)
		self.este.setEnabled(False)
		self.oeste.setEnabled(False)
		self.central.setEnabled(False)




		self.norte.clicked.connect(self.salaNorte)
		self.sur.clicked.connect(self.salaSur)
		self.central.clicked.connect(self.salaCentral)
		self.este.clicked.connect(self.salaEste)
		self.oeste.clicked.connect(self.salaOeste)

	def salaCentral(self):
		msg = QMessageBox()
		msg.setText(
		"La habitacion norte esta: "+ str(self.CNorte)+"\n"
		"La habitacion este esta: "+ str(self.CEste)+"\n"
		"La habitacion sur esta: "+ str(self.CSur)+"\n"
		"La habitacion oeste esta: "+ str(self.COeste))
		x = msg.exec()

	def salaNorte(self):
		"""
		Un enemigo que atacará al jugador. Si saca 90 o más (sobre 100) 
		el jugador habrá muerto y se le ofrecerá volver a empezar. 
		Tras cada ataque del enemigo, el jugador podrá atacar respondiendo 
		Sí a la pregunta: ¿Quieres defenderte? Si saca más de 60 (sobre 100), 
		habrá derrotado al enemigo y podrá ir a otra habitación. 
		Si dice que No quiere defenderse, huirá a la habitación central pero no habrá derrotado al enemigo 
		y no podrá salir de la mazmorra todavía.
		"""
		self.ok = QPushButton("Si",self)
		msg = QMessageBox()
		salir=False
		if(self.CNorte==False):
			msg.setText("Un enemigo ha aparecido en tu camino")
			x = msg.exec()
			while(salir==False):
				ene=randint(0,100)
				msg.setText("El enemigo te ha hecho "+str(ene)+ " de daño")
				x = msg.exec()
				if(ene>=90):
					msg.setText("Has muerto")
					x = msg.exec()
					self.CNorte=False
					self.CSur=False
					self.CEste=False
					self.COeste=False
					msg.setText("¿Quieres seguir?")
					myYesButton = msg.addButton("Si", QMessageBox.ButtonRole.NoRole)
					myNoButton = msg.addButton("No", QMessageBox.ButtonRole.NoRole)
					x = msg.exec()
					msg.removeButton(myYesButton)
					msg.removeButton(myNoButton)
					if(x==0):
						salir=True
					else:
						pass
				else:
					msg.setText("¿Quieres defenderte?")
					myYesButton = msg.addButton("Si", QMessageBox.ButtonRole.NoRole)
					myNoButton = msg.addButton("No", QMessageBox.ButtonRole.NoRole)
					x = msg.exec()
					msg.removeButton(myYesButton)
					msg.removeButton(myNoButton)
					if(x==0):
						per=randint(0,100)
						msg.setText("Le has hecho "+str(per)+" de daño")
						x = msg.exec()
						if(per>60):
							salir=True
							msg.setText("¡Has vencido la sala norte!")
							x = msg.exec()
							self.CNorte=True
					elif(x==1):
						msg.setText("Huyes hacia la sala central")
						x = msg.exec()
						
						salir=True
					else:
						msg.setText("Respuesta no contemplada")
						x = msg.exec()
						salir=True
					msg.removeButton(myYesButton)
					msg.removeButton(myNoButton)
		else:
			msg.setText("Esta prueba ya ha sido superada")
			x = msg.exec()

	def salaSur(self):
		"""
		Un acertijo de entre una lista de 10.
		"""
		if(self.CSur==False):
			msg = QMessageBox()
			pregunta=["Hay algo que, aunque te pertenezca, la gente siempre lo utiliza más que tú. ¿Qué es?",
				"Crezco a pesar de no estar vivo. No tengo pulmones, pero para vivir necesito el aire. El agua, aunque no tenga boca, me mata. ¿Qué soy?",
				"Estando roto es más útil que sin romperse. ¿Qué es?",
				"Aparato que vibra y gira, te metes en la boca unas 3 veces al día y mide unos 15 cm. ¿Qué es?",
				"Las personas siempre duermen menos en un mes del año. ¿Cuál es este mes?",
				"Estoy en todo pese a estar en nada. ¿Qué soy?",
				"Te paras cuando está verde y continúas cuando está rojo. ¿Qué es?",
				"¿Qué monte era el más alto del mundo antes de descubrir el Everest?",
				"La señora y el señor Sánchez tienen 6 hijos. Cada hijo tiene una hermana. ¿Cuántas personas hay en la familia Sánchez?",
				"Soy alto siendo joven y corto cuando soy viejo. Resplandezco con la vida y el viento es mi mayor enemigo. ¿Qué soy?"]
			respuesta=["nombre",
					"fuego",
					"huevo",
					"cepillo de dientes electrico",
					"febrero",
					"letra d",
					"sandia",
					"monte everest",
					"9",
					"vela"]

			msg.setText("En esta prueba deberas acertar un acertijo")
			x=msg.exec()

			while(self.CSur==False):
				pre=randint(0,9)
				msg.setText(str(pregunta[pre]) + ". Escribelo en la consola")
				x=msg.exec()
				resp=input("Dime la respuesta ") #Te lo pregunta por consola
				resp=resp.lower()
				if(resp==respuesta[pre]):
					print("Correcto!")
					self.CSur=True
				else:
					print("No es correcto, ahi va otra")
		else:
			msg.setText("Esta prueba ya ha sido superada")
			x = msg.exec()
		

	def salaEste(self):
		"""
		Un cofre con un tesoro para el que habrá que tirar un dado de 100 y sacar (aleatoriamente más) de 63 para abrir.
		Si no se consigue abrir, habrá que esperar 230 segundos antes de volver a intentarlo
		"""
		if(self.CEste==False):
			msg = QMessageBox()
			cont = 0
			msg.setText("Te has encontrado un cofre magico, para abrirlo necesitas sacar mas de un 63 con este dado")
			x=msg.exec()
			while(self.CEste==False):
				ale=randint(cont,100)
				msg.setText("Has sacado un "+str(ale)+" en el dado")
				x=msg.exec()
				if(ale>63):
					msg.setText("Lo has conseguido!")
					x=msg.exec()
					self.CEste=True
				else:
					msg.setText("Vaya, que mala suerte, vuelve a intentar tras 230 segundos")
					x=msg.exec()
					cont+2 # para ayudar al jugador le quitamos dos numeros cada vez que falle dentro de las posibilidades
					time.sleep(230)
		else:
			msg.setText("Esta prueba ya ha sido superada")
			x = msg.exec()		


	def salaOeste(self):
		"""
		Una pregunta sacada de una lista de preguntas guardada previamente en una
		variable de tipo lista definida al principio del programa. La pregunta se elegirá aleatoriamente.
		Las preguntas (y la palabra que tendrá que estar en la respuesta, 
		aunque la respuesta podrá contener otras palabras) son:
		¿Cuál es el río más largo de España? Ebro
		¿Cuál es el río más largo de la península ibérica? Tajo
		¿Cuál es el río más largo del mundo? Amazonas
		¿Cuál es la montaña más alta de España? Teide
		¿Cuál es la montaña más alta del mundo? Everest
		¿Cuál es el océano más grande? Pacífico
		¿Cuál es el país con más extensión? Rusia
		¿Cuál es el país más poblado? India
		"""
		if(self.COeste==False):
			msg = QMessageBox()
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
			msg.setText("En esta prueba deberas acertar una pregunta generada aleatoriamente")
			x=msg.exec()
			acertado=False
			while(acertado==False):
				pre=randint(0,7)
				msg.setText(str(pregunta[pre])+ " Escribelo en la consola")
				x=msg.exec()
				resp=input("Dime la respuesta ") # Te lo pregunta por consola
				if(resp==respuesta[pre]):
					acertado=True
					msg.setText("Correcto!")
					x=msg.exec()
					return True
				else:
					msg.setText("No es correcto, ahi va otra pregunta")
					x=msg.exec()
		else:
			msg.setText("Esta prueba ya ha sido superada")
			x = msg.exec()	

 
	def salir(self):
		# clearing a single digit
		sys.exit()

	def play(self):
		self.norte.setEnabled(True)
		self.sur.setEnabled(True)
		self.este.setEnabled(True)
		self.oeste.setEnabled(True)
		self.central.setEnabled(True)
		self.norte.setStyleSheet("background-color: rgb(255,255,255);")
		self.sur.setStyleSheet("background-color: rgb(255,255,255);")
		self.este.setStyleSheet("background-color: rgb(255,255,255);")
		self.oeste.setStyleSheet("background-color: rgb(255,255,255);")
		self.central.setStyleSheet("background-color: rgb(255,255,255);")

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