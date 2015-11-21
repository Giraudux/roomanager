
package scala.fr.univ.nantes.roomanager



class Demandeur(var no_dem: Int, var nom: String, var e: Adresse,
                var origine: String,
                var titre: String) {
  var reservations: Set[Reservation] = Set()
}
