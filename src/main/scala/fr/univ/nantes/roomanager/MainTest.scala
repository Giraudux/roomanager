package fr.univ.nantes.roomanager


object MainTest {
  def main(args: Array[String]): Unit = {
    val syst: Systeme = new Systeme
    val bat1 = new Batiment(1, "bebert", new Adresse("1", "rue du Cheval Jaune", "43000", "Hert"))
    syst.ajouterBatiment(bat1)
    syst.ajouterSalle(new Salle(1, 12, 1, 12, "Cuisine"))
    syst.ajouterSalle(new Salle(1, 2, 1, 17, "Toilettes"))
    syst.ajouterSalle(new Salle(3, 1, 1, 120, "Toilettes"))
    syst.ajouterSalle(new Salle(4, 8, 1, 10, "Cuisine"))
    val set1 = syst.rechercherSalle(bat1, (s: Salle) => s.type_sal == "Cuisine")
    set1.foreach((s: Salle) => println(s.no_bat + " " + s.no_etage + " " + s.no_salle + " " + s.superficie + " " + s.type_sal))
  }
}
