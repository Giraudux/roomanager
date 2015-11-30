
package scala.fr.univ.nantes.roomanager



class Salle(var batiment: Batiment,
            var no_etage: Int,
            var no_salle: Int,
            var superficie: Int,
            var type_sal: String,) {
  var materiels: Set[Materiel] = Set() // fixe
}
