package fr.univ.nantes.roomanager.dao.typemateriel

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeMaterielDaoImpl extends TypeMaterielDao {
  private var increment: Int = 0
  private var typesMateriel: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = typesMateriel.find((typeMateriel: TarifBean) => typeMateriel.getId() == id).get

  override def update(typeMateriel: TarifBean): Unit = if (typesMateriel.contains(typeMateriel)) typesMateriel += typeMateriel else throw new Exception()

  override def delete(typeMateriel: TarifBean): Unit = typesMateriel -= typeMateriel

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = typesMateriel.filter(predicate)

  override def create(typeMateriel: TarifBean): TarifBean = {
    var newTypeMateriel: TypeMateriel = new TypeMateriel(increment, typeMateriel)
    if (typesMateriel.exists((other: TarifBean) => newTypeMateriel.uniqueConstraint(other)))
      throw new Exception()
    typesMateriel += newTypeMateriel
    increment += 1
    newTypeMateriel
  }

  override def getAll(): Traversable[TarifBean] = typesMateriel
}
