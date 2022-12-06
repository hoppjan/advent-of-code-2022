fun main() {
    val testLines = readInput("Day05_test")

    val testResult1 = part1(testLines)
    println("test part 1: $testResult1")
    check(testResult1 == "CMZ")

    val testResult2 = part2(testLines)
    println("test part 2: $testResult2")
    check(testResult2 == "MCD")


    val lines = readInput("Day05")

    println("part 1: ${part1(lines)}")
    println("part 2: ${part2(lines)}")
}

private fun part1(input: List<String>) = parsePlay(input)
    .let { (field, moves) -> field.toMutableList().play9000(moves).topCrates() }

private fun part2(input: List<String>) = parsePlay(input)
    .let { (field, moves) -> field.toMutableList().play9001(moves).topCrates() }

private fun parsePlay(input: List<String>): Play {
    val moves = input
        .takeLastWhile { line -> line.startsWith("move") }
        .map { it.toMove() }
    val start = input
        .take(input.indexOfFirst { it.isBlank() })
        .dropLast(1)
        .rotate()
        .filter { it.matches(Regex("[A-Z]+ *")) }
        .map { it.trim() }

    return Play(start, moves)
}

private fun MutableList<String>.play9000(moves: List<Move>): List<String> {
    for (move in moves) {
        this[move.to] += this[move.from].takeLast(move.crates).reversed()
        this[move.from] = this[move.from].dropLast(move.crates)
    }
    return toList()
}

private fun MutableList<String>.play9001(moves: List<Move>): List<String> {
    for (move in moves) {
        this[move.to] += this[move.from].takeLast(move.crates)
        this[move.from] = this[move.from].dropLast(move.crates)
    }
    return toList()
}

private fun List<String>.topCrates() = buildString {
    for (stack in this@topCrates)
        append(stack.last())
}

private fun String.toMove() = replace(Regex("[a-z]*"), "")
    .trim()
    .split(Regex(" +"))
    .let { (move, from, to) -> Move(move.toInt(), from.toInt() - 1, to.toInt() - 1) }

private data class Move(
    val crates: Int,
    val from: Int,
    val to: Int,
)

private data class Play(
    val field: List<String>,
    val moves: List<Move>,
)

private fun List<String>.rotate(): List<String> = buildList {
    for (charIndex in 0 until this@rotate.maxOf { it.length })
        add(
            buildString {
                for (lineIndex in this@rotate.indices.reversed())
                    append(this@rotate.getOrElse(lineIndex) { " " }.getOrElse(charIndex) { ' ' })
            }
        )
}
