package fr.univ.nantes.roomanager.dao.adresse

import fr.univ.nantes.roomanager.bean.AdresseBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Adresse(val id: Int, adresseBean: AdresseBean) extends AdresseBean(adresseBean.adresse, adresseBean.code, adresseBean.ville) {

  override def getId(): Int = id
}
