package yage.math.analysis.integration

trait UnivariateIntegrator:

  var relAccuracy = 0.0
  var absAccuracy = 0.0
  var minIterations = 10
  var maxIterations = 1000

  def numIterations = 0
  def numEvaluations = 0
