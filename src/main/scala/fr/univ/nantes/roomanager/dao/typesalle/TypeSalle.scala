package fr.univ.nantes.roomanager.dao.typesalle

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeSalle(val id: Int, typeSalle: TarifBean) extends TarifBean(typeSalle.getLibelle, typeSalle.getTarifSup, typeSalle.getTarifCoef) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: TarifBean =>
      other.isInstanceOf[TarifBean] &&
        getLibelle == that.getLibelle
    case _ => false
  }
}
