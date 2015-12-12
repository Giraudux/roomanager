package fr.univ.nantes.roomanager.dao.batiment

import fr.univ.nantes.roomanager.bean.BatimentBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait BatimentDao {
  def get(p: (BatimentBean) => Boolean): Option[BatimentBean]

  def getAll(p: (BatimentBean) => Boolean): Traversable[BatimentBean]

  def insert(adresse: BatimentBean): BatimentBean

  def update(adresse: BatimentBean): BatimentBean

  def upsert(adresse: BatimentBean): BatimentBean

  def delete(p: (BatimentBean) => Boolean): Unit

  def deleteAll(p: (BatimentBean) => Boolean): Unit
}
