package fr.univ.nantes.roomanager.dao.materiel

import fr.univ.nantes.roomanager.bean.MaterielBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class Materiel(val id: Int, materielBean: MaterielBean) extends MaterielBean(materielBean.getId_typeMateriel, materielBean.getId_salle, materielBean.getId_reservation) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: MaterielBean =>
      other.isInstanceOf[MaterielBean] &&
        false
    case _ => false
  }
}
