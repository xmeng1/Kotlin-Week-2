package mastermind


data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val length = secret.length
    var right = 0
    val rightPositionArray = BooleanArray(length) { _ -> false }

    secret.forEachIndexed { index, char ->
        if (char == guess[index]) {
            right++;
            rightPositionArray[index] = true
        }
    }
    var wrong = 0
    if (right == length) {
        return Evaluation(right, wrong)
    }
    val secretHasMatched = BooleanArray(length) { _ -> false }
    guess.forEachIndexed { index, cGuess ->
        /*val sameChar = { a: Int -> a == c.toInt() }
        if (unmatchedSecret.chars().anyMatch(sameChar)) {
            wrong--
            unmatchedSecret.
        }*/
        for (i in secret.indices) {
            if (cGuess == secret[i]
                    && i != index  // guarantee position is diff
                    && !rightPositionArray[index] // guarantee the matched position not consider
                    && !rightPositionArray[i]  // guarantee the matched position not consider
                    && !secretHasMatched[i]) {   // guarantee the secret position do not compare twice
                wrong++
                secretHasMatched[i] = true
                break
            }
        }
    }
    return Evaluation(right, wrong)
}
