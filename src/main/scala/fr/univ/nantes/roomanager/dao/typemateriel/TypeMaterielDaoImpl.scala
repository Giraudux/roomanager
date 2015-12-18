package fr.univ.nantes.roomanager.dao.typemateriel

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class TypeMaterielDaoImpl extends TypeMaterielDao {
  private var increment: Int = 0
  private var typesMateriel: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = {
    val typeMateriel: TarifBean = typesMateriel.find((typeMateriel: TarifBean) => typeMateriel.getId() == id).get
    new TypeMateriel(typeMateriel.getId(), typeMateriel)
  }

  override def update(typeMateriel: TarifBean): Unit = {
    if (typesMateriel.contains(typeMateriel)) {
      var newTypeMateriel: TypeMateriel = new TypeMateriel(typeMateriel.getId(), typeMateriel)
      if (!typesMateriel.exists((other: TarifBean) => newTypeMateriel.uniqueConstraint(other)))
        typesMateriel += newTypeMateriel
      else
        throw new Exception()
    }
    else
      throw new Exception()
  }

  override def delete(typeMateriel: TarifBean): Unit = typesMateriel -= typeMateriel

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = {
    var retTypeMateriels: Set[TarifBean] = Set()
    typesMateriel.filter(predicate).foreach((typeMateriel: TarifBean) => retTypeMateriels += new TypeMateriel(typeMateriel.getId(), typeMateriel))
    retTypeMateriels
  }

  override def create(typeMateriel: TarifBean): TarifBean = {
    var newTypeMateriel: TypeMateriel = new TypeMateriel(increment, typeMateriel)
    if (typesMateriel.exists((other: TarifBean) => newTypeMateriel.uniqueConstraint(other)))
      throw new Exception()
    typesMateriel += newTypeMateriel
    increment += 1
    new TypeMateriel(newTypeMateriel.getId(), newTypeMateriel)
  }

  override def getAll(): Traversable[TarifBean] = {
    var retTypeMateriels: Set[TarifBean] = Set()
    typesMateriel.foreach((typeMateriel: TarifBean) => retTypeMateriels += new TypeMateriel(typeMateriel.getId(), typeMateriel))
    retTypeMateriels
  }
}
