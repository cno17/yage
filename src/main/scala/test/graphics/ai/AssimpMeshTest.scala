package test.graphics.ai

import java.io.File
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
import org.lwjgl.assimp.Assimp.aiProcess_Triangulate

object AssimpMeshTest:

  def main(args: Array[String]) = thingTest

  def cubeTest =
    // File file = new File("src\\main\\resources\\mesh\\Cube.obj");
    val file = File("res/mesh/Cube.obj")
    val flags =
      aiProcess_Triangulate | aiProcess_CalcTangentSpace | aiProcess_GenSmoothNormals | aiProcess_GenBoundingBoxes
    val scene = aiImportFile(file.getAbsolutePath, flags)
    val mesh = AIMesh.create(scene.mMeshes.get(0))
    for i <- 0 until mesh.mNumFaces do
      val face = mesh.mFaces.get(i)
      val indices = face.mIndices
      // println(indices.get(0) + ", " + indices.get(1) + ", " + indices.get(2))
    val box = mesh.mAABB
    val min = box.mMin
    val max = box.mMax
    // println(min.x + ", " + min.y + ", " + min.z)
    // println(max.x + ", " + max.y + ", " + max.z)
    println(mesh.mNumFaces)

  def thingTest =
    val f = File("res/mesh/Thing1.obj")
    // int flags = aiProcess_CalcTangentSpace | aiProcess_Triangulate | aiProcess_GenSmoothNormals;
    val flags = aiProcess_CalcTangentSpace
    val scene = aiImportFile(f.getAbsolutePath, flags)
    println(scene.mNumMeshes)
    println(scene.mNumMaterials)
    val mesh = AIMesh.create(scene.mMeshes.get(0))
    println(mesh.mNumVertices)
    println(mesh.mNumFaces)
    println(mesh.mVertices)
    println(mesh.mNormals)
    println(mesh.mTangents)
    println(mesh.mTextureCoords)
    // println(mesh.mAABB());
    for i <- 0 to mesh.mNumVertices - 1 do
      val pos = mesh.mVertices().get(i);
      println(pos.x());
    println(mesh.mNumUVComponents)
