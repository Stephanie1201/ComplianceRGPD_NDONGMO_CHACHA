package complianceGRPD
package Service2Hacher

import complianceGRPD.SchemaDonnee
import org.apache.curator.shaded.com.google.common.hash.Hashing
import org.apache.spark.sql.SparkSession

import java.nio.charset.StandardCharsets
import java.util.UUID

object Hacher {
  import org.apache.spark.sql.functions._

  def hashage(str: String): String={
    Hashing.md5().hashString(str, StandardCharsets.UTF_8).toString

  }

  def hacher(): Unit = {

    val sparkSession: SparkSession = SparkSession.builder().master("yarn").getOrCreate()

    /**   read of csv file **/
    val dataframe: org.apache.spark.sql.DataFrame = sparkSession.read.option("header", true)
      .csv("hdfs://172.31.250.9:9000/user/namenode/complianceRGPDMS")

    val resultatHachage = dataframe.withColumn("IdentificationClient", when(col("IdentificationClient") === 1, 1)
        .otherwise(col("IdentificationClient")))
      .withColumn("Nom", when(col("IdentificationClient") === 1, hashage(col("Nom").toString()))// recupère la donnée correspondant à ma colonne nom de l'id 1 et tu hash
        .otherwise(col("Nom")))//Après tu écrase la colonne avec son hachage
      .withColumn("Prenom", when(col("IdentificationClient") === 1,  hashage(col("Prenom")
        .toString())).otherwise(col("Prenom")))
      .withColumn("Adresse", when(col("IdentificationClient") === 1,  hashage(col("Adresse")
        .toString())).otherwise(col("Adresse")))

    resultatHachage.show()

    resultatHachage.write.mode("overwrite").csv("hdfs://172.31.250.9:9000/user/namenode/complianceRGPDMS")
  }

}
