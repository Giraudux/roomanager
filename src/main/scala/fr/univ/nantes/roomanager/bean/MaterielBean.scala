package fr.univ.nantes.roomanager.bean

import scala.beans.BeanProperty

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class MaterielBean(@BeanProperty var id_typeMateriel: Int,
                   @BeanProperty var id_salle: Int,
                   @BeanProperty var id_reservation: Int) extends BaseBean {}
