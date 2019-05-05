package ie.eoinahern.currencyconverter.domain.exception


sealed class Failure {
    object NetworkFailure : Failure()
    object ServerError : Failure()
    abstract class FeatureFailure : Failure()
}