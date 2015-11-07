
package fr.univ.nantes.roomanager

import fr.univ.nantes.roomanager.Origine.Origine
import fr.univ.nantes.roomanager.Titre.Titre

class Demandeur(var no_dem: Int,
                var nom: String,
                var adresse: Adresse,
                var origine: Origine,
                var titre: Titre) {
  var reservations: Set[Reservation] = Set()
}
