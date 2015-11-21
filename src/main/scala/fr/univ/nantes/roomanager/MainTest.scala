package fr.univ.nantes.roomanager

import scala.fr.univ.nantes.roomanager.Salle
import scala.fr.univ.nantes.roomanager.Batiment
import scala.fr.univ.nantes.roomanager.Adresse
import scala.fr.univ.nantes.roomanager.Demandeur
import scala.fr.univ.nantes.roomanager.Reservation
import scala.fr.univ.nantes.roomanager.GestionFinanciere
import java.util.Date



object MainTest {
  def main(args: Array[String]): Unit = {
    
    val syst: Systeme = new Systeme
    val bat1 = new Batiment(1, "bebert", new Adresse("1", "rue du Cheval Jaune", "43000", "Hert"))
    val dem1 = new Demandeur(1,"Choupette<3",new Adresse("17", "impasse Hector Berlioz", "43000", "Basse-Goulaine"),"Particulier","Resident")
    val GF = new GestionFinanciere()
    
    syst.ajouterBatiment(bat1)
    syst.ajouterSalle(bat1,new Salle(1, 12, 1, 12, "Cuisine"))
    syst.ajouterSalle(bat1,new Salle(1, 2, 1, 17, "Toilettes"))
    syst.ajouterSalle(bat1,new Salle(3, 1, 1, 120, "Toilettes"))
    syst.ajouterSalle(bat1,new Salle(4, 8, 1, 10, "Cuisine"))
    syst.ajouterDemandeur(dem1)
    
    val salleTest = syst.rechercherSalle((b:Batiment) => b.nom=="bebert", (s: Salle) => s.no_etage == 1 && s.no_salle == 12).head
    
    val res1 = new Reservation(new Date(),salleTest,"Banquet","Soiree")
    
    syst.reserverSalle(dem1, res1)
    val set1 = syst.rechercherSalle((b:Batiment) => b.nom=="bebert", (s: Salle) => s.type_sal == "Cuisine")
    set1.foreach((s: Salle) => println(s.no_bat + " " + s.no_etage + " " + s.no_salle + " " + s.superficie + " " + s.type_sal))

    println("cout de la reservation RES1: " + GF.cout(dem1,res1))
        
  }
}
