package fr.univ.nantes.roomanager.dao.batiment

import fr.univ.nantes.roomanager.bean.{AdresseBean, BatimentBean}

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Batiment(val id: Int, batimentBean: BatimentBean) extends BatimentBean(batimentBean.getId_adresse, batimentBean.getNom) {
  override def getId(): Int = id

  override def equals(other: Any): Boolean = other match {
    case that: BatimentBean =>
      other.isInstanceOf[BatimentBean] &&
        getId == that.getId
    case _ => false
  }

  def equalsUnique(other: Any): Boolean = other match {
    case that: BatimentBean =>
      other.isInstanceOf[BatimentBean] &&
        getId_adresse == that.getId_adresse &&
        getNom == that.getNom
    case _ => false
  }
}
