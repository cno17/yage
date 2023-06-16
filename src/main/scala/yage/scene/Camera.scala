package yage.scene

import yage.math.Mat4

class Camera:

  var matWV = Mat4().toOne()
  var matVW = Mat4().toOne()
  var matCV = Mat4().toOne()
  var matVC = Mat4().toOne()

  def update =
    matVW.set(matWV).invert()
    matVC.set(matCV).invert()

  override def toString() = matWV.toString + "\n" + matVW.toString


/*

private var _matWV = Mat4().toOne
private var _matVW = Mat4().toOne
private var _matCV = Mat4().toOne

def matWV = _matWV
def matVW = _matVW
def matCV = _matCV

def matWV_=(m: Mat4) = _matWV := m  
def matCV_=(m: Mat4) = _matCV := m

override def toString() = s"${_matWV.toString}\n${_matVW.toString}"

*/