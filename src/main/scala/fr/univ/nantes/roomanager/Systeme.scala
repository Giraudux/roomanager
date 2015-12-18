
package fr.univ.nantes.roomanager

import java.util.Calendar

import fr.univ.nantes.roomanager.bean._
import fr.univ.nantes.roomanager.dao.adresse.{AdresseDao, AdresseDaoImpl}
import fr.univ.nantes.roomanager.dao.batiment.{BatimentDao, BatimentDaoImpl}
import fr.univ.nantes.roomanager.dao.demandeur.{DemandeurDao, DemandeurDaoImpl}
import fr.univ.nantes.roomanager.dao.materiel.{MaterielDao, MaterielDaoImpl}
import fr.univ.nantes.roomanager.dao.reservation.{ReservationDao, ReservationDaoImpl}
import fr.univ.nantes.roomanager.dao.salle.{SalleDao, SalleDaoImpl}
import fr.univ.nantes.roomanager.dao.typeduree.{TypeDureeDao, TypeDureeDaoImpl}
import fr.univ.nantes.roomanager.dao.typemanifestation.{TypeManifestationDao, TypeManifestationDaoImpl}
import fr.univ.nantes.roomanager.dao.typemateriel.{TypeMaterielDao, TypeMaterielDaoImpl}
import fr.univ.nantes.roomanager.dao.typeorigine.{TypeOrigineDao, TypeOrigineDaoImpl}
import fr.univ.nantes.roomanager.dao.typesalle.{TypeSalleDao, TypeSalleDaoImpl}
import fr.univ.nantes.roomanager.dao.typetitre.{TypeTitreDao, TypeTitreDaoImpl}

import scala.fr.univ.nantes.roomanager._


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

  def ajouterTypeSalle(s: String, d: Double) = GF.coutTypeSalle += ((s, d))

  def supprimerTypeSalle(s: String) = GF.coutTypeSalle -= s

  def consulterTypeSalle() = GF.coutTypeSalle

  def ajouterTypeMateriel(t: String, d: Double) = GF.coutTypeMateriel += ((t, d))

  def supprimerTypeMateriel(t: String) = GF.coutTypeMateriel -= t

  def consulterTypeMateriel() = GF.coutTypeMateriel

  def ajouterTypeManifestation(t: String, d: Double) = GF.typeManifestation += ((t, d))

  def supprimerTypeManifestation(t: String) = GF.typeManifestation -= t

  def consulterTypeManifestation() = GF.typeManifestation

  def ajouterTypeDuree(t: String, d: Double) = GF.coutDuree += ((t, d))

  def supprimerTypeDuree(t: String) = GF.coutDuree -= t

  def consulterTypeDuree() = GF.coutDuree

  def ajouterDemandeur(d: Demandeur) = demandeurs += d

  def rechercherDemandeur(predicate: Demandeur => Boolean) = demandeurs.find(predicate)

  def modiferDemandeur(predicate: Demandeur => Boolean, function: Demandeur => Unit) = rechercherDemandeur(predicate).foreach(function)

  def supprimerDemandeur(predicate: Demandeur => Boolean) = {
    demandeurs = demandeurs.filterNot(predicate)
  }

  def factureHebdo(d: Demandeur, numSemaine: Int) = {
    var acc: Double = 0
    rechercherReservations((d2: Demandeur) => d2 == d, (r: Reservation) => r.date_resa.get(Calendar.WEEK_OF_YEAR) == numSemaine).foreach((r: Reservation) => acc += GF.cout(d, r))
    acc
  }

  def tauxOccupationSemaine(predicat: Salle => Boolean, numSemaine: Integer): Double = {
    var resSalle = rechercherReservations((d: Demandeur) => true, (r: Reservation) => predicat(r.salle) && r.date_resa.get(Calendar.WEEK_OF_YEAR) == numSemaine).size
    resSalle / (7.0 * GF.coutDuree.size) * 100.0
  }

  def PlanningSemaine(predicat: Salle => Boolean, numSemaine: Integer) = {
    rechercherReservations((d: Demandeur) => true, (r: Reservation) => predicat(r.salle) && r.date_resa.get(Calendar.WEEK_OF_YEAR) == numSemaine)
  }


  /*Une biche a brouté dans ta main l'herbe du savoir. */
  /* L'herbe du savoir commence à se faner */
  /* Mais elle renaitra dans le champs de la connaissance */
  /* THUG LIFE */

  var adresseDao: AdresseDao = new AdresseDaoImpl()
  var batimentDao: BatimentDao = new BatimentDaoImpl()
  var demandeurDao: DemandeurDao = new DemandeurDaoImpl()
  var materielDao: MaterielDao = new MaterielDaoImpl()
  var reservationDao: ReservationDao = new ReservationDaoImpl()
  var salleDao: SalleDao = new SalleDaoImpl()
  var typeDureeDao: TypeDureeDao = new TypeDureeDaoImpl()
  var typeManifestationDao: TypeManifestationDao = new TypeManifestationDaoImpl()
  var typeMaterielDao: TypeMaterielDao = new TypeMaterielDaoImpl()
  var typeOrigineDao: TypeOrigineDao = new TypeOrigineDaoImpl()
  var typeSalleDao: TypeSalleDao = new TypeSalleDaoImpl()
  var typeTitreDao: TypeTitreDao = new TypeTitreDaoImpl()

  init()

  def init(): Unit = {
    var tarif: TarifBean = new TarifBean(null, 0.0, 1.0)

    tarif.setLibelle("Demi-journée")
    typeDureeDao.create(tarif)
    tarif.setLibelle("Soirée")
    typeDureeDao.create(tarif)

    tarif.setLibelle("Réunion")
    typeManifestationDao.create(tarif)
    tarif.setLibelle("Banquet")
    typeManifestationDao.create(tarif)
    tarif.setLibelle("Spectacle")
    typeManifestationDao.create(tarif)

    tarif.setLibelle("Tableau")
    typeMaterielDao.create(tarif)
    tarif.setLibelle("Rétro-projecteur")
    typeMaterielDao.create(tarif)
    tarif.setLibelle("Vidéo-projecteur")
    typeMaterielDao.create(tarif)

    tarif.setLibelle("Résident")
    typeOrigineDao.create(tarif)
    tarif.setLibelle("Non résident")
    typeOrigineDao.create(tarif)

    tarif.setLibelle("Salle des fêtes")
    typeSalleDao.create(tarif)
    tarif.setLibelle("Salle polyvalente")
    typeSalleDao.create(tarif)
    tarif.setLibelle("Salle hôtel")
    typeSalleDao.create(tarif)
    tarif.setLibelle("Salle restaurant")
    typeSalleDao.create(tarif)

    tarif.setLibelle("Particulier")
    typeTitreDao.create(tarif)
    tarif.setLibelle("Association")
    typeTitreDao.create(tarif)
    tarif.setLibelle("Entreprise")
    typeTitreDao.create(tarif)
  }

  def createAdresse(adresse: AdresseBean): AdresseBean = adresseDao.create(adresse)

  def getAdresse(id: Int): AdresseBean = adresseDao.get(id)

  def deleteAdresse(adresse: AdresseBean): Unit = adresseDao.delete(adresse)

  def createReservation(reservation: ReservationBean): ReservationBean = null

  def getReservation(id: Int): ReservationBean = reservationDao.get(id)

  def getAllReservation(): Traversable[ReservationBean] = reservationDao.getAll()

  def searchReservation(predicate: (ReservationBean) => Boolean): Traversable[ReservationBean] = reservationDao.find(predicate)

  //no update
  def deleteReservation(reservation: ReservationBean): Unit = reservationDao.delete(reservation)

  def createSalle(salle: SalleBean): SalleBean = salleDao.create(salle)

  def getSalle(id: Int): SalleBean = salleDao.get(id)

  def getAllSalle(): Traversable[SalleBean] = salleDao.getAll()

  def searchSalle(predicate: (SalleBean) => Boolean): Traversable[SalleBean] = salleDao.find(predicate)

  def updateSalle(salle: SalleBean): Unit = salleDao.update(salle)

  def deleteSalle(salle: SalleBean): Unit = salleDao.delete(salle)

  def createBatiment(batiment: BatimentBean): BatimentBean = batimentDao.create(batiment)

  def getBatiment(id: Int): BatimentBean = batimentDao.get(id)

  def getAllBatiment(): Traversable[BatimentBean] = batimentDao.getAll()

  def searchBatiment(predicate: (BatimentBean) => Boolean): Traversable[BatimentBean] = batimentDao.find(predicate)

  def updateBatiment(batiment: BatimentBean): Unit = batimentDao.update(batiment)

  def deleteBatiment(batiment: BatimentBean): Unit = batimentDao.delete(batiment)

  def createDemandeur(demandeur: DemandeurBean): DemandeurBean = demandeurDao.create(demandeur)

  def getDemandeur(id: Int): DemandeurBean = demandeurDao.get(id)

  def getAllDemandeur(): Traversable[DemandeurBean] = demandeurDao.getAll()

  def searchDemandeur(predicate: (DemandeurBean) => Boolean): Traversable[DemandeurBean] = demandeurDao.find(predicate)

  def updateDemandeur(demandeur: DemandeurBean): Unit = demandeurDao.update(demandeur)

  def deleteDemandeur(demandeur: DemandeurBean): Unit = demandeurDao.delete(demandeur)

  def createMateriel(materiel: MaterielBean): MaterielBean = materielDao.create(materiel)

  def getMateriel(id: Int): MaterielBean = materielDao.get(id)

  def getAllMateriel(): Traversable[MaterielBean] = materielDao.getAll()

  def searchMateriel(predicate: (MaterielBean) => Boolean): Traversable[MaterielBean] = materielDao.find(predicate)

  def updateMateriel(materiel: MaterielBean): Unit = materielDao.update(materiel)

  def deleteMateriel(materiel: MaterielBean): Unit = materielDao.delete(materiel)


  //ajout/suppression/consultation type salle
  def createTypeDuree(typeDuree: TarifBean): TarifBean = typeDureeDao.create(typeDuree)

  def getTypeDuree(id: Int): TarifBean = typeDureeDao.get(id)

  def getAllTypeDuree(): Traversable[TarifBean] = typeDureeDao.getAll()

  def deleteTypeDuree(typeDuree: TarifBean): Unit = /*check usage*/ typeDureeDao.delete(typeDuree)

  def createTypeManifestation(typeManifestation: TarifBean): TarifBean = typeManifestationDao.create(typeManifestation)

  def getTypeManifestation(id: Int): TarifBean = typeManifestationDao.get(id)

  def getAllTypeManifestation(): Traversable[TarifBean] = typeManifestationDao.getAll()

  def deleteTypeManifestation(typeManifestation: TarifBean): Unit = /*check usage*/ typeManifestationDao.delete(typeManifestation)

  def createTypeMateriel(typeMateriel: TarifBean): TarifBean = typeMaterielDao.create(typeMateriel)

  def getTypeMateriel(id: Int): TarifBean = typeMaterielDao.get(id)

  def getAllTypeMateriel(): Traversable[TarifBean] = typeMaterielDao.getAll()

  def deleteTypeMateriel(typeMateriel: TarifBean): Unit = /*check usage*/ typeMaterielDao.delete(typeMateriel)

  def createTypeOrigine(typeOrigine: TarifBean): TarifBean = typeOrigineDao.create(typeOrigine)

  def getTypeOrigine(id: Int): TarifBean = typeOrigineDao.get(id)

  def getAllTypeOrigine(): Traversable[TarifBean] = typeOrigineDao.getAll()

  def deleteTypeOrigine(typeOrigine: TarifBean): Unit = /*check usage*/ typeOrigineDao.delete(typeOrigine)

  def createTypeSalle(typeSalle: TarifBean): TarifBean = typeSalleDao.create(typeSalle)

  def getTypeSalle(id: Int): TarifBean = typeSalleDao.get(id)

  def getAllTypeSalle(): Traversable[TarifBean] = typeSalleDao.getAll()

  def deleteTypeSalle(typeSalle: TarifBean): Unit = /*check usage*/ typeSalleDao.delete(typeSalle)

  def createTypeTitre(typeTitre: TarifBean): TarifBean = typeTitreDao.create(typeTitre)

  def getTypeTitre(id: Int): TarifBean = typeTitreDao.get(id)

  def getAllTypeTitre(): Traversable[TarifBean] = typeTitreDao.getAll()

  def deleteTypeTitre(typeTitre: TarifBean): Unit = /*check usage*/ typeTitreDao.delete(typeTitre)


  //gestion demandeur

  //gestion materiel

  //gestion financière
}
