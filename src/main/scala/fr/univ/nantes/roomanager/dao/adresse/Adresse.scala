package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Adresse(val id: Int, adresseBean: AdresseBean) extends AdresseBean(adresseBean.getAdresse(), adresseBean.getCode(), adresseBean.getVille()) {
  override def getId(): Int = id

  def canEqual(other: Any): Boolean = other.isInstanceOf[AdresseBean]

  override def equals(other: Any): Boolean = other match {
    case that: Adresse =>
      (that canEqual this) &&
        getId() == that.getId()
    case _ => false
  }

  def equalsUnique(other: Any): Boolean = other match {
    case that: Adresse =>
      (that canEqual this) &&
        getAdresse() == that.getAdresse() &&
        getCode() == that.getCode() &&
        getVille() == that.getVille()
    case _ => false
  }
}
