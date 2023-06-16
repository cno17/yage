package yage.scene.geometry

import java.io.File
import yage.math.Vec4
import java.nio.IntBuffer
import org.lwjgl.assimp.AIAABB
import org.lwjgl.assimp.AIFace
import org.lwjgl.assimp.AIMesh
import org.lwjgl.assimp.AIScene
import org.lwjgl.assimp.AIVector3D
import org.lwjgl.assimp.Assimp.aiImportFile
import org.lwjgl.assimp.Assimp.aiProcess_CalcTangentSpace
import org.lwjgl.assimp.Assimp.aiProcess_GenBoundingBoxes
import org.lwjgl.assimp.Assimp.aiProcess_GenSmoothNormals
import org.lwjgl.assimp.Assimp.*
import yage.math.Vec2

class ObjGeometry(file: File) extends Geometry:

  // var box: AIAABB = null

  // make this more functionally
  override def init() = 
    val flags = aiProcess_JoinIdenticalVertices | aiProcess_Triangulate | aiProcess_CalcTangentSpace | aiProcess_GenSmoothNormals | aiProcess_GenBoundingBoxes
    val scene = aiImportFile(file.getAbsolutePath, flags)
    val mesh = AIMesh.create(scene.mMeshes.get(0))
    val posB = mesh.mVertices()
    val norB = mesh.mNormals()
    val tanB = mesh.mTangents()
    val triB = mesh.mFaces()
    val nv = mesh.mNumVertices()
    val nf = mesh.mNumFaces()
    // box = mesh.mAABB() // seems to be null
    if posB != null then
      posA = new Array[Vec4](nv)
      for i <- 0 to nv - 1 do 
        posA(i) = toPos(posB.get(i))
    if norB != null then
      norA = new Array[Vec4](nv)
      for i <- 0 to nv - 1 do 
        norA(i) = toDir(norB.get(i))
    if tanB != null then
      tanA = new Array[Vec4](nv)
      for i <- 0 to nv - 1 do 
        tanA(i) = toDir(tanB.get(i))
    // todo cols, tecs: fake for now
    colA = new Array[Vec4](nv)
    for i <- 0 to nv - 1 do 
      colA(i) = Vec4(1, 1, 1, 1)
    tecA = new Array[Vec2](nv)
    for i <- 0 to nv - 1 do 
      tecA(i) = Vec2(1, 1)
    // end of fake
    if triB != null then
      indA = new Array[Int](3 * nf)
      for i <- 0 to nf - 1 do
        val is = triB.get(i).mIndices
        indA(3 * i + 0) = is.get(0)
        indA(3 * i + 1) = is.get(1)
        indA(3 * i + 2) = is.get(2)

  private def toDir(v: AIVector3D) = Vec4(v.x(), v.y(), v.z(), 0)   
  private def toPos(v: AIVector3D) = Vec4(v.x(), v.y(), v.z(), 1)   


