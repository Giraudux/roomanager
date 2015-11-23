package scala.fr.univ.nantes.roomanager

import java.util.Calendar


class Reservation(var date_resa:Calendar,
                  var salle: Salle,
                  var manifestation: String,
                  var duree: String) {
  var materiels: Set[Materiel] = Set() // mobile
}
