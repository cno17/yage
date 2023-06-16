# NanoVG

## ToDo

Vec2.rotate(a)

Vec: secondary constructors

Point, Box useful? use Vec2 and ABox2!

## Context State

The context state is composed of attributes. It can be pushed to and popped from the state stack.

- imageSmoothingEnabled: Boolean // games and pixel art!
- global alpha: Float
- global composit operation // blend mode
- draw style
  - fill style
  - stroke style
- line style
  - width: Float
  - miterLimit: Float
  - dashOffset: Float
  - cap: LineCap
  - join: LineJoin
- text style
  - font: Font
  - fillColor?
  - size
  - blur
  - letterSpacing
  - baseline
  - direction
- shadow style
  - offsetX: Float
  - offsetY: Float
  - blur: Float
  - color: Color

## Drawing Shapes

- Shape
  - Rectangle
  - Path

## Drawing Text

You can load .ttf files into font objects and use them to draw text.
Font measure functions return values in local space

## Draw Style

The draw style affects filling and stroking.

- draw style
  - color
  - paint
    - gradient
      - box gradient
      - linear gradient
      - radial gradient
    - pattern

pen and brush?

## Trafo

Each path, gradient, pattern and scissor region is transformed by the current (affine) transformation
matrix when it is passed to the API.

Apart from nvgResetTransform(), each transformation function first creates
a specific transformation matrix and premultiplies the current transformation by it.

## Images

You can load jpg, png, psd, tga, pic and gif files into image objects and use them for drawing.

## Scissors

A scissor allows you to clip the rendering into a rectangle. This is useful for various
user interface cases like rendering a text edit or a timeline.

## Path
