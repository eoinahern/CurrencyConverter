package ie.eoinahern.currencyconverter.data.repository.graph


interface GraphDataSource {
    fun getList(symbols: String): Map<String, Map<String, Double>>
}