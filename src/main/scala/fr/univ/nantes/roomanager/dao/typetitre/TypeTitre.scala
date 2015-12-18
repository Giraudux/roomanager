package fr.univ.nantes.roomanager.dao.typetitre

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeTitre(val id: Int, typeTitre: TarifBean) extends TarifBean(typeTitre.getLibelle, typeTitre.getTarifBase, typeTitre.getTarifCoef) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: TarifBean =>
      other.isInstanceOf[TarifBean] &&
        getLibelle == that.getLibelle
    case _ => false
  }
}
