package fr.univ.nantes.roomanager.dao.reservation

import fr.univ.nantes.roomanager.bean.ReservationBean

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait ReservationDao {
    def get(p: (ReservationBean) => Boolean): Option[ReservationBean]

    def getAll(p: (ReservationBean) => Boolean): Traversable[ReservationBean]

    def insert(adresse: ReservationBean): ReservationBean

    def update(adresse: ReservationBean): ReservationBean

    def upsert(adresse: ReservationBean): ReservationBean

    def delete(p: (ReservationBean) => Boolean): Unit

    def deleteAll(p: (ReservationBean) => Boolean): Unit
}
