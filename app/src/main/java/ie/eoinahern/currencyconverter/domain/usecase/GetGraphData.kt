package ie.eoinahern.currencyconverter.domain.usecase

import ie.eoinahern.currencyconverter.data.repository.graph.GraphDataRepositoryImpl
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.tools.Either
import javax.inject.Inject


class GetGraphData @Inject constructor(private val repo: GraphDataRepositoryImpl) :
    BaseUsecase<Triple<String, String, String>, Map<String, Double>>() {

    override suspend fun executeUsecase(params: Triple<String, String, String>): Either<Failure, Map<String, Double>> {
        return repo.getGraphData(params.first, params.second, params.third)
    }
}