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

object ImportTest1:

  def main(args: Array[String]): Unit = 
    val file = File("src/main/resources/meshes/Crate.obj")
    val flags = aiProcess_Triangulate | aiProcess_CalcTangentSpace | aiProcess_GenSmoothNormals | aiProcess_GenBoundingBoxes
    val scene = aiImportFile(file.getAbsolutePath, flags)
    val mesh = AIMesh.create(scene.mMeshes.get(0))
    val box = mesh.mAABB()
    println(toString(box.mMin()))
    println(toString(box.mMax()))

  def toString(v: AIVector3D) = s"(${v.x()}, ${v.y()}, ${v.z()}))"