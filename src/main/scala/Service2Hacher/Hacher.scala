package complianceGRPD
package Service2Hacher

import complianceGRPD.SchemaDonnee
import org.apache.spark.sql.SparkSession

import java.util.UUID

object Hacher {
  import org.apache.spark.sql.functions._

  def hacher(): Unit = {

    val sparkSession: SparkSession = SparkSession.builder().master("local").getOrCreate()

    /** lit le fichier csv* */
    val dataframe: org.apache.spark.sql.DataFrame = sparkSession.read.option("header", true)
      .csv("data\\steph.csv") //.csv("hdfs://172.31.249.118:9000/user/administrator/complianceRGPDMS")
     // .csv("hdfs://172.31.250.9:7077/user/namenode/complianceRGPDMS")
    val appelShema = sparkSession.createDataFrame(dataframe.rdd, SchemaDonnee.schema)

    /** Affichage du contenu de notre fichier steph.csv dans la console**/
    val datasfile = dataframe.toDF("IdentificationClient",",Nom","Prenom","Adresse","DateDeSouscription")
        datasfile.show()
    /** Hachage des données de notre fichier et affichage du resultat dans la console**/
    val generateUUID = udf(() => UUID.randomUUID().toString)
    val dataHache = datasfile.withColumn("UUID", generateUUID())
        dataHache.show()

    /** Autres méthode pour hacher**/
     dataframe.toDF("IdentificationClient",",Nom","Prenom","Adresse","DateDeSouscription")
      .withColumn("uuid", expr("uuid()"))
      .show(false)


  }

}
