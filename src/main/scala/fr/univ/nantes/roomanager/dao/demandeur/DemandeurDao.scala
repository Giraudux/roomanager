package fr.univ.nantes.roomanager.dao.demandeur

import fr.univ.nantes.roomanager.bean.DemandeurBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait DemandeurDao {
  def get(p: (DemandeurBean) => Boolean): Option[DemandeurBean]

  def getAll(p: (DemandeurBean) => Boolean): Traversable[DemandeurBean]

  def insert(adresse: DemandeurBean): DemandeurBean

  def update(adresse: DemandeurBean): DemandeurBean

  def upsert(adresse: DemandeurBean): DemandeurBean

  def delete(p: (DemandeurBean) => Boolean): Unit

  def deleteAll(p: (DemandeurBean) => Boolean): Unit
}
