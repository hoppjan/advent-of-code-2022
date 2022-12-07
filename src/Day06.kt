fun main() {
    val testLines = readInput("Day06_test")

    val testResult1 = part1(testLines)
    println("test part 1: $testResult1")
    check(testResult1 == 7)

    val testResult2 = part2(testLines)
    println("test part 2: $testResult2")
    check(testResult2 == 19)


    val lines = readInput("Day06")

    println("part 1: ${part1(lines)}")
    println("part 2: ${part2(lines)}")
}

private fun part1(input: List<String>) = input.first().let { stream ->
    for (i in stream.indices.drop(3)) {
        if ("${stream[i]}${stream[i + 1] }${stream[i + 2]}${stream[i + 3]}".hasNoRepeats())
            return@let i + 4
    }
    return 0
}

private fun part2(input: List<String>) = input.first().let { stream ->
    for (i in stream.indices) {
        if (stream.substring(i, i + 14).hasNoRepeats())
            return@let i + 14
    }
    return 0
}

private fun String.hasNoRepeats(): Boolean {
    for (i in indices)
        if (substring((i + 1)..lastIndex).contains(this[i]))
            return false
    return true
}
