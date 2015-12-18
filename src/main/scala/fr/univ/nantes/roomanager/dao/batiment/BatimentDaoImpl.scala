package fr.univ.nantes.roomanager.dao.batiment

import fr.univ.nantes.roomanager.bean.BatimentBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class BatimentDaoImpl extends BatimentDao {
  private var increment: Int = 0
  private var batiments: Set[BatimentBean] = Set()

  override def get(id: Int): BatimentBean = {
    val batiment: BatimentBean = batiments.find((batiment: BatimentBean) => batiment.getId() == id).get
    new Batiment(batiment.getId(), batiment)
  }

  override def update(batiment: BatimentBean): Unit = {
    if (batiments.contains(batiment)) {
      var newBatiment: Batiment = new Batiment(batiment.getId(), batiment)
      if (!batiments.exists((other: BatimentBean) => newBatiment.uniqueConstraint(other)))
        batiments += newBatiment
      else
        throw new Exception()
    }
    else
      throw new Exception()
  }

  override def delete(batiment: BatimentBean): Unit = batiments -= batiment

  override def find(predicate: (BatimentBean) => Boolean): Traversable[BatimentBean] = {
    var retBatiments: Set[BatimentBean] = Set()
    batiments.filter(predicate).foreach((batiment: BatimentBean) => retBatiments += new Batiment(batiment.getId(), batiment))
    retBatiments
  }

  override def create(batiment: BatimentBean): BatimentBean = {
    var newBatiment: Batiment = new Batiment(increment, batiment)
    if (batiments.exists((other: BatimentBean) => newBatiment.uniqueConstraint(other)))
      throw new Exception()
    batiments += newBatiment
    increment += 1
    new Batiment(newBatiment.getId(), newBatiment)
  }

  override def getAll(): Traversable[BatimentBean] = {
    var retBatiments: Set[BatimentBean] = Set()
    batiments.foreach((batiment: BatimentBean) => retBatiments += new Batiment(batiment.getId(), batiment))
    retBatiments
  }
}
