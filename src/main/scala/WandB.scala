package py4j.wandb

import py4j.GatewayServer;
import py4j.ClientServer;
import java.lang.ProcessBuilder;
import collection.JavaConverters._
import java.net.ServerSocket
// import scala.util.{Using, Try}
import java.io.File
import java.net.InetAddress;
import scala.collection.JavaConverters._

class WandB(outputFile: String = null) {
    
    import WandB._   

    val port = 25333
    val addressBytes = InetAddress.getLoopbackAddress().getAddress
    val address = InetAddress.getByAddress(addressBytes)
    val pythonPort = 25334
    val pythonAddressBytes = InetAddress.getLoopbackAddress().getAddress
    val pythonAddress = InetAddress.getByAddress(pythonAddressBytes)
    val connectTimeout = 60000000
    val readTimeout = 60000000

    val args = List("start_wandb_gateway", "--java-address", address.getHostAddress, "--java-port", port.toString, "--python-address", pythonAddress.getHostAddress, "--python-port", pythonPort.toString).asJava

    val wandbPython = 
        outputFile match { 
            case null => new ProcessBuilder().inheritIO().command(args).start()
            case _ => new ProcessBuilder().redirectOutput(new File(outputFile)).redirectErrorStream(true).command(args).start()
        }
        
    val clientServer: ClientServer = new ClientServer(null)
    // val clientServer: ClientServer = new ClientServer(port, address, pythonPort, pythonAddress, connectTimeout, readTimeout, null, null, null) 
    val interface: IWandB = clientServer.getPythonServerEntryPoint( Array[Class[_]]( classOf[IWandB])).asInstanceOf[IWandB]
    
    def login(anonymous: String = null, key: String = null, relogin: Boolean = false, host: String = null, 
    force: Boolean = false, timeout: Integer = null ) = { 
        interface.login(anonymous, key, relogin, host, force, timeout)
    }

    def log(data: java.util.Map[String, Object], step: Integer = null, commit: Boolean = false, sync: Boolean = false) = {
        interface.log(data, step, commit, sync)
    }

    def logArtifact(artifactPath: String, name: String = null, artifactType: String = null, aliases: java.util.List[String] = null) = { 
        interface.logArtifact(artifactPath, name, artifactType, aliases) 
    }

    def init(job_type: String = null,
            dir: String = null,
            config: java.util.Map[String, java.lang.Object] = null, 
            project: String = null,
            entity: String = null,
            reinit: Boolean = false,
            tags: java.util.List[String] = null ,
            group: String = null,
            name: String = null,
            notes: String = null,
            magic: java.util.Map[String, java.lang.Object] = null ,
            configExcludeKeys: java.util.List[String] = null,
            configIncludeKeys: java.util.List[String] = null ,
            anonymous: String = null,
            mode: String = null,
            allowValChange: Boolean = false,
            resume: Boolean = false ,
            force: Boolean = false,
            tensorBoard: java.lang.Object = null,
            syncTensorBoard: java.lang.Object = null ,
            monitorGym: java.lang.Object = null,
            saveCode: java.lang.Object = null,
            id: String = null,
            settings: java.util.Map[String, java.lang.Object]= null) =  {
                interface.init(
                    job_type,dir,config,project,entity,reinit,tags,group,name,notes,magic,configExcludeKeys,configIncludeKeys,anonymous,mode,allowValChange,resume,force,tensorBoard,syncTensorBoard,monitorGym,saveCode,id,settings
                )
            }


    def finish() = interface.finish()

    // def connectGateway() = {
    //     clientServer = new ClientServer(null);
    //     interface = clientServer.getPythonServerEntryPoint( Array[Class[_]]( classOf[IWandB])).asInstanceOf[IWandB]
    //     interface
    // }

    def shutdownGateway() = { 
        this.clientServer.shutdown
        this.wandbPython.destroy
    }


}

object WandB { 
    type Config = java.util.HashMap[String, Any]
    // def findFreePort(): Try[Int] = Using(new ServerSocket(0)) (_.getLocalPort)
}