import FilterType.osVersionEquals
import FilterType.osVersionMajorGreaterThan
import assertk.assertThat
import assertk.assertions.containsExactly
import org.junit.jupiter.api.Test

internal class InputMapperKtTest {
    @Test
    fun `should map card with no filtering`() {
        val input = """
            [
              {
                "id": "card1",
                "cardType": "carousel",
                "name": "Carousel"
              }
            ]
        """.trimIndent()

        val result = mapInput(input)

        assertThat(result).containsExactly(
            Card(id = "card1", name = "Carousel", cardType = "carousel")
        )
    }

    @Test
    fun `should map card with group filtering`() {
        val input = """[
          {
            "id": "card1",
            "cardType": "carousel",
            "name": "Carousel",
            "filtering": {
              "groupId": "departmentGroup",
              "filters": [
                {
                  "filter": "osVersionEquals",
                  "value": "13.1.2"
                }
              ]
            }
          }
        ]""".trimIndent()

        val result = mapInput(input)

        assertThat(result).containsExactly(
            Card(
                id = "card1",
                name = "Carousel",
                cardType = "carousel",
                filtering = GroupFiltering(
                    "departmentGroup",
                    listOf(
                        ValueFilter(
                            type = osVersionEquals,
                            majorVersion = 13,
                            minorVersion = 1,
                            patchVersion = 2
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `should map card with ungrouped filtering`() {
        val input = """[
          {
            "id": "card1",
            "cardType": "carousel",
            "name": "Carousel",
            "filtering": {
              "filters": [
                {
                  "filter": "osVersionMajorGreaterThan",
                  "value": "15.0.7"
                }
              ]
            }
          }
        ]""".trimIndent()

        val result = mapInput(input)

        assertThat(result).containsExactly(
            Card(
                id = "card1",
                name = "Carousel",
                cardType = "carousel",
                filtering = UngroupedFiltering(
                    listOf(
                        ValueFilter(
                            type = osVersionMajorGreaterThan,
                            majorVersion = 15,
                            minorVersion = 0,
                            patchVersion = 7
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `should map card with control group filtering`() {
        val input = """[
          {
            "id": "card1",
            "cardType": "carousel",
            "name": "Carousel",
            "filtering": {
              "groupId": "departmentGroup",
              "filters": [
                {
                  "filter": "controlGroup"
                }
              ]
            }
          }
        ]""".trimIndent()

        val result = mapInput(input)

        assertThat(result).containsExactly(
            Card(
                id = "card1",
                name = "Carousel",
                cardType = "carousel",
                filtering = GroupFiltering(
                    "departmentGroup",
                    listOf(ControlFilter)
                )
            )
        )
    }
}