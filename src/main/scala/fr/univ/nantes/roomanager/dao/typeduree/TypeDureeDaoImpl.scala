package fr.univ.nantes.roomanager.dao.typeduree

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeDureeDaoImpl extends TypeDureeDao {
  private var increment: Int = 0
  private var typesDuree: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = typesDuree.find((typeDuree: TarifBean) => typeDuree.getId() == id).get

  override def update(typeDuree: TarifBean): Unit = if (typesDuree.contains(typeDuree)) typesDuree += typeDuree else throw new Exception()

  override def delete(typeDuree: TarifBean): Unit = typesDuree -= typeDuree

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = typesDuree.filter(predicate)

  override def create(typeDuree: TarifBean): TarifBean = {
    var newTypeDuree: TypeDuree = new TypeDuree(increment, typeDuree)
    if (typesDuree.exists((other: TarifBean) => newTypeDuree.uniqueConstraint(other)))
      throw new Exception()
    typesDuree += newTypeDuree
    increment += 1
    newTypeDuree
  }

  override def getAll(): Traversable[TarifBean] = typesDuree
}
