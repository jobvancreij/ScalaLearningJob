package aoc2022

import org.scalatest.funsuite.AnyFunSuite

class TestSolutions extends AnyFunSuite:

  test("Day01 [32ms]") {
    assertResult(67830)(actual = Exercise11.answer1)
  }