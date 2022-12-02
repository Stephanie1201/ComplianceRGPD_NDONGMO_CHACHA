package complianceGRPD
package Service1Delete

import org.apache.spark.sql.{DataFrame, SparkSession}

object Delete {

  import org.apache.spark.sql.functions._

  def delete(): Unit = {
    val sparkSession: SparkSession = SparkSession.builder().master("spark://master:7077").getOrCreate()
    sparkSession.conf.set("spark.sql.execution.arrow.enabled", "true")

    /**   lit le fichier csv*   */
    val dataframe: org.apache.spark.sql.DataFrame = sparkSession.read.option("header", true)
        //.csv("data\\steph.csv") //local
        .csv("hdfs://172.31.250.9:7077/user/namenode/complianceRGPDMS")//hdfs
    val appelShema = sparkSession.createDataFrame(dataframe.rdd, SchemaDonnee.schema)

    /**   Je supprime la ligne*   */
    val result: DataFrame = dataframe.filter(col("IdentificationClient") =!= "1")

    /** J'affiche le reustat dans la console*   */
    result.show()

    /** Réecriture du nouveau contenu du fichier **   */
    result.write.format("csv").mode("overwrite").save("data\\result.csv")

  }

}
