package yage.math

import scala.util.Random

trait MathUtil:

  type S = Float
  
  val PI = Math.PI.toFloat

  def abs(x: Int) = Math.abs(x)
  def abs(x: Float) = Math.abs(x)
  def acos(x: Float) = Math.acos(x).toFloat
  def asin(x: Float) = Math.asin(x).toFloat
  def ceil(x: Float) = Math.ceil(x).toFloat
  def cos(x: Float) = Math.cos(x).toFloat
  def floor(x: Float) = Math.floor(x).toFloat
  def fract(x: Float) = x - floor(x)  
  def max(x: Int, y: Int) = Math.max(x, y)
  def max(x: Float, y: Float) = Math.max(x, y)
  def min(x: Int, y: Int) = Math.min(x, y)
  def min(x: Float, y: Float) = Math.min(x, y)
  def nextFloat = Random.nextFloat
  def nextFloat(min: Float, max: Float) = min + (max - min) * Random.nextFloat
  def pow(x: Float, y: Float) = Math.pow(x, y).toFloat
  def sin(x: Float) = Math.sin(x).toFloat
  def sqrt(x: Float) = Math.sqrt(x).toFloat
  def tan(x: Float) = Math.tan(x).toFloat
  def toDegrees(rad: Float) = Math.toDegrees(rad).toFloat
  def toRadians(deg: Float) = Math.toRadians(deg).toFloat


  def distance(x1: Float, y1: Float, x2: Float, y2: Float): Float = 0
  def distanceOne = 0
  // ...

  def distanceSquared(x1: Float, y1: Float, x2: Float, y2: Float): Float = 0
