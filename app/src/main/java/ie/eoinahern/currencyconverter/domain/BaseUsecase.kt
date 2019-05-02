package ie.eoinahern.currencyconverter.domain

import ie.eoinahern.currencyconverter.tools.Either


abstract class BaseUsecase<in Param, out Res> {

    abstract suspend fun executeUsecase(p: Param): Either<Unit, Res>
}