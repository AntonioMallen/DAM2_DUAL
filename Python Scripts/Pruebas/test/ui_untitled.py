# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'untitled.ui'
##
## Created by: Qt User Interface Compiler version 6.4.1
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtAxContainer import QAxWidget
from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QAction, QBrush, QColor, QConicalGradient,
    QCursor, QFont, QFontDatabase, QGradient,
    QIcon, QImage, QKeySequence, QLinearGradient,
    QPainter, QPalette, QPixmap, QRadialGradient,
    QTransform)
from PySide6.QtWidgets import (QApplication, QDial, QMainWindow, QMenu,
    QMenuBar, QPushButton, QSizePolicy, QStatusBar,
    QTextBrowser, QVBoxLayout, QWidget)

class Ui_MiAplicacion(object):
    def setupUi(self, MiAplicacion):
        if not MiAplicacion.objectName():
            MiAplicacion.setObjectName(u"MiAplicacion")
        MiAplicacion.resize(621, 753)
        self.centralwidget = QWidget(MiAplicacion)
        self.centralwidget.setObjectName(u"centralwidget")
        self.textBrowser = QTextBrowser(self.centralwidget)
        self.textBrowser.setObjectName(u"textBrowser")
        self.textBrowser.setGeometry(QRect(30, 220, 231, 71))
        self.verticalLayoutWidget = QWidget(self.centralwidget)
        self.verticalLayoutWidget.setObjectName(u"verticalLayoutWidget")
        self.verticalLayoutWidget.setGeometry(QRect(280, 30, 181, 326))
        self.verticalLayout = QVBoxLayout(self.verticalLayoutWidget)
        self.verticalLayout.setObjectName(u"verticalLayout")
        self.verticalLayout.setContentsMargins(0, 0, 0, 0)
        self.pushButton = QPushButton(self.verticalLayoutWidget)
        self.pushButton.setObjectName(u"pushButton")

        self.verticalLayout.addWidget(self.pushButton)

        self.pushButton_3 = QPushButton(self.verticalLayoutWidget)
        self.pushButton_3.setObjectName(u"pushButton_3")

        self.verticalLayout.addWidget(self.pushButton_3)

        self.pushButton_2 = QPushButton(self.verticalLayoutWidget)
        self.pushButton_2.setObjectName(u"pushButton_2")

        self.verticalLayout.addWidget(self.pushButton_2)

        self.pushButton_4 = QPushButton(self.verticalLayoutWidget)
        self.pushButton_4.setObjectName(u"pushButton_4")

        self.verticalLayout.addWidget(self.pushButton_4)

        self.pushButton_6 = QPushButton(self.verticalLayoutWidget)
        self.pushButton_6.setObjectName(u"pushButton_6")

        self.verticalLayout.addWidget(self.pushButton_6)

        self.pushButton_8 = QPushButton(self.verticalLayoutWidget)
        self.pushButton_8.setObjectName(u"pushButton_8")

        self.verticalLayout.addWidget(self.pushButton_8)

        self.dial = QDial(self.centralwidget)
        self.dial.setObjectName(u"dial")
        self.dial.setGeometry(QRect(80, 360, 151, 141))
        self.dial.setLayoutDirection(Qt.LeftToRight)
        self.axWidget = QAxWidget(self.centralwidget)
        self.axWidget.setObjectName(u"axWidget")
        self.axWidget.setProperty("geometry", QRect(50, 130, 80, 70))
        MiAplicacion.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(MiAplicacion)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 621, 22))
        self.menuMenu = QMenu(self.menubar)
        self.menuMenu.setObjectName(u"menuMenu")
        MiAplicacion.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(MiAplicacion)
        self.statusbar.setObjectName(u"statusbar")
        MiAplicacion.setStatusBar(self.statusbar)
        QWidget.setTabOrder(self.pushButton, self.pushButton_3)
        QWidget.setTabOrder(self.pushButton_3, self.pushButton_2)
        QWidget.setTabOrder(self.pushButton_2, self.pushButton_4)
        QWidget.setTabOrder(self.pushButton_4, self.pushButton_6)
        QWidget.setTabOrder(self.pushButton_6, self.pushButton_8)
        QWidget.setTabOrder(self.pushButton_8, self.textBrowser)
        QWidget.setTabOrder(self.textBrowser, self.dial)

        self.menubar.addAction(self.menuMenu.menuAction())

        self.retranslateUi(MiAplicacion)
        self.pushButton.clicked.connect(self.textBrowser.clear)

        QMetaObject.connectSlotsByName(MiAplicacion)
    # setupUi

    def retranslateUi(self, MiAplicacion):
        MiAplicacion.setWindowTitle(QCoreApplication.translate("MiAplicacion", u"AntonioMallen", None))
        self.pushButton.setText(QCoreApplication.translate("MiAplicacion", u"PushButton", None))
        self.pushButton_3.setText(QCoreApplication.translate("MiAplicacion", u"PushButton", None))
        self.pushButton_2.setText(QCoreApplication.translate("MiAplicacion", u"PushButton", None))
        self.pushButton_4.setText(QCoreApplication.translate("MiAplicacion", u"PushButton", None))
        self.pushButton_6.setText(QCoreApplication.translate("MiAplicacion", u"PushButton", None))
#if QT_CONFIG(tooltip)
        self.pushButton_8.setToolTip(QCoreApplication.translate("MiAplicacion", u"<html><head/><body><p>Esto es un boton</p></body></html>", None))
#endif // QT_CONFIG(tooltip)
        self.pushButton_8.setText(QCoreApplication.translate("MiAplicacion", u"PushButton", None))
        self.menuMenu.setTitle(QCoreApplication.translate("MiAplicacion", u"Menu", None))
    # retranslateUi

