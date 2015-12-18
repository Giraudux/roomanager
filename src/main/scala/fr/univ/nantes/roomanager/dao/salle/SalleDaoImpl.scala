package fr.univ.nantes.roomanager.dao.salle

import fr.univ.nantes.roomanager.bean.SalleBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class SalleDaoImpl extends SalleDao {
  private var increment: Int = 0
  private var salles: Set[SalleBean] = Set()

  override def get(id: Int): SalleBean = {
    val salle: SalleBean = salles.find((salle: SalleBean) => salle.getId() == id).get
    new Salle(salle.getId(), salle)
  }

  override def update(salle: SalleBean): Unit = {
    if (salles.contains(salle)) {
      var newSalle: Salle = new Salle(salle.getId(), salle)
      if (salles.exists((other: SalleBean) => newSalle.uniqueConstraint(other)))
        salles += newSalle
    }
    else
      throw new Exception()
  }

  override def delete(salle: SalleBean): Unit = salles -= salle

  override def find(predicate: (SalleBean) => Boolean): Traversable[SalleBean] = {
    var retSalles: Set[SalleBean] = Set()
    salles.filter(predicate).foreach((salle: SalleBean) => retSalles += new Salle(salle.getId(), salle))
    retSalles
  }

  override def create(salle: SalleBean): SalleBean = {
    var newSalle: Salle = new Salle(increment, salle)
    if (salles.exists((other: SalleBean) => newSalle.uniqueConstraint(other)))
      throw new Exception()
    salles += newSalle
    increment += 1
    new Salle(newSalle.getId(), newSalle)
  }

  override def getAll(): Traversable[SalleBean] = {
    var retSalles: Set[SalleBean] = Set()
    salles.foreach((salle: SalleBean) => retSalles += new Salle(salle.getId(), salle))
    retSalles
  }
}
