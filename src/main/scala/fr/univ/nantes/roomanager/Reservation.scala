package scala.fr.univ.nantes.roomanager

import java.util.Date


class Reservation(var date_resa:Date,
                  var salle: Salle,
                  var manifestation: String,
                  var duree: String) {
  var materiels: Set[Materiel] = Set() // mobile
}
