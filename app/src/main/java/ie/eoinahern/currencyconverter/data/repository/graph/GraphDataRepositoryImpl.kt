package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.repository.GraphDataRepository
import ie.eoinahern.currencyconverter.tools.Either
import javax.inject.Inject


class GraphDataRepositoryImpl @Inject constructor() : GraphDataRepository<Either<Failure, List<String>>> {

    override fun getGraphData(): Either<Failure, List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

