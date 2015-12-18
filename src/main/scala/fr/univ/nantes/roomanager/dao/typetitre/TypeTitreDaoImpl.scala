package fr.univ.nantes.roomanager.dao.typetitre

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class TypeTitreDaoImpl extends TypeTitreDao {
  private var increment: Int = 0
  private var typesTitre: Set[TarifBean] = Set()

  override def get(id: Int): TarifBean = {
    val typeTitre: TarifBean = typesTitre.find((typeTitre: TarifBean) => typeTitre.getId() == id).get
    new TypeTitre(typeTitre.getId(), typeTitre)
  }

  override def update(typeTitre: TarifBean): Unit = {
    if (typesTitre.contains(typeTitre)) {
      var newTypeTitre: TypeTitre = new TypeTitre(typeTitre.getId(), typeTitre)
      if (typesTitre.exists((other: TarifBean) => newTypeTitre.uniqueConstraint(other)))
        typesTitre += newTypeTitre
    }
    else
      throw new Exception()
  }

  override def delete(typeTitre: TarifBean): Unit = typesTitre -= typeTitre

  override def find(predicate: (TarifBean) => Boolean): Traversable[TarifBean] = {
    var retTypeTitres: Set[TarifBean] = Set()
    typesTitre.filter(predicate).foreach((typeTitre: TarifBean) => retTypeTitres += new TypeTitre(typeTitre.getId(), typeTitre))
    retTypeTitres
  }

  override def create(typeTitre: TarifBean): TarifBean = {
    var newTypeTitre: TypeTitre = new TypeTitre(increment, typeTitre)
    if (typesTitre.exists((other: TarifBean) => newTypeTitre.uniqueConstraint(other)))
      throw new Exception()
    typesTitre += newTypeTitre
    increment += 1
    new TypeTitre(newTypeTitre.getId(), newTypeTitre)
  }

  override def getAll(): Traversable[TarifBean] = {
    var retTypeTitres: Set[TarifBean] = Set()
    typesTitre.foreach((typeTitre: TarifBean) => retTypeTitres += new TypeTitre(typeTitre.getId(), typeTitre))
    retTypeTitres
  }
}
