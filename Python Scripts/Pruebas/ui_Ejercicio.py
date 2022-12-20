# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'Ejercicio.ui'
##
## Created by: Qt User Interface Compiler version 6.4.1
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QMainWindow, QMenuBar, QPushButton,
    QSizePolicy, QSpinBox, QStatusBar, QTextEdit,
    QVBoxLayout, QWidget)

class ui_Ejercicio_main(object):
    def setupUi(self, ui_Ejercicio):
        ui_Ejercicio.resize(495, 530)
        self.centralwidget = QWidget(ui_Ejercicio)
        self.centralwidget.setObjectName(u"centralwidget")
        self.verticalLayoutWidget = QWidget(self.centralwidget)
        self.verticalLayoutWidget.setObjectName(u"verticalLayoutWidget")
        self.verticalLayoutWidget.setGeometry(QRect(50, 40, 71, 91))
        self.verticalLayout = QVBoxLayout(self.verticalLayoutWidget)
        self.verticalLayout.setObjectName(u"verticalLayout")
        self.verticalLayout.setContentsMargins(0, 0, 0, 0)
        self.sb_numero1 = QSpinBox(self.verticalLayoutWidget)
        self.sb_numero1.setObjectName(u"sb_numero1")

        self.verticalLayout.addWidget(self.sb_numero1)

        self.sb_numero2 = QSpinBox(self.verticalLayoutWidget)
        self.sb_numero2.setObjectName(u"sb_numero2")

        self.verticalLayout.addWidget(self.sb_numero2)

        self.sumar = QPushButton(self.centralwidget)
        self.sumar.setObjectName(u"sumar")
        self.sumar.setGeometry(QRect(140, 40, 131, 41))
        self.pushButton_2 = QPushButton(self.centralwidget)
        self.pushButton_2.setObjectName(u"pushButton_2")
        self.pushButton_2.setGeometry(QRect(140, 90, 131, 41))
        self.t_resultado = QTextEdit(self.centralwidget)
        self.t_resultado.setObjectName(u"t_resultado")
        self.t_resultado.setGeometry(QRect(300, 40, 151, 91))
        restar.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(restar)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 495, 22))
        restar.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(restar)
        self.statusbar.setObjectName(u"statusbar")
        restar.setStatusBar(self.statusbar)

        self.retranslateUi(restar)

        QMetaObject.connectSlotsByName(restar)
    # setupUi

    def retranslateUi(self, restar):
        restar.setWindowTitle(QCoreApplication.translate("restar", u"MainWindow", None))
        self.sumar.setText(QCoreApplication.translate("restar", u"Sumar", None))
        self.pushButton_2.setText(QCoreApplication.translate("restar", u"Restar", None))
        self.t_resultado.setHtml(QCoreApplication.translate("restar", u"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><meta charset=\"utf-8\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"hr { height: 1px; border-width: 0; }\n"
"li.unchecked::marker { content: \"\\2610\"; }\n"
"li.checked::marker { content: \"\\2612\"; }\n"
"</style></head><body style=\" font-family:'Segoe UI'; font-size:9pt; font-weight:400; font-style:normal;\">\n"
"<p align=\"center\" style=\"-qt-paragraph-type:empty; margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\"><br /></p></body></html>", None))
    # retranslateUi

