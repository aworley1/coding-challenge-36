import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

private val mapper = jacksonObjectMapper()
fun mapInput(input: String) = mapper.readValue<CardsJson>(input)

data class CardsJson(val id: String, val cardType: String, val name: String, val filtering: FilteringJson?)
data class FilteringJson(val groupId: String?, val filters: List<FilterJson>)
data class FilterJson(val filter: String, val value: String?)