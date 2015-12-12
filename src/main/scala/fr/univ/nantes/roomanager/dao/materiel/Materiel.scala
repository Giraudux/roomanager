package fr.univ.nantes.roomanager.dao.materiel

import fr.univ.nantes.roomanager.bean.MaterielBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Materiel(val id: Int, materielBean: MaterielBean) extends MaterielBean(materielBean.getId_typeMateriel, materielBean.getId_salle, materielBean.getId_reservation) {
  override def getId(): Int = id

  override def equals(other: Any): Boolean = other match {
    case that: MaterielBean =>
      other.isInstanceOf[MaterielBean] &&
        getId == that.getId
    case _ => false
  }

  def equalsUnique(other: Any): Boolean = other match {
    case that: MaterielBean =>
      other.isInstanceOf[MaterielBean] &&
        getId_typeMateriel == that.getId_typeMateriel &&
        getId_salle == that.getId_salle &&
        getId_reservation == that.getId_reservation
    case _ => false
  }
}
