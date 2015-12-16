package fr.univ.nantes.roomanager.dao.salle

import fr.univ.nantes.roomanager.bean.SalleBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class SalleDaoImpl extends SalleDao {
  private var increment: Int = 0
  private var salles: Set[SalleBean] = Set()

  override def get(id: Int): SalleBean = salles.find((salle: SalleBean) => salle.getId() == id).get

  override def update(salle: SalleBean): Unit = if (salles.contains(salle)) salles += salle else throw new Exception()

  override def delete(salle: SalleBean): Unit = salles -= salle

  override def find(predicate: (SalleBean) => Boolean): Traversable[SalleBean] = salles.filter(predicate)

  override def create(salle: SalleBean): SalleBean = {
    var newSalle: Salle = new Salle(increment, salle)
    if (salles.exists((other: SalleBean) => newSalle.uniqueConstraint(other)))
      throw new Exception()
    salles += newSalle
    increment += 1
    newSalle
  }

  override def getAll(): Traversable[SalleBean] = salles
}
