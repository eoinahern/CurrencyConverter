package ie.eoinahern.currencyconverter.data.repository.graph


interface GraphDataSource {
    fun getList(start: String, end: String, symbols: String): Map<String, Double>
}