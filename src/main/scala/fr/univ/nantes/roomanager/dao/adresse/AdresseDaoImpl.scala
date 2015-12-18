package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class AdresseDaoImpl extends AdresseDao {
  private var increment: Int = 0
  private var adresses: Set[AdresseBean] = Set()

  override def get(id: Int): AdresseBean = {
    val adresse: AdresseBean = adresses.find((adresse: AdresseBean) => adresse.getId() == id).get
    new Adresse(adresse.getId(), adresse)
  }

  override def update(adresse: AdresseBean): Unit = {
    if (adresses.contains(adresse)) {
      var newAdresse: Adresse = new Adresse(adresse.getId(), adresse)
      if (adresses.exists((other: AdresseBean) => newAdresse.uniqueConstraint(other)))
        adresses += newAdresse
    }
    else
      throw new Exception()
  }

  override def delete(adresse: AdresseBean): Unit = adresses -= adresse

  override def find(predicate: (AdresseBean) => Boolean): Traversable[AdresseBean] = {
    var retAdresses: Set[AdresseBean] = Set()
    adresses.filter(predicate).foreach((adresse: AdresseBean) => retAdresses += new Adresse(adresse.getId(), adresse))
    retAdresses
  }

  override def create(adresse: AdresseBean): AdresseBean = {
    var newAdresse: Adresse = new Adresse(increment, adresse)
    if (adresses.exists((other: AdresseBean) => newAdresse.uniqueConstraint(other)))
      throw new Exception()
    adresses += newAdresse
    increment += 1
    new Adresse(newAdresse.getId(), newAdresse)
  }

  override def getAll(): Traversable[AdresseBean] = {
    var retAdresses: Set[AdresseBean] = Set()
    adresses.foreach((adresse: AdresseBean) => retAdresses += new Adresse(adresse.getId(), adresse))
    retAdresses
  }
}
