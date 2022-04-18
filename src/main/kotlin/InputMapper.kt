import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

private val mapper = jacksonObjectMapper()
fun mapInput(input: String): List<Card> {
    val cardsJson = mapper.readValue<List<CardJson>>(input)
    return cardsJson.map {
        Card(
            id = it.id,
            name = it.name,
            cardType = it.cardType,
            filtering = mapFiltering(it.filtering)
        )
    }
}

private fun mapFiltering(filtering: FilteringJson?) =
    filtering?.groupId?.let { groupId ->
        GroupFiltering(
            groupId = groupId,
            filters = mapFilters(filtering.filters)
        )
    } ?: filtering?.let {
        UngroupedFiltering(mapFilters(it.filters))
    }

private fun mapFilters(filters: List<FilterJson>): List<Filter> = filters.map {
    if (it.filter == "controlGroup") ControlFilter
    else ValueFilter(
        type = FilterType.valueOf(it.filter),
        majorVersion = it.value!!.split(".")[0].toInt(),
        minorVersion = it.value.split(".")[1].toInt(),
        patchVersion = it.value.split(".")[2].toInt(),
    )
}


data class CardJson(val id: String, val cardType: String, val name: String, val filtering: FilteringJson?)
data class FilteringJson(val groupId: String?, val filters: List<FilterJson>)
data class FilterJson(val filter: String, val value: String?)