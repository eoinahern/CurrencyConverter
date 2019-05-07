package ie.eoinahern.currencyconverter.domain

import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.tools.Either
import kotlinx.coroutines.*


abstract class BaseUsecase<in Param, out Result> {

    protected lateinit var usecaseJob: Job

    abstract suspend fun executeUsecase(p: Param): Either<Failure, Result>

    operator fun invoke(
        params: Param, onResult: (Either<Failure, Result>) -> Unit = {}
    ) {
        val job = GlobalScope.async(Dispatchers.IO) {
            executeUsecase(params)
        }

        usecaseJob = GlobalScope.launch(Dispatchers.Main) {
            onResult(job.await())
        }
    }

    fun clearJob() {
        usecaseJob.cancel()
    }


    class None

}
