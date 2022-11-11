"""Listing 2-3 to Listing 2-7
Written by Joshua Willman
Featured in "Beginning PyQt - A Hands-on Approach to GUI Programming, 2nd Ed."
"""

# Import necessary modules
import sys
from PyQt6.QtWidgets import QApplication, QWidget, QLabel
from PyQt6.QtGui import QFont, QPixmap

class MainWindow(QWidget):

    def __init__(self):
        super().__init__()
        self.initializeUI()

    def initializeUI(self):
        """Set up the application's GUI."""
        self.setGeometry(50, 50, 270, 450)
        self.setWindowTitle("Tarjeta de Identificacion")

        self.setUpMainWindow()
        self.show()

    def setUpMainWindow(self):
        """Create the labels to be displayed in the window."""
        self.createImageLabels()

        user_label = QLabel(self)
        user_label.setText("Klaus Iohannis")
        user_label.setFont(QFont("Arial", 20))
        user_label.move(40, 140)


        skills_label = QLabel(self)
        skills_label.setText("Presidente")
        skills_label.setFont(QFont('Arial', 17))
        skills_label.move(15, 170)

        languages_label = QLabel(self)
        languages_label.setText("Rumania")
        languages_label.move(15, 195)

        bio_label = QLabel(self)
        bio_label.setText("Bibliografia")
        bio_label.setFont(QFont("Arial", 17))
        bio_label.move(15, 220)

        about_label = QLabel(self) 
        about_label.setText("Soy un político rumano con raíces alemanas ,\
            quien actualmente ostenta el cargo de presidente de Rumanía.")
        about_label.setWordWrap(True)
        about_label.move(15, 245)



        experience_label = QLabel(self)
        experience_label.setText("Experiencia")
        experience_label.setFont(QFont("Arial", 17))
        experience_label.move(15, 295)

        developer_label = QLabel(self)
        developer_label.setText("Presidente de Rumania")
        developer_label.move(15, 320)

        dev_dates_label = QLabel(self)
        dev_dates_label.setText("Dic 2014 - Present")
        dev_dates_label.setFont(QFont("Arial", 10))
        dev_dates_label.move(15, 340)

        driver_label = QLabel(self)
        driver_label.setText("Alcalde de Sibiu")
        driver_label.move(15, 360)

        driver_dates_label = QLabel(self)
        driver_dates_label.setText("Jun 2000 - Dic 2014")
        driver_dates_label.setFont(QFont("Arial", 10))
        driver_dates_label.move(15, 380)

        Lider = QLabel(self)
        Lider.setText("Líder del Partido Nacional Liberal")
        Lider.move(15, 400)

        lider_dates_label = QLabel(self)
        lider_dates_label.setText("Jun 2014 - Dic 2014")
        lider_dates_label.setFont(QFont("Arial", 10))
        lider_dates_label.move(15, 420)

    def createImageLabels(self):
        """Open image files and create image labels."""
        images = ["images/skyblue.png", 
                  "images/profile_image.jpg"]

        for image in images:
            try:
                with open(image):
                    label = QLabel(self)
                    pixmap = QPixmap(image)
                    label.setPixmap(pixmap)
                    if image == "images/profile_image.jpg":
                        label.move(80, 20) 
            except FileNotFoundError as error:
                print(f"Image not found.\nError: {error}")            

# Run program
if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MainWindow()
    sys.exit(app.exec())