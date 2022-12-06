package year2022.day6

import java.io.File

fun main() {
    //https://www.geeksforgeeks.org/read-from-files-using-inputreader-in-kotlin/
    val path = "D:\\Downloads\\2022.day.6.input.txt"
    val inputString = File(path).reader().use { it.readText() }

    for (i in 0..inputString.length) {
        if (inputString.subSequence(i, i + 4).toString().allUnique()) {
            println(i + 4)
            break
        }
    }
}

//https://stackoverflow.com/a/62233436
fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)