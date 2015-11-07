
package fr.univ.nantes.roomanager

class Batiment(var no_bat: Int,
               var nom: String,
               var adresse: Adresse) {
  var salles: Set[Salle] = Set()
}
