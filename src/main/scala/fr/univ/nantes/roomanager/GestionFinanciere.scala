package scala.fr.univ.nantes.roomanager

import scala.collection.mutable.HashMap

class GestionFinanciere() {
  
  
  /* a revoir #PONEY*/
  var coutTypeSalle : HashMap[String,Double] = HashMap("Cuisine" -> 27.0, "Toilettes" -> 12.5,"Salon" ->25.0)
  var coutSuperficie : HashMap[Double,Double] = HashMap(10.0 -> 50, 20.0 -> 75,30.0 ->100,40.0->125)
  var coutDurée : HashMap[String,Double] = HashMap("DemiJournee" -> 30, "Soiree" -> 45)
  var coutOrigine : HashMap[String,Double] = HashMap("Resident" -> 25, "NonResident" -> 75)
  var coutTypeTitre : HashMap[String,Double] = HashMap("Particulier" -> 29.0, "Association" -> 23.5,"Entreprise" ->50.0)
  var typeManifestation : HashMap[String,Double] = HashMap("Reunion" -> 99.0, "Banquet" -> 89.5,"Spectacle" ->17)
  
  def cout(d: Demandeur,  r: Reservation) = (coutTypeSalle apply r.salle.type_sal)+ (coutSuperficie apply r.salle.superficie)+ (coutDurée apply r.duree)+ (coutOrigine apply d.origine)+ (coutTypeTitre apply d.titre) + (typeManifestation apply r.manifestation)
}