import assertk.assertThat
import assertk.assertions.containsExactly
import org.junit.jupiter.api.Test

internal class FunctionalTests {
    private val inputJson = this::class.java.getResourceAsStream("input.json")?.bufferedReader()?.readText()!!

    @Test
    fun `should produce correct answer for null osVersion`() {
        val result = runChallenge(inputJson, null)

        val cardNames = result.map { it.name }

        assertThat(cardNames).containsExactly("Carousel", "Banner", "New departments list default")
    }

    @Test
    fun `should produce correct answer for 15 0 0 osVersion`() {
        val result = runChallenge(inputJson, null)

        val cardNames = result.map { it.name }

        assertThat(cardNames).containsExactly("Carousel", "Banner", "Recommendations1", "New departments list 15", "Banner2")
    }

    @Test
    fun `should produce correct answer for 14 0 1 osVersion`() {
        val result = runChallenge(inputJson, null)

        val cardNames = result.map { it.name }

        assertThat(cardNames).containsExactly("Carousel", "Banner", "New departments list default", "Recommendations2")
    }

    @Test
    fun `should produce correct answer for 13 0 0 osVersion`() {
        val result = runChallenge(inputJson, null)

        val cardNames = result.map { it.name }

        assertThat(cardNames).containsExactly("Carousel", "Banner", "Old departments list")
    }
}