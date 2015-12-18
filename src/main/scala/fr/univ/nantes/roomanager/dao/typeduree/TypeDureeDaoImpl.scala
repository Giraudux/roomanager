package fr.univ.nantes.roomanager.dao.typeduree

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class TypeDureeDaoImpl extends TypeDureeDao {
  private var increment: Int = 0
  private var typesDuree: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = {
    val typeDuree: TarifBean = typesDuree.find((typeDuree: TarifBean) => typeDuree.getId() == id).get
    new TypeDuree(typeDuree.getId(), typeDuree)
  }

  override def update(typeDuree: TarifBean): Unit = {
    if (typesDuree.contains(typeDuree)) {
      var newTypeDuree: TypeDuree = new TypeDuree(typeDuree.getId(), typeDuree)
      if (typesDuree.exists((other: TarifBean) => newTypeDuree.uniqueConstraint(other)))
        typesDuree += newTypeDuree
    }
    else
      throw new Exception()
  }

  override def delete(typeDuree: TarifBean): Unit = typesDuree -= typeDuree

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = {
    var retTypeDurees: Set[TarifBean] = Set()
    typesDuree.filter(predicate).foreach((typeDuree: TarifBean) => retTypeDurees += new TypeDuree(typeDuree.getId(), typeDuree))
    retTypeDurees
  }

  override def create(typeDuree: TarifBean): TarifBean = {
    var newTypeDuree: TypeDuree = new TypeDuree(increment, typeDuree)
    if (typesDuree.exists((other: TarifBean) => newTypeDuree.uniqueConstraint(other)))
      throw new Exception()
    typesDuree += newTypeDuree
    increment += 1
    new TypeDuree(newTypeDuree.getId(), newTypeDuree)
  }

  override def getAll(): Traversable[TarifBean] = {
    var retTypeDurees: Set[TarifBean] = Set()
    typesDuree.foreach((typeDuree: TarifBean) => retTypeDurees += new TypeDuree(typeDuree.getId(), typeDuree))
    retTypeDurees
  }
}
