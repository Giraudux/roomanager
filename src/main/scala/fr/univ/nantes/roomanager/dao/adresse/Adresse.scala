package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Adresse(val id: Int, adresseBean: AdresseBean) extends AdresseBean(adresseBean.getAdresse, adresseBean.getCode, adresseBean.getVille) {
  override def getId(): Int = id

  override def equals(other: Any): Boolean = other match {
    case that: AdresseBean =>
      other.isInstanceOf[AdresseBean] &&
        getId == that.getId
    case _ => false
  }

  def equalsUnique(other: Any): Boolean = other match {
    case that: AdresseBean =>
      other.isInstanceOf[AdresseBean] &&
        getAdresse == that.getAdresse &&
        getCode == that.getCode &&
        getVille == that.getVille
    case _ => false
  }
}
