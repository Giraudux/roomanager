package fr.univ.nantes.roomanager.dao.batiment

import fr.univ.nantes.roomanager.bean.BatimentBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class BatimentDaoImpl extends BatimentDao {
  private var increment: Int = 0
  private var batiments: Set[BatimentBean] = Set()

  override def get(id: Int): BatimentBean = batiments.find((batiment: BatimentBean) => batiment.getId() == id).get

  override def update(batiment: BatimentBean): Unit = if (batiments.contains(batiment)) batiments += batiment else throw new Exception()

  override def delete(batiment: BatimentBean): Unit = batiments -= batiment

  override def find(predicate: (BatimentBean) => Boolean): Traversable[BatimentBean] = batiments.filter(predicate)

  override def create(batiment: BatimentBean): BatimentBean = {
    var newBatiment: Batiment = new Batiment(increment, batiment)
    if (batiments.exists((other: BatimentBean) => newBatiment.uniqueConstraint(other)))
      throw new Exception()
    batiments += newBatiment
    increment += 1
    newBatiment
  }

  override def getAll(): Traversable[BatimentBean] = batiments
}
