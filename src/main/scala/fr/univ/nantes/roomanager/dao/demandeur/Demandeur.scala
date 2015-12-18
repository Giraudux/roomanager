package fr.univ.nantes.roomanager.dao.demandeur

import fr.univ.nantes.roomanager.bean.DemandeurBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class Demandeur(val id: Int, demandeurBean: DemandeurBean) extends DemandeurBean(demandeurBean.getId_adresse, demandeurBean.getId_typeOrigine, demandeurBean.getId_typeTitre, demandeurBean.getNom) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: DemandeurBean =>
      other.isInstanceOf[DemandeurBean] &&
        getId_adresse == that.getId_adresse &&
        getNom == that.getNom
    case _ => false
  }
}
