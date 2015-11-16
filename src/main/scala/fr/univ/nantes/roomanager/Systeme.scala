
package fr.univ.nantes.roomanager

class Systeme {
  // use storage manager interface ?
  var batiments = Set[Batiment]()
  var demandeurs = Set[Demandeur]()
  var typeSalle = Set[String]("Cuisine", "Toilettes", "Salon")
  var typeMateriel = Set[String]("Tableau", "RetroProjecteur", "MoyensVideo")
  var typeManifestation = Set[String]("Reunion", "Banquet", "Spectacle")
  var typeDuree = Set[String]("DemiJournee", "Soiree")
  var typeTitre = Set[String]("Particulier", "Association", "Entreprise")
  var typeOrigine = Set[String]("Resident", "NonResident")

  //@TODO: reservation, suppression (suppression batiment/salle/demandeur => suppression reservation)
  //@TODO: gestion tarifs, planning, facture +=//; faire uml
  //@TODO: tests/docs
   def reserverSalle(demandeur: Demandeur, reservation: Reservation) = false

  def rechercherReservations(predicateDem: Demandeur => Boolean, predicateRes: Reservation => Boolean): Set[Reservation] = {
    var retourRes = Set[Reservation]()
    demandeurs.find(predicateDem).foreach((d: Demandeur) => d.reservations.find(predicateRes).foreach((r: Reservation) => retourRes += r))
    retourRes
  }

  def annulerReservation(predicateDem: Demandeur => Boolean, predicateRes: Reservation => Boolean) = {
    demandeurs.find(predicateDem).foreach((d: Demandeur) => d.reservations.find(predicateRes).foreach((r: Reservation) => d.reservations -= r))
  }

  def ajouterSalle(batiment: Batiment, salle: Salle) = {
    batiment.salles += salle
    batiments += batiment
  }

  def rechercherSalle(predicateBat: Batiment => Boolean, predicateSalle: Salle => Boolean): Set[Salle] = {
    var resSalles = Set[Salle]()
    batiments.find(predicateBat).foreach((b: Batiment) => b.salles.find(predicateSalle).foreach((s: Salle) => resSalles += s))
    resSalles
  }

  def ajouterMaterielSalle(salle: Salle, materiel: Materiel) = salle.materiels += materiel

  def supprimerMaterielSalle(salle: Salle, materiel: Materiel) = salle.materiels -= materiel

  def supprimerSalle(predicateBat: Batiment => Boolean, predicateSalle: Salle => Boolean) = batiments.find(predicateBat).foreach((b: Batiment) => b.salles.find(predicateSalle).foreach((s: Salle) => b.salles -= s))

  def ajouterBatiment(b: Batiment) = batiments += b

  def rechercherBatiment(predicate: Batiment => Boolean) = batiments.find(predicate)

  def modiferBatiment(predicate: Batiment => Boolean, function: Batiment => Unit) = rechercherBatiment(predicate).foreach(function)

  def supprimerBatiment(predicate: Batiment => Boolean) = batiments = batiments.filterNot(predicate)

  def ajouterTypeSalle(s: String) = typeSalle += s

  def supprimerTypeSalle(s: String) = typeSalle -= s

  def consulterTypeSalle() = typeSalle

  def ajouterTypeMateriel(t: String) = typeMateriel += t

  def supprimerTypeMateriel(t: String) = typeMateriel -= t

  def consulterTypeMateriel() = typeMateriel

  def ajouterTypeManifestation(t: String) = typeManifestation += t

  def supprimerTypeManifestation(t: String) = typeManifestation -= t

  def consulterTypeManifestation() = typeManifestation

  def ajouterTypeDuree(t: String) = typeDuree += t

  def supprimerTypeDuree(t: String) = typeDuree -= t

  def consulterTypeDuree() = typeDuree

  def ajouterDemandeur(d: Demandeur) = demandeurs += d

  def rechercherDemandeur(predicate: Demandeur => Boolean) = demandeurs.find(predicate)

  def modiferDemandeur(predicate: Demandeur => Boolean, function: Demandeur => Unit) = rechercherDemandeur(predicate).foreach(function)

  def supprimerDemandeur(predicate: Demandeur => Boolean) = demandeurs = demandeurs.filterNot(predicate)


}
