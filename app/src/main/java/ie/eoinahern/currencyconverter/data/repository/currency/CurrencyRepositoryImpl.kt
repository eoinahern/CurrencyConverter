package ie.eoinahern.currencyconverter.data.repository.currency

import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.repository.Repository
import ie.eoinahern.currencyconverter.tools.Either
import java.io.IOException
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(private val currencyDataStoreFactory: CurrencyDataStoreFactory) :
    Repository<Either<Failure, List<DomainCurrency>>> {

    override fun getData(): Either<Failure, List<DomainCurrency>> {
        return try {
            val data = currencyDataStoreFactory.getCurrencyDatastore().getCurrencyList()
            Either.Right(data)
        } catch (e: Exception) {
            if (e is IOException) {
                Either.Left(Failure.NetworkFailure)
            } else {
                Either.Left(Failure.ServerError)
            }
        }
    }
}