package fr.univ.nantes.roomanager.dao.typemanifestation

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class TypeManifestationDaoImpl extends TypeManifestationDao {
  private var increment: Int = 0
  private var typesManifestation: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = {
    val typeManifestation: TarifBean = typesManifestation.find((typeManifestation: TarifBean) => typeManifestation.getId() == id).get
    new TypeManifestation(typeManifestation.getId(), typeManifestation)
  }

  override def update(typeManifestation: TarifBean): Unit = {
    if (typesManifestation.contains(typeManifestation)) {
      var newTypeManifestation: TypeManifestation = new TypeManifestation(typeManifestation.getId(), typeManifestation)
      if (!typesManifestation.exists((other: TarifBean) => newTypeManifestation.uniqueConstraint(other)))
        typesManifestation += newTypeManifestation
      else
        throw new Exception()
    }
    else
      throw new Exception()
  }

  override def delete(typeManifestation: TarifBean): Unit = typesManifestation -= typeManifestation

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = {
    var retTypeManifestations: Set[TarifBean] = Set()
    typesManifestation.filter(predicate).foreach((typeManifestation: TarifBean) => retTypeManifestations += new TypeManifestation(typeManifestation.getId(), typeManifestation))
    retTypeManifestations
  }

  override def create(typeManifestation: TarifBean): TarifBean = {
    var newTypeManifestation: TypeManifestation = new TypeManifestation(increment, typeManifestation)
    if (typesManifestation.exists((other: TarifBean) => newTypeManifestation.uniqueConstraint(other)))
      throw new Exception()
    typesManifestation += newTypeManifestation
    increment += 1
    new TypeManifestation(newTypeManifestation.getId(), newTypeManifestation)
  }

  override def getAll(): Traversable[TarifBean] = {
    var retTypeManifestations: Set[TarifBean] = Set()
    typesManifestation.foreach((typeManifestation: TarifBean) => retTypeManifestations += new TypeManifestation(typeManifestation.getId(), typeManifestation))
    retTypeManifestations
  }
}
