package complianceGRPD
package Service1Delete

import org.apache.hadoop.shaded.org.apache.commons.net.ntp.TimeStamp


case class Client(IdentificationClient: Long, Nom: String, Prenom: String, Adresse: String, DateDeSouscription: TimeStamp)
