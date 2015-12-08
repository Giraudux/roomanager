package fr.univ.nantes.roomanager.bean

import scala.beans.BeanProperty

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class AdresseBean(@BeanProperty var adresse: String,
                  @BeanProperty var code: String,
                  @BeanProperty var ville: String) extends BaseBean {}
