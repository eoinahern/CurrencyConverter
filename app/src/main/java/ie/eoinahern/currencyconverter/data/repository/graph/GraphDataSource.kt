package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.data.model.GraphNestedMap


interface GraphDataSource {
    fun getList(symbols: String): GraphNestedMap
}