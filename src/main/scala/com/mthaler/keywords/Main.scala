package com.mthaler.keywords

import javax.swing.JFrame

object Main extends App {

  private val mainWindow = new MainWindow
  mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  mainWindow.pack()
  mainWindow.setVisible(true)
}