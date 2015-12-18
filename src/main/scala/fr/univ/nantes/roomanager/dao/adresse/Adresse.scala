package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class Adresse(val id: Int, adresseBean: AdresseBean) extends AdresseBean(adresseBean.getAdresse, adresseBean.getCode, adresseBean.getVille) {
  override def getId(): Int = id

  def uniqueConstraint(other: Any): Boolean = other match {
    case that: AdresseBean =>
      other.isInstanceOf[AdresseBean] &&
        false
    case _ => false
  }
}
