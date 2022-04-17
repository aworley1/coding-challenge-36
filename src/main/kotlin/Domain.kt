data class Card(
    val id: String,
    val name: String,
    val cardType: String,
    val filtering: Filtering
    )

data class UserDetails(val osVersion: OperatingSystemVersion)

data class OperatingSystemVersion(
    val majorVersion: Int,
    val minorVersion: Int,
    val patchVersion: Int
)

sealed class Filtering(val filters: List<Filter>)
data class GroupFilter(val groupId: String, val filters: List<Filter>)

sealed class Filter(val type: FilterType)
enum class FilterType
