package complianceGRPD
package Service2Hacher

import complianceGRPD.SchemaDonnee
import complianceGRPD.Service1Delete.schema
import javafx.scene.chart.PieChart.Data
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

import java.util.UUID

object Hacher {

  import org.apache.spark.sql.functions._

  def hacher(): Unit = {

    val sparkSession: SparkSession = SparkSession.builder().master("local[*]").getOrCreate()

    /** read of csv file * */
    val dataframe: org.apache.spark.sql.DataFrame = sparkSession.read.option("header", true)
      .csv("data/steph.csv")

    val appelShema = sparkSession.createDataFrame(dataframe.rdd, SchemaDonnee.schema)

    /** Show the content of file.csv in our terminal * */
    val datasfile = dataframe.toDF("IdentificationClient", ",Nom", "Prenom", "Adresse", "DateDeSouscription")
    datasfile.show()

    import java.util.UUID
    def MyUuidApp {
        val uuid = UUID.randomUUID
        val uuidAsString = uuid.toString
        System.out.println("Your UUID is: " + uuidAsString)
      }
    }



      val dfWithUuid = datasfile.withColumn("Nom" )
      dfWithUuid.show(true)





//    def hashage(sparkSession: SparkSession, data: Dataset[schema], id: Long): Dataset[schema] = {
//      var dset = data
//      dset =
//
//    }





    //    /** Hash data from our file and display the result in the terminal **/
    //    val generateUUID = udf(() => UUID.randomUUID().toString)
    //    val dataHache = datasfile.withColumn("UUID", generateUUID())
    //        dataHache.show()
    //
    //    /** Other chopping methods **/
    //     dataframe.toDF("IdentificationClient",",Nom","Prenom","Adresse","DateDeSouscription")
    //      .withColumn("uuid", expr("uuid()"))
    //      .show(false)


  }

}
