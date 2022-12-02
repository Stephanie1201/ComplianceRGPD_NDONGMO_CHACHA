package complianceGRPD
package Service2Hacher

import complianceGRPD.SchemaDonnee
import org.apache.spark.sql.SparkSession

import java.util.UUID

object Hacher {
  import org.apache.spark.sql.functions._

  def hacher(): Unit = {

    val sparkSession: SparkSession = SparkSession.builder().master("spark://172.31.250.9:7077").getOrCreate()

    /**   read of csv file **/
    val dataframe: org.apache.spark.sql.DataFrame = sparkSession.read.option("header", true)
      .csv("hdfs://172.31.250.9:7077/user/namenode/complianceRGPDMS")

    val appelShema = sparkSession.createDataFrame(dataframe.rdd, SchemaDonnee.schema)

    /** Show the content of file.csv in our terminal **/
    val datasfile = dataframe.toDF("IdentificationClient",",Nom","Prenom","Adresse","DateDeSouscription")
        datasfile.show()

    /** Hash data from our file and display the result in the terminal **/
    val generateUUID = udf(() => UUID.randomUUID().toString)
    val dataHache = datasfile.withColumn("UUID", generateUUID())
        dataHache.show()

    /** Other chopping methods **/
     dataframe.toDF("IdentificationClient",",Nom","Prenom","Adresse","DateDeSouscription")
      .withColumn("uuid", expr("uuid()"))
      .show(false)


  }

}
