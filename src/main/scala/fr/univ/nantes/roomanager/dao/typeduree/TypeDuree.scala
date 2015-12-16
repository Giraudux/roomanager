package fr.univ.nantes.roomanager.dao.typeduree

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeDuree(val id: Int, typeDuree: TarifBean) extends TarifBean(typeDuree.getLibelle, typeDuree.getTarifSup, typeDuree.getTarifCoef) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: TarifBean =>
      other.isInstanceOf[TarifBean] &&
        getLibelle == that.getLibelle
    case _ => false
  }
}
