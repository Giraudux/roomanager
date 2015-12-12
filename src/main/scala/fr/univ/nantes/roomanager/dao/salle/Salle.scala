package fr.univ.nantes.roomanager.dao.salle

import fr.univ.nantes.roomanager.bean.SalleBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Salle(val id: Int, salleBean: SalleBean) extends SalleBean(salleBean.getId_batiment, salleBean.getId_typeSalle, salleBean.getEtage, salleBean.getSuperficie) {
  override def getId(): Int = id

  override def equals(other: Any): Boolean = other match {
    case that: SalleBean =>
      other.isInstanceOf[SalleBean] &&
        getId == that.getId
    case _ => false
  }

  def equalsUnique(other: Any): Boolean = other match {
    case that: SalleBean =>
      other.isInstanceOf[SalleBean] &&
        false
    case _ => false
  }
}
