# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'MazmorraDesigner.ui'
##
## Created by: Qt User Interface Compiler version 6.4.1
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QAction, QBrush, QColor, QConicalGradient,
    QCursor, QFont, QFontDatabase, QGradient,
    QIcon, QImage, QKeySequence, QLinearGradient,
    QPainter, QPalette, QPixmap, QRadialGradient,
    QTransform)
from PySide6.QtWidgets import (QApplication, QFrame, QLabel, QMainWindow,
    QMenu, QMenuBar, QPushButton, QRadioButton,
    QSizePolicy, QStatusBar, QWidget)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(751, 448)
        MainWindow.setStyleSheet(u"")
        self.actionAyuda = QAction(MainWindow)
        self.actionAyuda.setObjectName(u"actionAyuda")
        self.actionSalir = QAction(MainWindow)
        self.actionSalir.setObjectName(u"actionSalir")
        self.actionJugar = QAction(MainWindow)
        self.actionJugar.setObjectName(u"actionJugar")
        self.actionAyuda_2 = QAction(MainWindow)
        self.actionAyuda_2.setObjectName(u"actionAyuda_2")
        self.actionSalir_2 = QAction(MainWindow)
        self.actionSalir_2.setObjectName(u"actionSalir_2")
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.centralwidget.setStyleSheet(u"background-color:qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(249,251,231,255), stop:1 rgba(230,238,156, 255))")
        self.botones = QFrame(self.centralwidget)
        self.botones.setObjectName(u"botones")
        self.botones.setGeometry(QRect(10, 10, 301, 281))
        font = QFont()
        font.setFamilies([u"Gill Sans MT"])
        font.setPointSize(11)
        self.botones.setFont(font)
        self.botones.setStyleSheet(u"background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:1, stop:0 rgba(255, 255, 255, 0))")
        self.botones.setFrameShape(QFrame.StyledPanel)
        self.botones.setFrameShadow(QFrame.Raised)
        self.pushButton = QPushButton(self.botones)
        self.pushButton.setObjectName(u"pushButton")
        self.pushButton.setGeometry(QRect(110, 10, 71, 71))
        font1 = QFont()
        font1.setFamilies([u"Georgia"])
        font1.setPointSize(16)
        self.pushButton.setFont(font1)
        self.pushButton.setStyleSheet(u"border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(229, 229, 229)\n"
"")
        self.pushButton_2 = QPushButton(self.botones)
        self.pushButton_2.setObjectName(u"pushButton_2")
        self.pushButton_2.setGeometry(QRect(20, 100, 71, 71))
        self.pushButton_2.setFont(font1)
        self.pushButton_2.setStyleSheet(u"border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(229, 229, 229)\n"
"")
        self.pushButton_3 = QPushButton(self.botones)
        self.pushButton_3.setObjectName(u"pushButton_3")
        self.pushButton_3.setGeometry(QRect(110, 100, 71, 71))
        self.pushButton_3.setFont(font1)
        self.pushButton_3.setStyleSheet(u"border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(229, 229, 229)\n"
"")
        self.pushButton_4 = QPushButton(self.botones)
        self.pushButton_4.setObjectName(u"pushButton_4")
        self.pushButton_4.setGeometry(QRect(200, 100, 71, 71))
        self.pushButton_4.setFont(font1)
        self.pushButton_4.setStyleSheet(u"border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(229, 229, 229)\n"
"")
        self.pushButton_5 = QPushButton(self.botones)
        self.pushButton_5.setObjectName(u"pushButton_5")
        self.pushButton_5.setGeometry(QRect(110, 190, 71, 71))
        self.pushButton_5.setFont(font1)
        self.pushButton_5.setStyleSheet(u"border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(229, 229, 229)\n"
"")
        self.textos = QFrame(self.centralwidget)
        self.textos.setObjectName(u"textos")
        self.textos.setGeometry(QRect(340, 10, 401, 381))
        self.textos.setStyleSheet(u"background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:1, stop:0 rgba(255, 255, 255, 0))")
        self.textos.setFrameShape(QFrame.StyledPanel)
        self.textos.setFrameShadow(QFrame.Raised)
        self.TextGrande = QLabel(self.textos)
        self.TextGrande.setObjectName(u"TextGrande")
        self.TextGrande.setGeometry(QRect(20, 10, 371, 191))
        sizePolicy = QSizePolicy(QSizePolicy.Preferred, QSizePolicy.MinimumExpanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.TextGrande.sizePolicy().hasHeightForWidth())
        self.TextGrande.setSizePolicy(sizePolicy)
        self.TextGrande.setFont(font1)
        self.TextGrande.setStyleSheet(u"background-color: rgb(229, 229, 229);\n"
"border: 1px solid black;")
        self.textosAux = QFrame(self.textos)
        self.textosAux.setObjectName(u"textosAux")
        self.textosAux.setGeometry(QRect(10, 150, 381, 231))
        self.textosAux.setStyleSheet(u"background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:1, stop:0 rgba(255, 255, 255, 0))")
        self.textosAux.setFrameShape(QFrame.StyledPanel)
        self.textosAux.setFrameShadow(QFrame.Raised)
        self.label_3 = QLabel(self.textosAux)
        self.label_3.setObjectName(u"label_3")
        self.label_3.setGeometry(QRect(10, 140, 371, 81))
        self.label_3.setFont(font1)
        self.label_3.setStyleSheet(u"background-color: rgb(229, 229, 229);\n"
"border: 1px solid black;")
        self.frame = QFrame(self.textosAux)
        self.frame.setObjectName(u"frame")
        self.frame.setGeometry(QRect(10, 50, 361, 81))
        self.frame.setFrameShape(QFrame.StyledPanel)
        self.frame.setFrameShadow(QFrame.Raised)
        self.radioButton = QRadioButton(self.frame)
        self.radioButton.setObjectName(u"radioButton")
        self.radioButton.setGeometry(QRect(20, 10, 91, 61))
        self.radioButton.setFont(font1)
        self.radioButton_2 = QRadioButton(self.frame)
        self.radioButton_2.setObjectName(u"radioButton_2")
        self.radioButton_2.setGeometry(QRect(130, 10, 91, 61))
        self.radioButton_2.setFont(font1)
        self.radioButton_3 = QRadioButton(self.frame)
        self.radioButton_3.setObjectName(u"radioButton_3")
        self.radioButton_3.setGeometry(QRect(240, 10, 91, 61))
        self.radioButton_3.setFont(font1)
        self.pushButton_6 = QPushButton(self.centralwidget)
        self.pushButton_6.setObjectName(u"pushButton_6")
        self.pushButton_6.setGeometry(QRect(20, 310, 101, 41))
        self.pushButton_6.setFont(font1)
        self.pushButton_6.setStyleSheet(u"border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(229, 229, 229)\n"
"")
        self.pushButton_7 = QPushButton(self.centralwidget)
        self.pushButton_7.setObjectName(u"pushButton_7")
        self.pushButton_7.setGeometry(QRect(190, 310, 101, 41))
        self.pushButton_7.setFont(font1)
        self.pushButton_7.setStyleSheet(u"border-radius: 10px;\n"
"border: 1px solid black;\n"
"background-color: rgb(229, 229, 229)\n"
"")
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(MainWindow)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 751, 22))
        self.menuMenu = QMenu(self.menubar)
        self.menuMenu.setObjectName(u"menuMenu")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.menubar.addAction(self.menuMenu.menuAction())
        self.menuMenu.addAction(self.actionAyuda_2)
        self.menuMenu.addAction(self.actionSalir_2)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"MainWindow", None))
#if QT_CONFIG(accessibility)
        MainWindow.setAccessibleName(QCoreApplication.translate("MainWindow", u"La mazmorra de Antonio Mallen", None))
#endif // QT_CONFIG(accessibility)
        self.actionAyuda.setText(QCoreApplication.translate("MainWindow", u"Ayuda", None))
        self.actionSalir.setText(QCoreApplication.translate("MainWindow", u"Salir", None))
        self.actionJugar.setText(QCoreApplication.translate("MainWindow", u"Jugar", None))
        self.actionAyuda_2.setText(QCoreApplication.translate("MainWindow", u"Ayuda", None))
        self.actionSalir_2.setText(QCoreApplication.translate("MainWindow", u"Salir", None))
        self.pushButton.setText(QCoreApplication.translate("MainWindow", u"Norte", None))
        self.pushButton_2.setText(QCoreApplication.translate("MainWindow", u"Oeste", None))
        self.pushButton_3.setText(QCoreApplication.translate("MainWindow", u"Inicio", None))
        self.pushButton_4.setText(QCoreApplication.translate("MainWindow", u"Este", None))
        self.pushButton_5.setText(QCoreApplication.translate("MainWindow", u"Sur", None))
        self.TextGrande.setText(QCoreApplication.translate("MainWindow", u"Estas en el inicio", None))
        self.label_3.setText("")
        self.radioButton.setText(QCoreApplication.translate("MainWindow", u"resp1", None))
        self.radioButton_2.setText(QCoreApplication.translate("MainWindow", u"resp2", None))
        self.radioButton_3.setText(QCoreApplication.translate("MainWindow", u"resp3", None))
        self.pushButton_6.setText(QCoreApplication.translate("MainWindow", u"Jugar", None))
        self.pushButton_7.setText(QCoreApplication.translate("MainWindow", u"Salir", None))
        self.menuMenu.setTitle(QCoreApplication.translate("MainWindow", u"Menu", None))
    # retranslateUi

