package fr.univ.nantes.roomanager.dao.typemanifestation

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeManifestationDaoImpl extends TypeManifestationDao {
  private var increment: Int = 0
  private var typesManifestation: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = typesManifestation.find((typeManifestation: TarifBean) => typeManifestation.getId() == id).get

  override def update(typeManifestation: TarifBean): Unit = if (typesManifestation.contains(typeManifestation)) typesManifestation += typeManifestation else throw new Exception()

  override def delete(typeManifestation: TarifBean): Unit = typesManifestation -= typeManifestation

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = typesManifestation.filter(predicate)

  override def create(typeManifestation: TarifBean): TarifBean = {
    var newTypeManifestation: TypeManifestation = new TypeManifestation(increment, typeManifestation)
    if (typesManifestation.exists((other: TarifBean) => newTypeManifestation.uniqueConstraint(other)))
      throw new Exception()
    typesManifestation += newTypeManifestation
    increment += 1
    newTypeManifestation
  }

  override def getAll(): Traversable[TarifBean] = typesManifestation
}
