
name := "py4j-wandb"

ThisBuild / version := "0.1.0"

scalaVersion := "2.12.8"

libraryDependencies  ++= Seq(
    // "com.opencsv" % "opencsv" % "5.3",
    "net.sf.py4j" % "py4j" % "0.10.7",
    // "org.deeplearning4j" % "deeplearning4j-core" % "0.9.1",
    // "org.apache.commons" % "commons-csv" % "1.9.0",
    // "me.shadaj" %% "scalapy-core" % "0.5.1",
    // "ai.kien" %% "python-native-libs" % "0.2.2"
)

// fork := true
// import scala.sys.process._
// javaOptions += s"-Djava.library.path=/Users/timothy.whittaker/.pyenv/versions/3.8.6/lib"
// javaOptions += "-Djna.library.path=/usr/local/opt/python@3.7/Frameworks/Python.framework/Versions/3.7/lib"
// fork := true

// import ai.kien.python.Python

// lazy val python = Python("/Users/timothy.whittaker/.pyenv/versions/3.8.6/bin/python")

// lazy val javaOpts = python.scalapyProperties.get.map {
//   case (k, v) => s"""-D$k=$v"""
// }.toSeq



// javaOptions ++= javaOpts


// import scala.sys.process._
// lazy val pythonLdFlags = {
//   val withoutEmbed = "/Users/timothy.whittaker/.pyenv/versions/3.8.6/bin/python3-config --ldflags".!!
//   if (withoutEmbed.contains("-lpython")) {
//     withoutEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
//   } else {
//     val withEmbed = "/Users/timothy.whittaker/.pyenv/versions/3.8.6/bin/python3-config --ldflags --embed".!!
//     withEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
//   }
// }

// lazy val pythonLibsDir = {
//   pythonLdFlags.find(_.startsWith("-L")).get.drop("-L".length)
// }


// // javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib"

// // javaOptions += s"-Djna.library.path=/Users/timothy.whittaker/.pyenv/versions/3.9.7/lib"
// javaOptions += s"-Djna.library.path=$pythonLibsDir"
// // javaOptions += s"-Djna.library.path=/Users/timothy.whittaker/.pyenv/shims/python3.8"

// export SCALAPY_PYTHON_PROGRAMNAME=/Users/timothy.whittaker/.pyenv/shims/python3.8

// export SCALAPY_PYTHON_LIBRARY=python3.8