package fr.univ.nantes.roomanager.bean

import java.util.Calendar

import scala.beans.BeanProperty

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class ReservationBean(@BeanProperty var id_salle: Int,
                      @BeanProperty var id_demandeur: Int,
                      @BeanProperty var id_typeManifestation: Int,
                      @BeanProperty var id_typeDuree: Int,
                      @BeanProperty var dateResa: Calendar) extends BaseBean {}
