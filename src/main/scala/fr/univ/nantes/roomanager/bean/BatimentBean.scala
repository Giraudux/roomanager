package fr.univ.nantes.roomanager.bean

import scala.beans.BeanProperty

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class BatimentBean(@BeanProperty var id_adresse: Int,
                   @BeanProperty var nom: String) extends BaseBean {}
