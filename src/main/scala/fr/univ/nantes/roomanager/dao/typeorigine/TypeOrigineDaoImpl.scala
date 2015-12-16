package fr.univ.nantes.roomanager.dao.typeorigine

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeOrigineDaoImpl extends TypeOrigineDao {
  private var increment: Int = 0
  private var typesOrigine: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = typesOrigine.find((typeOrigine: TarifBean) => typeOrigine.getId() == id).get

  override def update(typeOrigine: TarifBean): Unit = if (typesOrigine.contains(typeOrigine)) typesOrigine += typeOrigine else throw new Exception()

  override def delete(typeOrigine: TarifBean): Unit = typesOrigine -= typeOrigine

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = typesOrigine.filter(predicate)

  override def create(typeOrigine: TarifBean): TarifBean = {
    var newTypeOrigine: TypeOrigine = new TypeOrigine(increment, typeOrigine)
    if (typesOrigine.exists((other: TarifBean) => newTypeOrigine.uniqueConstraint(other)))
      throw new Exception()
    typesOrigine += newTypeOrigine
    increment += 1
    newTypeOrigine
  }

  override def getAll(): Traversable[TarifBean] = typesOrigine
}
