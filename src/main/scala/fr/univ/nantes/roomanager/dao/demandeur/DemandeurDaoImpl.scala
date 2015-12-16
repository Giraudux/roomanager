package fr.univ.nantes.roomanager.dao.demandeur

import fr.univ.nantes.roomanager.bean.DemandeurBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class DemandeurDaoImpl extends DemandeurDao {
  private var increment: Int = 0
  private var demandeurs: Set[DemandeurBean] = Set()

  override def get(id: Int): DemandeurBean = demandeurs.find((demandeur: DemandeurBean) => demandeur.getId() == id).get

  override def update(demandeur: DemandeurBean): Unit = if (demandeurs.contains(demandeur)) demandeurs += demandeur else throw new Exception()

  override def delete(demandeur: DemandeurBean): Unit = demandeurs -= demandeur

  override def find(predicate: (DemandeurBean) => Boolean): Traversable[DemandeurBean] = demandeurs.filter(predicate)

  override def create(demandeur: DemandeurBean): DemandeurBean = {
    var newDemandeur: Demandeur = new Demandeur(increment, demandeur)
    if (demandeurs.exists((other: DemandeurBean) => newDemandeur.uniqueConstraint(other)))
      throw new Exception()
    demandeurs += newDemandeur
    increment += 1
    newDemandeur
  }

  override def getAll(): Traversable[DemandeurBean] = demandeurs
}
