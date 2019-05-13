package ie.eoinahern.currencyconverter.tools


import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DateUtil @Inject constructor() {

    private var dateRanges: MutableMap<String, String> = mutableMapOf()
    private val formatter: DateTimeFormatter by lazy { DateTimeFormatter.ISO_LOCAL_DATE }

    fun getLatestDateRanget(): Map<String, String> {
        dateRanges[NOW_KEY] = LocalDate.now().format(formatter)
        dateRanges[TWOFOUR_HOUR_KEY] = LocalDate.now().minusDays(1).format(formatter)
        dateRanges[ONE_WEEK_KEY] = LocalDate.now().minusDays(7).format(formatter)
        dateRanges[ONE_MONTH_KEY] = LocalDate.now().minusMonths(1).format(formatter)
        dateRanges[SIX_MONTH_KEY] = LocalDate.now().minusMonths(6).format(formatter)

        return dateRanges
    }

    fun parseDataTimeString(dateTimeString: String): LocalDateTime = LocalDateTime.parse(dateTimeString)

    fun checkSaveDateValid(dateTime: LocalDateTime, days: Long): Boolean {
        return dateTime.plusDays(days).isAfter(LocalDateTime.now())
    }
}
