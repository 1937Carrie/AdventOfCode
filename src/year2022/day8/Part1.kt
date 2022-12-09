package year2022.day8

import year2022.day8.Part1.Companion.checkVisible
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

    var outsideVisible = heightMap.size * 2 + (heightMap[0].size - 2) * 2

    for (row in 1 until heightMap.size - 1) {
        for (column in 1 until heightMap[row].size - 1) {
            outsideVisible += checkVisible(row, column, heightMap)
        }
    }

    println(outsideVisible)
}

class Part1() {
    companion object {
        fun checkVisible(row: Int, column: Int, heightMap: Array<IntArray>): Byte {
            val isVisibleFromTop = isVisibleFromTop(row, column, heightMap)
            val isVisibleFromBottom = isVisibleFromBottom(row, column, heightMap)
            val isVisibleFromLeft = isVisibleFromLeft(row, column, heightMap)
            val isVisibleFromRight = isVisibleFromRight(row, column, heightMap)

            if (isVisibleFromTop || isVisibleFromBottom || isVisibleFromLeft || isVisibleFromRight) {
                return 1
            }

            return 0
        }

        fun isVisibleFromRight(row: Int, column: Int, heightMap: Array<IntArray>): Boolean {
            for (col in heightMap[column].size - 1 downTo column + 1) {
                if (heightMap[row][column] <= heightMap[row][col]) {
                    return false
                }
            }

            return true
        }

        fun isVisibleFromLeft(row: Int, column: Int, heightMap: Array<IntArray>): Boolean {
            for (col in 0 until column) {
                if (heightMap[row][column] <= heightMap[row][col]) {
                    return false
                }
            }

            return true
        }

        fun isVisibleFromBottom(row: Int, column: Int, heightMap: Array<IntArray>): Boolean {
            for (r in heightMap.size - 1 downTo row + 1) {
                if (heightMap[row][column] <= heightMap[r][column]) {
                    return false
                }
            }

            return true
        }

        fun isVisibleFromTop(row: Int, column: Int, heightMap: Array<IntArray>): Boolean {
            for (r in 0 until row) {
                if (heightMap[row][column] <= heightMap[r][column]) {
                    return false
                }
            }

            return true
        }
    }
}

