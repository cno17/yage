package test

object SbtTest:

  def main(args: Array[String]) =

    val modules = Seq(
      "lwjgl",
      "lwjgl-glfw",
      "lwjgl-opengl",
      "lwjgl-stb"
    )

    val deps = modules.flatMap(mod => Seq(
        s"org.lwjgl % $mod % 3.1.3",
        s"org.lwjgl % $mod % 3.1.3 classifier natives-windows" // linux
      )
    )

    println(deps.mkString("\n"))

    // libraryDependencies ++= deps