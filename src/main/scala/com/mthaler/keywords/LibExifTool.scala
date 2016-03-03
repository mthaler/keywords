package com.mthaler.keywords

import java.nio.file.{Files, Path}

/**
  * Wrapper for ExifTool (http://www.sno.phy.queensu.ca/~phil/exiftool/)
  *
  * @param path path to ExifTool binary
  */
class LibExifTool(path: Path) {

  require(Files.exists(path))
}
