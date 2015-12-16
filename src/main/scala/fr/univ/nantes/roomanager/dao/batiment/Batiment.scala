package fr.univ.nantes.roomanager.dao.batiment

import fr.univ.nantes.roomanager.bean.BatimentBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Batiment(val id: Int, batimentBean: BatimentBean) extends BatimentBean(batimentBean.getId_adresse, batimentBean.getNom) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: BatimentBean =>
      other.isInstanceOf[BatimentBean] &&
        getId_adresse == that.getId_adresse &&
        getNom == that.getNom
    case _ => false
  }
}
