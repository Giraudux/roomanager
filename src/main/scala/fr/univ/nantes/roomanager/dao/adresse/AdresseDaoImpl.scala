package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean
import fr.univ.nantes.roomanager.dao.Dao

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class AdresseDaoImpl extends Dao[AdresseBean] {
  private var increment: Int = 0
  private var adresses: Set[AdresseBean] = Set()

  override def get(id: Int): AdresseBean = adresses.find((adresse: AdresseBean) => adresse.getId() == id).get

  override def update(adresse: AdresseBean): Unit = if(adresses.contains(adresse)) adresses += adresse else throw new Exception()

  override def delete(adresse: AdresseBean): Unit = adresses -= adresse

  override def find(predicate: (AdresseBean) => Boolean): Traversable[AdresseBean] = adresses.filter(predicate)

  override def create(adresse: AdresseBean): AdresseBean = ???
}
