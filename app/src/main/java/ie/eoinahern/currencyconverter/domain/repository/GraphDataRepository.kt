package ie.eoinahern.currencyconverter.domain.repository


interface GraphDataRepository<T> {
    fun getGraphData(): T
}