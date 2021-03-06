
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

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
class Systeme {

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

  // gestion des adresses

  def createAdresse(adresse: AdresseBean): AdresseBean = adresseDao.create(adresse)

  def getAdresse(id: Int): AdresseBean = adresseDao.get(id)

  def searchAdresse(predicate: (AdresseBean) => Boolean): Traversable[AdresseBean] = adresseDao.find(predicate)

  def updateAdresse(adresse: AdresseBean): Unit = adresseDao.update(adresse)

  def deleteAdresse(adresse: AdresseBean): Unit = adresseDao.delete(adresse)

  // gestion des reservations

  def createReservation(reservation: ReservationBean): ReservationBean = {
    if (searchReservation((otherReservation: ReservationBean) =>
      otherReservation.getDateResa.get(Calendar.YEAR) == reservation.getDateResa.get(Calendar.YEAR) &&
        otherReservation.getDateResa.get(Calendar.DAY_OF_YEAR) == reservation.getDateResa.get(Calendar.DAY_OF_YEAR) &&
        otherReservation.getId_salle == reservation.getId_salle &&
        otherReservation.getId_typeDuree == reservation.getId_typeDuree).nonEmpty)
      throw new Exception()
    reservationDao.create(reservation)
  }

  def getReservation(id: Int): ReservationBean = reservationDao.get(id)

  def getAllReservation(): Traversable[ReservationBean] = reservationDao.getAll()

  def searchReservation(predicate: (ReservationBean) => Boolean): Traversable[ReservationBean] = reservationDao.find(predicate)

  def deleteReservation(reservation: ReservationBean): Unit = {
    searchMateriel((materiel: MaterielBean) => materiel.getId_reservation == reservation.getId())
      .foreach((materiel: MaterielBean) => deleteMateriel(materiel))
    reservationDao.delete(reservation)
  }

  // gestion des salles

  def createSalle(salle: SalleBean): SalleBean = salleDao.create(salle)

  def getSalle(id: Int): SalleBean = salleDao.get(id)

  def getAllSalle(): Traversable[SalleBean] = salleDao.getAll()

  def searchSalle(predicate: (SalleBean) => Boolean): Traversable[SalleBean] = salleDao.find(predicate)

  def updateSalle(salle: SalleBean): Unit = salleDao.update(salle)

  def deleteSalle(salle: SalleBean): Unit = {
    searchMateriel((materiel: MaterielBean) => materiel.getId_salle == salle.getId())
      .foreach((materiel: MaterielBean) => deleteMateriel(materiel))
    searchReservation((reservation: ReservationBean) => reservation.getId_salle == salle.getId())
      .foreach((reservation: ReservationBean) => deleteReservation(reservation))
    salleDao.delete(salle)
  }

  // gestion des batiments

  def createBatiment(batiment: BatimentBean): BatimentBean = batimentDao.create(batiment)

  def getBatiment(id: Int): BatimentBean = batimentDao.get(id)

  def getAllBatiment(): Traversable[BatimentBean] = batimentDao.getAll()

  def searchBatiment(predicate: (BatimentBean) => Boolean): Traversable[BatimentBean] = batimentDao.find(predicate)

  def updateBatiment(batiment: BatimentBean): Unit = batimentDao.update(batiment)

  def deleteBatiment(batiment: BatimentBean): Unit = {
    searchSalle((salle: SalleBean) => salle.getId_batiment == batiment.getId())
      .foreach((salle: SalleBean) => deleteSalle(salle))
    searchAdresse((adresse: AdresseBean) => adresse.getId() == batiment.getId_adresse)
      .foreach((adresse: AdresseBean) => deleteAdresse(adresse))
    batimentDao.delete(batiment)
  }

  // gestion des demandeurs

  def createDemandeur(demandeur: DemandeurBean): DemandeurBean = demandeurDao.create(demandeur)

  def getDemandeur(id: Int): DemandeurBean = demandeurDao.get(id)

  def getAllDemandeur(): Traversable[DemandeurBean] = demandeurDao.getAll()

  def searchDemandeur(predicate: (DemandeurBean) => Boolean): Traversable[DemandeurBean] = demandeurDao.find(predicate)

  def updateDemandeur(demandeur: DemandeurBean): Unit = demandeurDao.update(demandeur)

  def deleteDemandeur(demandeur: DemandeurBean): Unit = {
    searchReservation((reservation: ReservationBean) => reservation.getId_demandeur == demandeur.getId())
      .foreach((reservation: ReservationBean) => deleteReservation(reservation))
    searchAdresse((adresse: AdresseBean) => adresse.getId() == demandeur.getId_adresse)
      .foreach((adresse: AdresseBean) => deleteAdresse(adresse))
    demandeurDao.delete(demandeur)
  }

  // gestion des materiels

  def createMateriel(materiel: MaterielBean): MaterielBean = {
    if (materiel.getId_reservation < 0 && materiel.getId_salle < 0 ||
      materiel.getId_reservation >= 0 && materiel.getId_salle >= 0)
      throw new Exception()
    materielDao.create(materiel)
  }

  def getMateriel(id: Int): MaterielBean = materielDao.get(id)

  def getAllMateriel(): Traversable[MaterielBean] = materielDao.getAll()

  def searchMateriel(predicate: (MaterielBean) => Boolean): Traversable[MaterielBean] = materielDao.find(predicate)

  def updateMateriel(materiel: MaterielBean): Unit = {
    if (materiel.getId_reservation < 0 && materiel.getId_salle < 0 ||
      materiel.getId_reservation >= 0 && materiel.getId_salle >= 0)
      throw new Exception()
    materielDao.update(materiel)
  }

  def deleteMateriel(materiel: MaterielBean): Unit = materielDao.delete(materiel)

  // gestion des types de duree

  def createTypeDuree(typeDuree: TarifBean): TarifBean = typeDureeDao.create(typeDuree)

  def getTypeDuree(id: Int): TarifBean = typeDureeDao.get(id)

  def getAllTypeDuree(): Traversable[TarifBean] = typeDureeDao.getAll()

  def updateTypeDuree(typeDuree: TarifBean): Unit = typeDureeDao.update(typeDuree)

  def deleteTypeDuree(typeDuree: TarifBean): Unit = {
    searchReservation((reservation: ReservationBean) => reservation.getId_typeDuree == typeDuree.getId())
      .foreach((reservation: ReservationBean) => deleteReservation(reservation))
    typeDureeDao.delete(typeDuree)
  }

  // gestion des types de manifestation

  def createTypeManifestation(typeManifestation: TarifBean): TarifBean = typeManifestationDao.create(typeManifestation)

  def getTypeManifestation(id: Int): TarifBean = typeManifestationDao.get(id)

  def getAllTypeManifestation(): Traversable[TarifBean] = typeManifestationDao.getAll()

  def updateTypeManifestation(typeManifestation: TarifBean): Unit = typeManifestationDao.update(typeManifestation)

  def deleteTypeManifestation(typeManifestation: TarifBean): Unit = {
    searchReservation((reservation: ReservationBean) => reservation.getId_typeManifestation == typeManifestation.getId())
      .foreach((reservation: ReservationBean) => deleteReservation(reservation))
    typeManifestationDao.delete(typeManifestation)
  }

  // gestion des types de materiel

  def createTypeMateriel(typeMateriel: TarifBean): TarifBean = typeMaterielDao.create(typeMateriel)

  def getTypeMateriel(id: Int): TarifBean = typeMaterielDao.get(id)

  def getAllTypeMateriel(): Traversable[TarifBean] = typeMaterielDao.getAll()

  def updateTypeMateriel(typeMateriel: TarifBean): Unit = typeMaterielDao.update(typeMateriel)

  def deleteTypeMateriel(typeMateriel: TarifBean): Unit = {
    searchMateriel((materiel: MaterielBean) => materiel.getId_typeMateriel == typeMateriel.getId())
      .foreach((materiel: MaterielBean) => deleteMateriel(materiel))
    typeMaterielDao.delete(typeMateriel)
  }

  // gestion des types d'origine

  def createTypeOrigine(typeOrigine: TarifBean): TarifBean = typeOrigineDao.create(typeOrigine)

  def getTypeOrigine(id: Int): TarifBean = typeOrigineDao.get(id)

  def getAllTypeOrigine(): Traversable[TarifBean] = typeOrigineDao.getAll()

  def updateTypeOrigine(typeOrigine: TarifBean): Unit = typeOrigineDao.update(typeOrigine)

  def deleteTypeOrigine(typeOrigine: TarifBean): Unit = {
    searchDemandeur((demandeur: DemandeurBean) => demandeur.getId_typeOrigine == typeOrigine.getId())
      .foreach((demandeur: DemandeurBean) => deleteDemandeur(demandeur))
    typeOrigineDao.delete(typeOrigine)
  }

  // gestion des types de salle

  def createTypeSalle(typeSalle: TarifBean): TarifBean = typeSalleDao.create(typeSalle)

  def getTypeSalle(id: Int): TarifBean = typeSalleDao.get(id)

  def getAllTypeSalle(): Traversable[TarifBean] = typeSalleDao.getAll()

  def updateTypeSalle(typeSalle: TarifBean): Unit = typeSalleDao.update(typeSalle)

  def deleteTypeSalle(typeSalle: TarifBean): Unit = {
    searchSalle((salle: SalleBean) => salle.getId_typeSalle == typeSalle.getId())
      .foreach((salle: SalleBean) => deleteSalle(salle))
    typeSalleDao.delete(typeSalle)
  }

  // gestion des types de titre

  def createTypeTitre(typeTitre: TarifBean): TarifBean = typeTitreDao.create(typeTitre)

  def getTypeTitre(id: Int): TarifBean = typeTitreDao.get(id)

  def getAllTypeTitre(): Traversable[TarifBean] = typeTitreDao.getAll()

  def updateTypeTitre(typeTitre: TarifBean): Unit = typeTitreDao.update(typeTitre)

  def deleteTypeTitre(typeTitre: TarifBean): Unit = {
    searchDemandeur((demandeur: DemandeurBean) => demandeur.getId_typeTitre == typeTitre.getId())
      .foreach((demandeur: DemandeurBean) => deleteDemandeur(demandeur))
    typeTitreDao.delete(typeTitre)
  }

  // gestion financiere

  def factureReservation(reservation: ReservationBean): Double = {
    var cout: Double = 0.0
    cout += getTypeSalle(getSalle(reservation.getId_salle).getId_typeSalle).getTarifBase
    cout += getTypeOrigine(getDemandeur(reservation.getId_demandeur).getId_typeOrigine).getTarifBase
    cout += getTypeTitre(getDemandeur(reservation.getId_demandeur).getId_typeTitre).getTarifBase
    cout += getTypeManifestation(reservation.getId_typeManifestation).getTarifBase
    cout += getTypeDuree(reservation.getId_typeDuree).getTarifBase
    searchMateriel((materiel: MaterielBean) => materiel.getId_reservation == reservation.getId() ||
      materiel.getId_salle == reservation.getId_salle)
      .foreach((materiel: MaterielBean) => cout += getTypeMateriel(materiel.getId_typeMateriel).getTarifBase)
    cout
  }

  def factureHebdo(demandeur: DemandeurBean, annee: Int, semaine: Int): Double = {
    var cout: Double = 0.0
    searchReservation((reservation: ReservationBean) => reservation.getId_demandeur == demandeur.getId() &&
      reservation.getDateResa.get(Calendar.YEAR) == annee &&
      reservation.getDateResa.get(Calendar.WEEK_OF_YEAR) == semaine)
      .foreach((reservation: ReservationBean) => cout += factureReservation(reservation))
    cout
  }

  def tauxOccupationHebdo(salle: SalleBean, annee: Int, semaine: Integer): Double = {
    searchReservation((reservation: ReservationBean) => reservation.getId_salle == salle.getId() &&
      reservation.getDateResa.get(Calendar.YEAR) == annee &&
      reservation.getDateResa.get(Calendar.WEEK_OF_YEAR) == semaine).size / 7.0 * getAllTypeDuree().size
  }

  def planningHebdo(salle: SalleBean, annee: Int, semaine: Int): Traversable[ReservationBean] = {
    searchReservation((reservation: ReservationBean) => reservation.getDateResa.get(Calendar.YEAR) == annee &&
      reservation.getDateResa.get(Calendar.WEEK_OF_YEAR) == semaine)
  }

}
