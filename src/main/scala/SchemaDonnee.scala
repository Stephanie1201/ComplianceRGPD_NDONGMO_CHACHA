package complianceGRPD

import org.apache.spark.sql.types.{DateType, LongType, StringType, StructField, StructType}

object SchemaDonnee{
lazy val schema =
  StructType(
    Seq(
      StructField("IdentifiantClient", LongType,false),
      StructField("Nom", StringType,true),
      StructField("Prenom", StringType,true),
      StructField("Adresse", StringType,true),
      StructField("DateDeSouscription", DateType,true)

    )
  )
}
