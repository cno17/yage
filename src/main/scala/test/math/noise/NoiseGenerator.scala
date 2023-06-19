package test.math.noise

import yage.math.noise.SimplexNoise

import java.awt.image.BufferedImage
import java.awt.Color

// move to image package

object NoiseGenerator:
	
	val amplitude = 0.0
	val frequency = 0.0

	def createImage2(si: Int, sj: Int) =
		val img = Image2(si, sj)
		for i <- 0 to si - 1 do
			for j <- 0 to sj - 1 do
				img(i, j) = SimplexNoise.noise(i, j)
		img

def createImage(si: Int, sj: Int) =
		val img = BufferedImage(si, sj, BufferedImage.TYPE_INT_RGB)
		for i <- 0 to si - 1 do
			val x = i.toDouble / si
			for j <- 0 to sj - 1 do
				val y = j.toDouble / sj
				val n = (SimplexNoise.noise(x, y).toFloat + 1) / 2
				img.setRGB(i, j, Color(n, n, n).getRGB())
		img
        
