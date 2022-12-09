package year2022.day8

import year2022.day8.Part2.Companion.calculateScenicScore
import java.io.File

fun main() {
    val path = "D:\\Downloads\\2022.day.8.input.txt"
    val inputString = File(path).reader().use { it.readText() }
    var splitted = inputString.split("\n").dropLast(1)

    var heightMap = Array(splitted.size) { IntArray(splitted[0].length) }

    for (row in splitted.indices) {
        for (column in splitted[row].indices) {
            heightMap[row][column] = splitted[row][column].digitToInt()
        }
    }

    var scenicScore = 0

    for (row in heightMap.indices) {
        for (column in heightMap[row].indices) {
//            a.coerceAtLeast(b) = Math.max(a, b)
            scenicScore = scenicScore.coerceAtLeast(calculateScenicScore(row, column, heightMap))
        }
    }

    println(scenicScore)
}


class Part2() {
    companion object {
        fun calculateScenicScore(row: Int, column: Int, heightMap: Array<IntArray>): Int {
            val calculateUp = calculateUp(row, column, heightMap)
            val calculateDown = calculateDown(row, column, heightMap)
            val calculateLeft = calculateLeft(row, column, heightMap)
            val calculateRight = calculateRight(row, column, heightMap)

            return calculateUp * calculateDown * calculateLeft * calculateRight
        }

        private fun calculateRight(row: Int, column: Int, heightMap: Array<IntArray>): Int {
            var count = 0

            if (column != heightMap[row].size - 1) {

                for (col in column + 1..heightMap[row].size - 1) {
                    if (heightMap[row][column] > heightMap[row][col]) count++
                    else return ++count
                }
            }

            return count
        }

        private fun calculateLeft(row: Int, column: Int, heightMap: Array<IntArray>): Int {
            var count = 0

            if (column != 0) {

                for (col in column - 1 downTo 0) {
                    if (heightMap[row][column] > heightMap[row][col]) count++
                    else return ++count
                }
            }

            return count
        }

        private fun calculateDown(row: Int, column: Int, heightMap: Array<IntArray>): Int {
            var count = 0

            if (row != heightMap.size - 1) {

                for (r in row + 1..heightMap.size - 1) {
                    if (heightMap[row][column] > heightMap[r][column]) count++
                    else return ++count
                }
            }

            return count
        }

        private fun calculateUp(row: Int, column: Int, heightMap: Array<IntArray>): Int {
            var count = 0

            if (row != 0) {

                for (r in row - 1 downTo 0) {
                    if (heightMap[row][column] > heightMap[r][column]) count++
                    else return ++count
                }
            }

            return count
        }
    }
}
