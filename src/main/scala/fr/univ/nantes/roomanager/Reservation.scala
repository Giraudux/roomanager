/** Class Reservation
  *
  * Classe qui represente une Reservation
  */
package fr.univ.nantes.roomanager

import java.util.Date

class Reservation(var ref_resa: Int,
                  var date_resa: Date,
                  var montant: Float,
                  var salle: Salle,
                  var manifestation: String,
                  var duree: String) {
  var materiels: Set[Materiel] = Set() // mobile
}
