@Grapes([
    @Grab(group='com.sparkjava', module='spark-core', version='2.9.3'),
    @Grab(group='org.slf4j', module='slf4j-simple', version='1.7.32')
])

import static spark.Spark.*

def isOpened(def host, def port) {
  try {
    (new Socket(host, port)).close()
    return true
  }
  catch(SocketException e) {
    return false
  }
  return false
}

port(9200)

def portsToCheck = System.env["PORTS_TO_CHECK"]

def toCheck = portsToCheck.split(",").collect {
    def (ip, port) = it.split(":")
    [ ip: ip, port: port as Integer ]
}


get("/*", { req, res ->
   def ret = "" 
   toCheck.each {
       def status = isOpened(it.ip, it.port) ? 1 : 0
       ret += "opened_port_status{ip=\"${it.ip}\",port=\"${it.port}\"} ${status}\n"
   }
   ret
})
