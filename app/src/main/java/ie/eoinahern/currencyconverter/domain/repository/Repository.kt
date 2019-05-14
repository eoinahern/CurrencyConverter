package ie.eoinahern.currencyconverter.domain.repository


interface Repository<T> {
    fun getData(): T
}