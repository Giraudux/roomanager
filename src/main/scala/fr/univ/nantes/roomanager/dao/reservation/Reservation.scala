package fr.univ.nantes.roomanager.dao.reservation

import fr.univ.nantes.roomanager.bean.ReservationBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
class Reservation(val id: Int, reservationBean: ReservationBean) extends ReservationBean(reservationBean.getId_salle, reservationBean.getId_demandeur, reservationBean.getId_typeManifestation, reservationBean.getId_typeDuree, reservationBean.getDateResa) {
  override def getId(): Int = id

  override def equals(other: Any): Boolean = other match {
    case that: ReservationBean =>
      other.isInstanceOf[ReservationBean] &&
        getId == that.getId
    case _ => false
  }

  def equalsUnique(other: Any): Boolean = other match {
    case that: ReservationBean =>
      other.isInstanceOf[ReservationBean] &&
        getId_salle == that.getId_salle &&
        getId_demandeur == that.getId_demandeur &&
        getId_typeManifestation == that.getId_typeManifestation &&
        getId_typeDuree == that.getId_typeDuree &&
        getDateResa == that.getDateResa
    case _ => false
  }
}
