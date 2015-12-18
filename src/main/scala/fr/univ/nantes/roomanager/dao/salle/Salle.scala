package fr.univ.nantes.roomanager.dao.salle

import fr.univ.nantes.roomanager.bean.SalleBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class Salle(val id: Int, salleBean: SalleBean) extends SalleBean(salleBean.getId_batiment, salleBean.getId_typeSalle, salleBean.getEtage, salleBean.getSuperficie) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: SalleBean =>
      other.isInstanceOf[SalleBean] &&
        false
    case _ => false
  }
}
