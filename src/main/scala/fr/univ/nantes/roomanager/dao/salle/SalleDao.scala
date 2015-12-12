package fr.univ.nantes.roomanager.dao.salle

import fr.univ.nantes.roomanager.bean.SalleBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait SalleDao {
  def get(p: (SalleBean) => Boolean): Option[SalleBean]

  def getAll(p: (SalleBean) => Boolean): Traversable[SalleBean]

  def insert(adresse: SalleBean): SalleBean

  def update(adresse: SalleBean): SalleBean

  def upsert(adresse: SalleBean): SalleBean

  def delete(p: (SalleBean) => Boolean): Unit

  def deleteAll(p: (SalleBean) => Boolean): Unit
}
