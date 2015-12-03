package fr.univ.nantes.roomanager.dao.adresse

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait AdresseDao {
  def getAdresse(p: (Adresse) => Boolean): Option[Adresse]

  def getAdresses(p: (Adresse) => Boolean): Traversable[Adresse]

  def addAdresse(adresse: Adresse): Unit

  def updateAdresse(adresse: Adresse): Unit

  def deleteAdresse(adresse: Adresse): Unit
}
