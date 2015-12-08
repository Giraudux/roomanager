package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait AdresseDao {

  def get(p: (AdresseBean) => Boolean): Option[AdresseBean]

  def getAll(p: (AdresseBean) => Boolean): Traversable[AdresseBean]

  def insert(adresse: AdresseBean): AdresseBean

  def update(adresse: AdresseBean): AdresseBean

  def delete(p: (AdresseBean) => Boolean): Unit

  def deleteAll(p: (AdresseBean) => Boolean): Unit
}
