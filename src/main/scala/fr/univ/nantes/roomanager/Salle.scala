
package fr.univ.nantes.roomanager

import fr.univ.nantes.roomanager.TypeSalle.TypeSalle

class Salle(var no_etage: Int,
            var no_salle: Int,
            var no_bat: Int,
            var superficie: Int,
            var type_sal: TypeSalle) {
  var materiels: Set[Materiel] = Set() // fixe
}
