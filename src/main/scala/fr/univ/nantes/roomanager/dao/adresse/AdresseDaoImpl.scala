package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class AdresseDaoImpl extends AdresseDao {
  private var increment: Int = 0
  private var adresses: Set[AdresseBean] = Set()

  override def get(id: Int): AdresseBean = adresses.find((adresse: AdresseBean) => adresse.getId() == id).get

  override def update(adresse: AdresseBean): Unit = if (adresses.contains(adresse)) adresses += adresse else throw new Exception()

  override def delete(adresse: AdresseBean): Unit = adresses -= adresse

  override def find(predicate: (AdresseBean) => Boolean): Traversable[AdresseBean] = adresses.filter(predicate)

  override def create(adresse: AdresseBean): AdresseBean = {
    var newAdresse: Adresse = new Adresse(increment, adresse)
    if (adresses.exists((other: AdresseBean) => newAdresse.uniqueConstraint(other)))
      throw new Exception()
    adresses += newAdresse
    increment += 1
    newAdresse
  }

  override def getAll(): Traversable[AdresseBean] = adresses
}
