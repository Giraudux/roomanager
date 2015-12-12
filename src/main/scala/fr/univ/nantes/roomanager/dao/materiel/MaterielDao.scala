package fr.univ.nantes.roomanager.dao.materiel

import fr.univ.nantes.roomanager.bean.MaterielBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait MaterielDao {
  def get(p: (MaterielBean) => Boolean): Option[MaterielBean]

  def getAll(p: (MaterielBean) => Boolean): Traversable[MaterielBean]

  def insert(adresse: MaterielBean): MaterielBean

  def update(adresse: MaterielBean): MaterielBean

  def upsert(adresse: MaterielBean): MaterielBean

  def delete(p: (MaterielBean) => Boolean): Unit

  def deleteAll(p: (MaterielBean) => Boolean): Unit
}
