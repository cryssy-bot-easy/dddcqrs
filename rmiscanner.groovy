// http://marxsoftware.blogspot.com/2010/09/in-previous-blog-post-i-demonstrated.html


import java.rmi.ConnectException
import java.rmi.RemoteException
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry

if (!args || args.length < 3)
{
   println "Provide name of host and range of ports (lowest and highest) on which to look for RMI-exposing ports."
   System.exit(-1)
}
def host = args[0]
def initialPort = args[1] as Integer
def finalPort = args[2] as Integer

println "Searching for RMI-exposed ports on host ${host} within port range ${initialPort}:${finalPort}"
for (port in initialPort..finalPort)
{   
   registry = LocateRegistry.getRegistry(host, port)

   try
   {
      def boundNames = registry.list()
      println "\nRMI Registry found on host:port ${host}:${port} with service names"
      boundNames.each{println "\t${it}"}
   }
   catch (ConnectException connectEx)
   {
      print "."
      // no RMI registry or cannot connect to it
   }
   catch (RemoteException remoteEx)
   {
      print "."
      // no RMI registry or cannot connect to it remotely (covers AccessException)
   }
}
println "\nNo ports with RMI exposed services found on host ${host} within range ${initialPort}:${finalPort}"
