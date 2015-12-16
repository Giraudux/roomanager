package fr.univ.nantes.roomanager.dao.materiel

import fr.univ.nantes.roomanager.bean.MaterielBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class MaterielDaoImpl extends MaterielDao {
  private var increment: Int = 0
  private var materiels: Set[MaterielBean] = Set()

  override def get(id: Int): MaterielBean = materiels.find((materiel: MaterielBean) => materiel.getId() == id).get

  override def update(materiel: MaterielBean): Unit = if (materiels.contains(materiel)) materiels += materiel else throw new Exception()

  override def delete(materiel: MaterielBean): Unit = materiels -= materiel

  override def find(predicate: (MaterielBean) => Boolean): Traversable[MaterielBean] = materiels.filter(predicate)

  override def create(materiel: MaterielBean): MaterielBean = {
    var newMateriel: Materiel = new Materiel(increment, materiel)
    if (materiels.exists((other: MaterielBean) => newMateriel.uniqueConstraint(other)))
      throw new Exception()
    materiels += newMateriel
    increment += 1
    newMateriel
  }

  override def getAll(): Traversable[MaterielBean] = materiels
}
