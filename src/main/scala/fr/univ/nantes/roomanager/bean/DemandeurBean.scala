package fr.univ.nantes.roomanager.bean

import scala.beans.BeanProperty

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class DemandeurBean(@BeanProperty var id_adresse: Int,
                    @BeanProperty var id_typeOrigine: Int,
                    @BeanProperty var id_typeTitre: Int,
                    @BeanProperty var nom: String) extends BaseBean {}
