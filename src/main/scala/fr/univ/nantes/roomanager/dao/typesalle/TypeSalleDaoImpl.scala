package fr.univ.nantes.roomanager.dao.typesalle

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class TypeSalleDaoImpl extends TypeSalleDao {
  private var increment: Int = 0
  private var typesSalle: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = {
    val typeSalle: TarifBean = typesSalle.find((typeSalle: TarifBean) => typeSalle.getId() == id).get
    new TypeSalle(typeSalle.getId(), typeSalle)
  }

  override def update(typeSalle: TarifBean): Unit = {
    if (typesSalle.contains(typeSalle)) {
      var newTypeSalle: TypeSalle = new TypeSalle(typeSalle.getId(), typeSalle)
      if (typesSalle.exists((other: TarifBean) => newTypeSalle.uniqueConstraint(other)))
        typesSalle += newTypeSalle
    }
    else
      throw new Exception()
  }

  override def delete(typeSalle: TarifBean): Unit = typesSalle -= typeSalle

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = {
    var retTypeSalles: Set[TarifBean] = Set()
    typesSalle.filter(predicate).foreach((typeSalle: TarifBean) => retTypeSalles += new TypeSalle(typeSalle.getId(), typeSalle))
    retTypeSalles
  }

  override def create(typeSalle: TarifBean): TarifBean = {
    var newTypeSalle: TypeSalle = new TypeSalle(increment, typeSalle)
    if (typesSalle.exists((other: TarifBean) => newTypeSalle.uniqueConstraint(other)))
      throw new Exception()
    typesSalle += newTypeSalle
    increment += 1
    new TypeSalle(newTypeSalle.getId(), newTypeSalle)
  }

  override def getAll(): Traversable[TarifBean] = {
    var retTypeSalles: Set[TarifBean] = Set()
    typesSalle.foreach((typeSalle: TarifBean) => retTypeSalles += new TypeSalle(typeSalle.getId(), typeSalle))
    retTypeSalles
  }
}
