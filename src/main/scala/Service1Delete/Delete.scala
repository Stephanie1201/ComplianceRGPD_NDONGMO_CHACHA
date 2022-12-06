package complianceGRPD
package Service1Delete

import com.sun.xml.internal.bind.api.impl.NameConverter.Standard
import org.apache.curator.shaded.com.google.common.hash.Hashing
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.nio.charset.StandardCharsets

object Delete {

  import org.apache.spark.sql.functions._

  def delete(): Unit = {

    val sparkSession: SparkSession = SparkSession.builder().master("hdfs://localhost:9000/user/complianceRGPDMS/").getOrCreate()
    sparkSession.conf.set("spark.sql.execution.arrow.enabled", "true")

    /**   read of csv file **/
    val dataframe: org.apache.spark.sql.DataFrame = sparkSession.read.option("header", true)
        .csv("hdfs://localhost:9000/user/complianceRGPDMS")//hdfs

    val appelShema = sparkSession.createDataFrame(dataframe.rdd, SchemaDonnee.schema)

    /** Delete of row with id=1 **/
    val result: DataFrame = dataframe.filter(col("IdentificationClient") =!= "1")

    /** Show the result in our terminal **/
    result.show()

    /** whrite the result in the new csv file **/
    result.write.mode("overwrite").csv("hdfs://172.31.250.9:9000/user/namenode/complianceRGPDMS")
  }

}
