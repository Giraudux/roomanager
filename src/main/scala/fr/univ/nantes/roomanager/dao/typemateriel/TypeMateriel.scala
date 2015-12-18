package fr.univ.nantes.roomanager.dao.typemateriel

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class TypeMateriel(val id: Int, typeMateriel: TarifBean) extends TarifBean(typeMateriel.getLibelle, typeMateriel.getTarifBase, typeMateriel.getTarifCoef) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: TarifBean =>
      other.isInstanceOf[TarifBean] &&
        getLibelle == that.getLibelle
    case _ => false
  }
}
