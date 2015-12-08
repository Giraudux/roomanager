package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class AdresseDaoImpl extends AdresseDao {
  private var increment: Int = 0
  private var adresses: Set[AdresseBean] = Set()

  override def get(p: (AdresseBean) => Boolean): Option[AdresseBean] = adresses.find(p)

  override def getAll(p: (AdresseBean) => Boolean): Set[AdresseBean] = adresses.filter(p)

  override def insert(adresse: AdresseBean): AdresseBean = {
    var newAdresse: Adresse = new Adresse(increment, adresse)
    if (get((other: AdresseBean) => newAdresse.equalsUnique(other)).isDefined) {
      throw new Exception("unique constraint violated")
    }
    adresses += newAdresse
    increment += 1
    newAdresse
  }

  override def update(adresse: AdresseBean): Unit = {
    if (adresses.contains(adresse)) {
      adresses += adresse
      adresse
    } else {
      throw new Exception("index not found")
    }
  }

  override def upsert(adresse: AdresseBean): AdresseBean = {
    if (adresses.contains(adresse)) {
      adresses += adresse
      adresse
    } else {
      insert(adresse)
    }
  }

  override def delete(p: (AdresseBean) => Boolean): Unit = adresses.find(p).foreach((adresse: AdresseBean) => {
    adresses -= adresse
  })

  override def deleteAll(p: (AdresseBean) => Boolean): Unit = adresses.filter(p).foreach((adresse: AdresseBean) => {
    adresses -= adresse
  })
}
