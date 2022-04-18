data class Card(
    val id: String,
    val name: String,
    val cardType: String,
    val filtering: Filtering? = null
)

data class UserDetails(val osVersion: OperatingSystemVersion)

data class OperatingSystemVersion(
    val majorVersion: Int,
    val minorVersion: Int,
    val patchVersion: Int
)

sealed class Filtering(open val filters: List<Filter>)
data class GroupFiltering(val groupId: String, override val filters: List<Filter>) : Filtering(filters)
data class UngroupedFiltering(override val filters: List<Filter>) : Filtering(filters)

sealed class Filter
object ControlFilter : Filter()
data class ValueFilter(
    val type: FilterType,
    val majorVersion: Int,
    val minorVersion: Int,
    val patchVersion: Int
): Filter()

enum class FilterType {
    osVersionEquals,
    osVersionMajorEquals,
    osVersionMinorEquals,
    osVersionGreaterThan,
    osVersionMajorGreaterThan,
    osVersionMinorGreaterThan,
    osVersionLessThan,
    osVersionMajorLessThan,
    osVersionMinorLessThan,
}
