package year2022.day7

import java.io.File

fun main() {
    val path = "D:\\Downloads\\2022.day.7.input.txt"
    val inputString = File(path).reader().use { it.readText() }
    var splitted = inputString.split("\n")

    splitted = splitted.dropLast(1)

    val diskSpace = 70000000
    val neededForUpdate = 30000000

    var arrayListOfDirectory = arrayListOf<Directory>()
    splitted.forEach {
        if (it.contains("\$ cd ..")) {
            var i = 1

            while (arrayListOfDirectory.get(arrayListOfDirectory.size - i).active == false) i++

            arrayListOfDirectory.get(arrayListOfDirectory.size - i).active = false
        } else if (it.contains("\$ cd") && !it.contains("..")) {
            val parentDirectory = it.substring(5)
            arrayListOfDirectory.add(Directory(parentDirectory, active = true))
        } else if (it.elementAt(1).isDigit()) {
            arrayListOfDirectory.forEach { itDirectory ->
                if (itDirectory.active) {
                    val toInt = it.substringBefore(' ').toInt()
                    itDirectory.size += toInt
                }
            }
        }
    }

    val usedSpace = arrayListOfDirectory.get(0).size
    val unusedSpace = diskSpace - usedSpace
    val neededToRelease = neededForUpdate - unusedSpace
    arrayListOfDirectory.sortBy { it.size }

    var part1Answer = 0
    arrayListOfDirectory.forEach { if (it.size <= 100000) part1Answer += it.size }
    println("Par1: $part1Answer")
    val part2Answer = arrayListOfDirectory.first { it.size >= neededToRelease }.size

    println("Part2: $part2Answer")
}