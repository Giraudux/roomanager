/** Class Adresse
  *
  * Classe qui represente une adresse
  */
  
package fr.univ.nantes.roomanager

import fr.univ.nantes.roomanager.Origine.Origine
import fr.univ.nantes.roomanager.Titre.Titre

class Demandeur(var no_dem: Int,
                var nom: String,
                var adresse: String,
                var origine: String,
                var titre: Titre) {
  var reservations: Set[Reservation] = Set()
}
