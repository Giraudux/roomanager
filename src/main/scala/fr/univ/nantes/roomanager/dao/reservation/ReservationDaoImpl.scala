package fr.univ.nantes.roomanager.dao.reservation

import fr.univ.nantes.roomanager.bean.ReservationBean

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class ReservationDaoImpl extends ReservationDao {
  private var increment: Int = 0
  private var reservations: Set[ReservationBean] = Set()

  override def get(id: Int): ReservationBean = {
    val reservation: ReservationBean = reservations.find((reservation: ReservationBean) => reservation.getId() == id).get
    new Reservation(reservation.getId(), reservation)
  }

  override def update(reservation: ReservationBean): Unit = {
    if (reservations.contains(reservation)) {
      var newReservation: Reservation = new Reservation(reservation.getId(), reservation)
      if (reservations.exists((other: ReservationBean) => newReservation.uniqueConstraint(other)))
        reservations += newReservation
    }
    else
      throw new Exception()
  }

  override def delete(reservation: ReservationBean): Unit = reservations -= reservation

  override def find(predicate: (ReservationBean) => Boolean): Traversable[ReservationBean] = {
    var retReservations: Set[ReservationBean] = Set()
    reservations.filter(predicate).foreach((reservation: ReservationBean) => retReservations += new Reservation(reservation.getId(), reservation))
    retReservations
  }

  override def create(reservation: ReservationBean): ReservationBean = {
    var newReservation: Reservation = new Reservation(increment, reservation)
    if (reservations.exists((other: ReservationBean) => newReservation.uniqueConstraint(other)))
      throw new Exception()
    reservations += newReservation
    increment += 1
    new Reservation(newReservation.getId(), newReservation)
  }

  override def getAll(): Traversable[ReservationBean] = {
    var retReservations: Set[ReservationBean] = Set()
    reservations.foreach((reservation: ReservationBean) => retReservations += new Reservation(reservation.getId(), reservation))
    retReservations
  }
}
