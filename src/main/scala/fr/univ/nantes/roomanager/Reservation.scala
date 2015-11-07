
package fr.univ.nantes.roomanager

import java.util.Date

import fr.univ.nantes.roomanager.Duree.Duree
import fr.univ.nantes.roomanager.Manifestation.Manifestation

class Reservation(var ref_resa: Int,
                  var date_resa: Date,
                  var montant: Float,
                  var salle: Salle,
                  var manifestation: Manifestation,
                  var duree: Duree) {
  var materiels: Set[Materiel] = Set() // mobile
}
