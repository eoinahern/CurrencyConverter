package ie.eoinahern.currencyconverter.domain.repository


interface GraphDataRepository<T> {
    fun getGraphData(start: String, end: String, symbol: String): T
}