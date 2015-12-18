package fr.univ.nantes.roomanager.dao.typeorigine

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class TypeOrigine(val id: Int, typeOrigine: TarifBean) extends TarifBean(typeOrigine.getLibelle, typeOrigine.getTarifBase, typeOrigine.getTarifCoef) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: TarifBean =>
      other.isInstanceOf[TarifBean] &&
        getLibelle == that.getLibelle
    case _ => false
  }
}
