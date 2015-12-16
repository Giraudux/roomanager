package fr.univ.nantes.roomanager.dao.typetitre

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeTitreDaoImpl extends TypeTitreDao {
  private var increment: Int = 0
  private var typesTitre: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = typesTitre.find((typeTitre: TarifBean) => typeTitre.getId() == id).get

  override def update(typeTitre: TarifBean): Unit = if (typesTitre.contains(typeTitre)) typesTitre += typeTitre else throw new Exception()

  override def delete(typeTitre: TarifBean): Unit = typesTitre -= typeTitre

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = typesTitre.filter(predicate)

  override def create(typeTitre: TarifBean): TarifBean = {
    var newTypeTitre: TypeTitre = new TypeTitre(increment, typeTitre)
    if (typesTitre.exists((other: TarifBean) => newTypeTitre.uniqueConstraint(other)))
      throw new Exception()
    typesTitre += newTypeTitre
    increment += 1
    newTypeTitre
  }

  override def getAll(): Traversable[TarifBean] = typesTitre
}
