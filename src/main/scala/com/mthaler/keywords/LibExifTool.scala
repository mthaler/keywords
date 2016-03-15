package com.mthaler.keywords

import java.io.{InputStreamReader, BufferedReader}
import java.nio.file.{Files, Path}

/**
  * Wrapper for ExifTool (http://www.sno.phy.queensu.ca/~phil/exiftool/)
  *
  * @param path path to ExifTool binary
  */
class LibExifTool(path: Path) {

  require(Files.exists(path))

  def getKeywords(p: Path): List[String] = {
    val builder = new ProcessBuilder(path.toString, "-keywords", p.toString)
    val process = builder.start()
    val reader = new BufferedReader(new InputStreamReader(process.getInputStream))
    var line = reader.readLine()
    val sb = new StringBuilder
    while (line != null) {
      sb.append(line)
      line = reader.readLine()
    }
    val s = sb.toString()
    val index = s.lastIndexOf(':')
    if (index > 0) {
      val keywordsString = s.substring(index + 1)
      val keywords = keywordsString.split(',').map(_.trim)
      keywords.toList
    } else {
      throw new Exception("Unknown format: " + s)
    }
  }

  def setKeywords(p: Path, keywords: List[String]): Unit = {
    val builder = new ProcessBuilder(path.toString, "-keywords=" + keywords.mkString(","), p.toString)
    val process = builder.start()
    val exitValue = process.waitFor()
    if (exitValue != 0) {
      throw new Exception("Error: " + exitValue)
    }
  }

  def getDescription(p: Path): String = {
    val builder = new ProcessBuilder(path.toString, "-description", p.toString)
    val process = builder.start()
    val reader = new BufferedReader(new InputStreamReader(process.getInputStream))
    var line = reader.readLine()
    val sb = new StringBuilder
    while (line != null) {
      sb.append(line)
      line = reader.readLine()
    }
    val s = sb.toString()
    val index = s.lastIndexOf(':')
    if (index > 0) {
      s.substring(index + 1).trim
    } else {
      throw new Exception("Unknown format: " + s)
    }
  }

  def setDescription(p: Path, description: String): Unit = {
    val builder = new ProcessBuilder(path.toString, "-description=" + description, p.toString)
    val process = builder.start()
    val exitValue = process.waitFor()
    if (exitValue != 0) {
      throw new Exception("Error: " + exitValue)
    }
  }
}
