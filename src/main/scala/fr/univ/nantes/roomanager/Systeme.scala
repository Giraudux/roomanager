
package fr.univ.nantes.roomanager

import scala.fr.univ.nantes.roomanager._


import java.util.Calendar


class Systeme {  
  // use storage manager interface ?
  var batiments = Set[Batiment]()
  var demandeurs = Set[Demandeur]()

  var typeSalle = Set[String]("Cuisine", "Toilettes", "Salon")
  var typeMateriel = Set[String]("Tableau", "RetroProjecteur", "MoyensVideo")
  var typeManifestation = Set[String]("Reunion", "Banquet", "Spectacle")
  var typeDuree = Set[String]("Matin","Apres-Midi", "Soiree")
  var typeTitre = Set[String]("Particulier", "Association", "Entreprise")
  var typeOrigine = Set[String]("Resident", "NonResident")

  val GF = new GestionFinanciere()

  //@TODO: reservation, suppression (suppression batiment/salle/demandeur => suppression reservation)
  //@TODO: tests/docs
  def reserverSalle(demandeur: Demandeur, reservation: Reservation) = demandeur.reservations += reservation

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

  def supprimerSalle(predicateBat: Batiment => Boolean, predicateSalle: Salle => Boolean) = {

    batiments.find(predicateBat).foreach((b: Batiment) => b.salles.find(predicateSalle).foreach((s: Salle) => b.salles -= s))
  }

  def ajouterBatiment(b: Batiment) = batiments += b

  def rechercherBatiment(predicate: Batiment => Boolean) = batiments.find(predicate)

  def modiferBatiment(predicate: Batiment => Boolean, function: Batiment => Unit) = rechercherBatiment(predicate).foreach(function)

  def supprimerBatiment(predicate: Batiment => Boolean) = batiments = batiments.filterNot(predicate)

  def ajouterTypeSalle(s: String,d:Double) = GF.coutTypeSalle += ((s,d))

  def supprimerTypeSalle(s: String) = GF.coutTypeSalle -= s

  def consulterTypeSalle() = GF.coutTypeSalle

  def ajouterTypeMateriel(t: String,d:Double) = GF.coutTypeMateriel += ((t,d))

  def supprimerTypeMateriel(t: String) = GF.coutTypeMateriel -= t

  def consulterTypeMateriel() = GF.coutTypeMateriel

  def ajouterTypeManifestation(t: String,d:Double) = GF.typeManifestation += ((t,d))

  def supprimerTypeManifestation(t: String) = GF.typeManifestation -= t

  def consulterTypeManifestation() = GF.typeManifestation

  def ajouterTypeDuree(t: String,d:Double) = GF.coutDuree += ((t,d))

  def supprimerTypeDuree(t: String) = GF.coutDuree -= t

  def consulterTypeDuree() = GF.coutDuree

  def ajouterDemandeur(d: Demandeur) = demandeurs += d

  def rechercherDemandeur(predicate: Demandeur => Boolean) = demandeurs.find(predicate)

  def modiferDemandeur(predicate: Demandeur => Boolean, function: Demandeur => Unit) = rechercherDemandeur(predicate).foreach(function)

  def supprimerDemandeur(predicate: Demandeur => Boolean) = {
    demandeurs = demandeurs.filterNot(predicate)
  }

  def factureHebdo(d: Demandeur,numSemaine:Int) = {
    var acc : Double =0
    rechercherReservations((d2 : Demandeur) => d2==d,(r:Reservation)=> r.date_resa.get(Calendar.WEEK_OF_YEAR) == numSemaine).foreach((r: Reservation) => acc += GF.cout(d, r))
    acc
  }

  def tauxOccupationSemaine(predicat : Salle => Boolean, numSemaine:Integer):Double = {
    var resSalle = rechercherReservations((d:Demandeur)=>true, (r:Reservation)=>predicat(r.salle) && r.date_resa.get(Calendar.WEEK_OF_YEAR) == numSemaine ).size
    resSalle / (7.0 * GF.coutDuree.size)*100.0
  }

  def PlanningSemaine(predicat : Salle => Boolean, numSemaine:Integer) = {
    rechercherReservations((d:Demandeur)=>true, (r:Reservation)=>predicat(r.salle) && r.date_resa.get(Calendar.WEEK_OF_YEAR) == numSemaine)
  }


/*Une biche a brouté dans ta main l'herbe du savoir. */
/* L'herbe du savoir commence à se faner */
/* Mais elle renaitra dans le champs de la connaissance */
/* THUG LIFE */
}
