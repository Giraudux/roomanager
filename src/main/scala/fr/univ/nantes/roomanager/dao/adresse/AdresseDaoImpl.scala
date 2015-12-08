package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class AdresseDaoImpl extends AdresseDao {
  private var increment: Int = 0
  private var adresses: Set[AdresseBean] = Set()

  override def get(p: (AdresseBean) => Boolean): Option[AdresseBean] = adresses.find(p)

  override def getAll(p: (AdresseBean) => Boolean): Traversable[AdresseBean] = adresses.filter(p)

  override def insert(adresse: AdresseBean): AdresseBean = {
    var newAdresse: Adresse = new Adresse(increment, adresse)
    adresses += newAdresse
    increment += 1
    newAdresse
  }

  override def update(adresse: AdresseBean): Unit = if (adresses.contains(adresse)) adresses += adresse

  override def delete(p: (AdresseBean) => Boolean): Unit = adresses.find(p).foreach((adresse: AdresseBean) => {
    adresses -= adresse
  })

  override def deleteAll(p: (AdresseBean) => Boolean): Unit = adresses.filter(p).foreach((adresse: AdresseBean) => {
    adresses -= adresse
  })
}
