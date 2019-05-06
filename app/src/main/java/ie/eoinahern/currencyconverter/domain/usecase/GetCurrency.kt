package ie.eoinahern.currencyconverter.domain.usecase

import ie.eoinahern.currencyconverter.data.repository.currency.CurrencyRepositoryImpl
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.Either
import javax.inject.Inject


class GetCurrency @Inject constructor(val repository: CurrencyRepositoryImpl) :
    BaseUsecase<Unit, List<DomainCurrency>>() {

    override suspend fun executeUsecase(p: Unit): Either<Failure, List<DomainCurrency>> {
        return try {
            val item = repository.currencyDataStoreFactory
                .getCurrencyDatastore().getCurrencyList()
            if (item.isNullOrEmpty()) {
                Either.Right(emptyList())
            } else {
                Either.Right(item)
            }
        } catch (e: Exception) {
            Either.Left(Failure.NetworkFailure)
        }
    }
}