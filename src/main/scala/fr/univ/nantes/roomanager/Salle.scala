
package fr.univ.nantes.roomanager


class Salle(var no_etage: Int,
            var no_salle: Int,
            var no_bat: Int,
            var superficie: Int,
            var type_sal: String) {
  var materiels: Set[Materiel] = Set() // fixe
}
