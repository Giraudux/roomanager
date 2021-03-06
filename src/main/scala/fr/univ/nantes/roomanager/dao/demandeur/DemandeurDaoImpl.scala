package fr.univ.nantes.roomanager.dao.demandeur

import fr.univ.nantes.roomanager.bean.DemandeurBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class DemandeurDaoImpl extends DemandeurDao {
  private var increment: Int = 0
  private var demandeurs: Set[DemandeurBean] = Set()

  override def get(id: Int): DemandeurBean = {
    val demandeur: DemandeurBean = demandeurs.find((demandeur: DemandeurBean) => demandeur.getId() == id).get
    new Demandeur(demandeur.getId(), demandeur)
  }

  override def update(demandeur: DemandeurBean): Unit = {
    if (demandeurs.contains(demandeur)) {
      var newDemandeur: Demandeur = new Demandeur(demandeur.getId(), demandeur)
      if (!demandeurs.exists((other: DemandeurBean) => newDemandeur.uniqueConstraint(other)))
        demandeurs += newDemandeur
      else
        throw new Exception()
    }
    else
      throw new Exception()
  }

  override def delete(demandeur: DemandeurBean): Unit = demandeurs -= demandeur

  override def find(predicate: (DemandeurBean) => Boolean): Traversable[DemandeurBean] = {
    var retDemandeurs: Set[DemandeurBean] = Set()
    demandeurs.filter(predicate).foreach((demandeur: DemandeurBean) => retDemandeurs += new Demandeur(demandeur.getId(), demandeur))
    retDemandeurs
  }

  override def create(demandeur: DemandeurBean): DemandeurBean = {
    var newDemandeur: Demandeur = new Demandeur(increment, demandeur)
    if (demandeurs.exists((other: DemandeurBean) => newDemandeur.uniqueConstraint(other)))
      throw new Exception()
    demandeurs += newDemandeur
    increment += 1
    new Demandeur(newDemandeur.getId(), newDemandeur)
  }

  override def getAll(): Traversable[DemandeurBean] = {
    var retDemandeurs: Set[DemandeurBean] = Set()
    demandeurs.foreach((demandeur: DemandeurBean) => retDemandeurs += new Demandeur(demandeur.getId(), demandeur))
    retDemandeurs
  }
}
