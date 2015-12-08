package fr.univ.nantes.roomanager.bean

import scala.beans.BeanProperty

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class TarifBean(@BeanProperty var libelle: String,
                @BeanProperty var tarifSup: Int,
                @BeanProperty var tarifCoef: Int) extends BaseBean {}
