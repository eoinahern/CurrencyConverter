package ie.eoinahern.currencyconverter.domain.usecase

import ie.eoinahern.currencyconverter.data.repository.graph.GraphDataRepositoryImpl
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.tools.Either
import javax.inject.Inject


class GetGraphData @Inject constructor(private val repo: GraphDataRepositoryImpl) :
    BaseUsecase<String, Map<String, Map<String, Double>>>() {

    override suspend fun executeUsecase(p: String): Either<Failure, Map<String, Map<String, Double>>> {
        return repo.getGraphData(p)
    }
}