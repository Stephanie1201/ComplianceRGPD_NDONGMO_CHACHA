package complianceGRPD
package Service1Delete

import org.apache.spark.sql.{DataFrame, SparkSession}

object Delete {

  import org.apache.spark.sql.functions._

  def delete(): Unit = {
    val sparkSession: SparkSession = SparkSession.builder().master("spark://172.31.250.9:7077").getOrCreate()
    sparkSession.conf.set("spark.sql.execution.arrow.enabled", "true")

    /**   read of csv file **/
    val dataframe: org.apache.spark.sql.DataFrame = sparkSession.read.option("header", true)
        .csv("hdfs://172.31.250.9:7077/user/namenode/complianceRGPDMS")//hdfs

    val appelShema = sparkSession.createDataFrame(dataframe.rdd, SchemaDonnee.schema)

    /** Delete of row with id=1 **/
    val result: DataFrame = dataframe.filter(col("IdentificationClient") =!= "1")

    /** Show the result in our terminal **/
    result.show()

    /** whrite the result in the new csv file **/
    result.write.format("csv").mode("overwrite").save("hdfs://172.31.250.9:7077/user/namenode/complianceRGPDMS/result.csv")
  }

}
