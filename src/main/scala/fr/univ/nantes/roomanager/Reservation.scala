
import java.util.Date
import Manifestation._

class Reservation(sr:Salle,mr:Materiel,r:Int,d:Date,m:Float,man:Manifestation) {
  var salleRes:Salle = sr
  var matRes:Materiel = mr
  var refResa:Int = r
  var dateResa:Date = d
  var montant:Float = m
  var tManifestation:Manifestation = man
  //var duree : Duree
}