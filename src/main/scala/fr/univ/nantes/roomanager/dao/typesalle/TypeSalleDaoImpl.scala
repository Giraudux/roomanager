package fr.univ.nantes.roomanager.dao.typesalle

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeSalleDaoImpl extends TypeSalleDao {
  private var increment: Int = 0
  private var typesSalle: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = typesSalle.find((typeSalle: TarifBean) => typeSalle.getId() == id).get

  override def update(typeSalle: TarifBean): Unit = if (typesSalle.contains(typeSalle)) typesSalle += typeSalle else throw new Exception()

  override def delete(typeSalle: TarifBean): Unit = typesSalle -= typeSalle

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = typesSalle.filter(predicate)

  override def create(typeSalle: TarifBean): TarifBean = {
    var newTypeSalle: TypeSalle = new TypeSalle(increment, typeSalle)
    if (typesSalle.exists((other: TarifBean) => newTypeSalle.uniqueConstraint(other)))
      throw new Exception()
    typesSalle += newTypeSalle
    increment += 1
    newTypeSalle
  }

  override def getAll(): Traversable[TarifBean] = typesSalle
}
