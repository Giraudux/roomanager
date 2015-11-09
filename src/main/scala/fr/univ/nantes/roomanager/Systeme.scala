
package fr.univ.nantes.roomanager

class Systeme {
  var batiments = Set[Batiment]()
  var typeSalle = Set("Cuisine", "Toilettes", "Salon")
  var typeMateriel = Set("Tableau", "RetroProjecteur", "MoyensVideo")

  def reserverSalle() = false

  def consulterReservations() = false

  def annulerReservation() = false

  def ajouterSalle(s: Salle) = {
    val b = rechercherBatiment(s.no_bat)
    b.get.salles += s
  }

  def rechercherSalle(b: Batiment, f: Salle => Boolean) = {
    val bat = rechercherBatiment(b.no_bat)
    bat.get.salles.filter(f)
  }

  def ajouterMaterielSalle(s: Salle, m: Materiel) = s.materiels += m

  def supprimerMaterielSalle(s: Salle, m: Materiel) = s.materiels -= m

  def supprimerSalle(s: Salle) = rechercherBatiment(s.no_bat).get.salles -= s

  def ajouterBatiment(b: Batiment) = batiments += b

  def rechercherBatiment(no_bat: Int) = batiments.find((b: Batiment) => b.no_bat == no_bat)

  def modiferBatiment(no_bat: Int, nom: String, adresse: Adresse) = {
    val b = rechercherBatiment(no_bat)
    b.get.nom = nom
    b.get.adresse = adresse
  }

  def supprimerBatiment(no_bat: Int) = batiments.filterNot((b: Batiment) => b.no_bat == no_bat)

  def ajouterTypeSalle(s: String) = typeSalle += s

  def supprimerTypeSalle(s: String) = typeSalle -= s

  //if type dosnt exist?
  def consulterTypeSalle() = typeSalle


}
