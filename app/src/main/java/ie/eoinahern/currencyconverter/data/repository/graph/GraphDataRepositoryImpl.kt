package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.repository.GraphDataRepository
import ie.eoinahern.currencyconverter.tools.DateUtil
import ie.eoinahern.currencyconverter.tools.Either
import java.io.IOException
import javax.inject.Inject


class GraphDataRepositoryImpl @Inject constructor(
    val factory: GraphDataStorFactory
) : GraphDataRepository<Either<Failure, Map<String, Map<String, Double>>>> {

    override fun getGraphData(symbol: String): Either<Failure, Map<String, Map<String, Double>>> {

        return try {
            val graphData = factory.getDataStore().getList(symbol)
            Either.Right(graphData)
        } catch (exc: Exception) {
            if (exc is IOException) {
                Either.Left(Failure.NetworkFailure)
            } else {
                Either.Left(Failure.ServerError)
            }
        }
    }
}

