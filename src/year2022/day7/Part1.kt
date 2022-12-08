package year2022.day7

import java.io.File

fun main() {
    val path = "D:\\Downloads\\2022.day.7.input.txt"
    val inputString = File(path).reader().use { it.readText() }
    var splitted = inputString.split("\n")

    splitted = splitted.dropLast(1)

    var arrayListOfDirectory = arrayListOf<Directory>()
    var parentDirectory: String

    splitted.forEach {
        if (it.contains("cd ..")) {
            var i = 1

            while (arrayListOfDirectory.get(arrayListOfDirectory.size - i).active == false) {
                i++
            }

            arrayListOfDirectory.get(arrayListOfDirectory.size - i).active = false
        } else if (it.contains("cd") && !it.contains("..")) {
            parentDirectory = it.substring(5)
            arrayListOfDirectory.add(Directory(parentDirectory, active = true))
        } else if (it.elementAt(1).isDigit()) {
            arrayListOfDirectory.forEach { itDirectory ->
                if (itDirectory.active) {
                    itDirectory.size += it.substringBefore(' ').toInt()
                }
            }
        }
    }

    var sum = 0

    arrayListOfDirectory.forEach { if (it.size <= 100000) sum += it.size }

    println(sum)
}

class Directory constructor(val name: String, var size: Int = 0, var active: Boolean = false) {
    override fun toString(): String {
        return "Directory(name='$name', size=$size, active=$active)"
    }
}
