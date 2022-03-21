package py4j.wandb
import collection.JavaConverters._

object Implicits { 

        implicit def toJavaMap(x: Map[String, Any]) = x.map{ case(k,v) => (k, v.asInstanceOf[Object])}.asJava

}