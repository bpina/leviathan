package org.leviathan.config

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import scala.reflect.BeanProperty
import scala.io.Source
import net.noerd.prequel.DatabaseConfig

object Configurator {
  abstract class DbProperties {
    @BeanProperty var host : String
    @BeanProperty var port : Int
    @BeanProperty var username : String
    @BeanProperty var password : String
    @BeanProperty var database : String
    @BeanProperty var ssl : Boolean
  }
  abstract case class Database(@BeanProperty var database: String, @BeanProperty var host: String, @BeanProperty var port: Int) extends DbProperties
  abstract case class Options(@BeanProperty var ssl: Boolean, @BeanProperty var username: String, @BeanProperty var password: String) extends DbProperties

  def getDatabaseConfig(file: String) : DatabaseConfig = {
    val config = Source.fromFile(file).mkString

    if(config == null && config.isEmpty)
      throw new Exception("No database configuration")
       
    val yaml = new Yaml(new Constructor(classOf[DbProperties]))
    val properties = yaml.load(config).asInstanceOf[DbProperties]
    
    var jdbcUrl = properties match {
      case Database(database, null, _) =>
        s"jdbc:postgres:$database"
      case Database(database, host, port) => port match {
        case 0 => s"jdbc:postgres://$host/$database"
        case _ => s"jdbc:postgres://$host:$port/$database"
      }
      case _ =>
        null
    }

    if (jdbcUrl == null) throw new Exception("No database specified")

    val options = properties match {
      case Options(ssl, null, null) =>
        s"ssl_enabled=$ssl"
      case Options(ssl, username, null | "") =>
        s"ssl_enabled=$ssl&user=$username"
      case Options(ssl, username, password) =>
        s"ssl_enabled=$ssl&user=$username&password=$password"
      case _ =>
        null
    }

    if(options != null) jdbcUrl = s"$jdbcUrl?options"

    DatabaseConfig(
      driver = "org.postgres.Driver",
      jdbcURL = jdbcUrl
    )
  }
}
