package fr.univ.nantes.roomanager.dao.typemanifestation

import fr.univ.nantes.roomanager.bean.TarifBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TypeManifestation(val id: Int, typeManifestation: TarifBean) extends TarifBean(typeManifestation.getLibelle, typeManifestation.getTarifBase, typeManifestation.getTarifCoef) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: TarifBean =>
      other.isInstanceOf[TarifBean] &&
        getLibelle == that.getLibelle
    case _ => false
  }
}
