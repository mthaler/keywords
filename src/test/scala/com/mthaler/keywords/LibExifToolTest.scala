package com.mthaler.keywords

import java.nio.file.{Paths, Files}
import org.scalatest.FunSuite

class LibExifToolTest extends FunSuite {

  test("getKeywords") {
    val tempDir = Files.createTempDirectory("keywords")
    try {
      val f = tempDir.resolve("test.jpg")
      val in = getClass.getResourceAsStream("mont_saint_michel-1437-2.jpg")
      Files.copy(in, f)
      assert(Files.exists(f))
      assertResult(List("ancient", "architecture", "aubert", "beach", "brittany", "building", "chapel", "church", "cliff", "coast",
        "coastline", "europe", "france", "french", "historic", "landmark", "medieval", "michel", "mont", "mount", "normandy", "old",
        "religion", "rocks", "saint", "sanctuary", "sand", "sea", "shore", "sky", "st", "tourism", "tourist", "touristic", "travel")) {
        new LibExifTool(Paths.get("/usr/bin/exiftool")).getKeywords(f)
      }

    } finally {
      Files.walkFileTree(tempDir, new DeleteDirVisitor)
    }
  }
}
