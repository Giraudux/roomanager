package fr.univ.nantes.roomanager.dao.adresse

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class AdresseDaoImpl extends AdresseDao {
  var adresses: Set[Adresse] = Set()

  override def getAdresse(p: (Adresse) => Boolean): Option[Adresse] = adresses.find(p)

  override def addAdresse(adresse: Adresse): Unit = adresses += adresse

  override def updateAdresse(adresse: Adresse): Unit = {
    deleteAdresse(adresse)
    addAdresse(adresse)
  }

  override def getAdresses(p: (Adresse) => Boolean): Set[Adresse] = adresses.filter(p)

  override def deleteAdresse(adresse: Adresse): Unit = adresses -= adresse
}
