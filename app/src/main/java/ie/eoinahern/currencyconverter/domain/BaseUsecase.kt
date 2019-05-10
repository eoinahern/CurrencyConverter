package ie.eoinahern.currencyconverter.domain

import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.tools.Either
import kotlinx.coroutines.*


abstract class BaseUsecase<in Param, out Result> {

    abstract suspend fun executeUsecase(p: Param): Either<Failure, Result>

    operator fun invoke(
        params: Param, scope: CoroutineScope, onResult: (Either<Failure, Result>) -> Unit = {}
    ) {
        val job = scope.async(Dispatchers.IO) {
            executeUsecase(params)
        }

        scope.launch {
            onResult(job.await())
        }
    }


    class None

}
