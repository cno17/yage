# Image

## Todo

- Hill <- ParaHill, ExpoHill (Glockenkurve, Bronstein), SineHill
- elliptic vs circular domain
- HillGen: add/subtract(hill)

- Vector, Matrix

- LaplaceOp(adj, eps)
- GradientOp: Stability, Noise

- ImageProducer: ProgressListener

- Adj: separate toroidality from domain? use some kind of mapping if needed!

- ImageCombinators:
  - pixelwise, kernelwise
  - two images of same size
  - large and small image: imgL.contains(imgS.center), imgL >= imgS (in all dimensions)
  - BlendCombinator

- SelectOperator

- Slices: 2d slice of 3d image?

- Pixel.take(off, ext/num) // rename: range, slice, projection, factor
- Pixel.join // cross, cartesian product

- Core Types:
  - Adjacency
  - Topology(adjFg, adjBg)
  - Grid(top)
  - Set
  - Region // connected
  - Domain(min, max)

- HeightMapGenerators: Lava, ...

## Grid

## Types

## GTE
