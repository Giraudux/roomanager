package fr.univ.nantes.roomanager.dao.typeorigine

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeOrigineDaoImpl extends TypeOrigineDao {
  private var increment: Int = 0
  private var typesOrigine: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = {
    val typeOrigine: TarifBean = typesOrigine.find((typeOrigine: TarifBean) => typeOrigine.getId() == id).get
    new TypeOrigine(typeOrigine.getId(), typeOrigine)
  }

  override def update(typeOrigine: TarifBean): Unit = {
    if (typesOrigine.contains(typeOrigine)) {
      var newTypeOrigine: TypeOrigine = new TypeOrigine(typeOrigine.getId(), typeOrigine)
      if (typesOrigine.exists((other: TarifBean) => newTypeOrigine.uniqueConstraint(other)))
        typesOrigine += newTypeOrigine
    }
    else
      throw new Exception()
  }

  override def delete(typeOrigine: TarifBean): Unit = typesOrigine -= typeOrigine

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = {
    var retTypeOrigines: Set[TarifBean] = Set()
    typesOrigine.filter(predicate).foreach((typeOrigine: TarifBean) => retTypeOrigines += new TypeOrigine(typeOrigine.getId(), typeOrigine))
    retTypeOrigines
  }

  override def create(typeOrigine: TarifBean): TarifBean = {
    var newTypeOrigine: TypeOrigine = new TypeOrigine(increment, typeOrigine)
    if (typesOrigine.exists((other: TarifBean) => newTypeOrigine.uniqueConstraint(other)))
      throw new Exception()
    typesOrigine += newTypeOrigine
    increment += 1
    new TypeOrigine(newTypeOrigine.getId(), newTypeOrigine)
  }

  override def getAll(): Traversable[TarifBean] = {
    var retTypeOrigines: Set[TarifBean] = Set()
    typesOrigine.foreach((typeOrigine: TarifBean) => retTypeOrigines += new TypeOrigine(typeOrigine.getId(), typeOrigine))
    retTypeOrigines
  }
}
