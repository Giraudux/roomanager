package fr.univ.nantes.roomanager.bean

import scala.beans.BeanProperty

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class SalleBean(@BeanProperty var id_batiment: Int,
                @BeanProperty var id_typeSalle: Int,
                @BeanProperty var etage: Int,
                @BeanProperty var superficie: Int) extends BaseBean {}
