package fr.univ.nantes.roomanager

import fr.univ.nantes.roomanager.bean._
import org.junit.{Before, Test}

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
@Test
class SystemTest {
  var sys: Systeme = _

  @Before
  def init(): Unit = {
    sys = new Systeme()
  }

  @Test(expected = classOf[Exception])
  def adresseDaoTest0(): Unit = {
    sys.getAdresse(-1)
  }

  @Test
  def adresseDaoTest1(): Unit = {
    val adr0: AdresseBean = sys.createAdresse(new AdresseBean(null, null, null))
    val adr1: AdresseBean = sys.getAdresse(adr0.getId())
    assert(adr0.getId() == adr1.getId())
    assert(adr0.getAdresse == adr1.getAdresse)
    assert(adr0.getCode == adr1.getCode)
    assert(adr0.getVille == adr1.getVille)
  }

  @Test(expected = classOf[Exception])
  def adresseDaoTest2(): Unit = {
    val adr0: AdresseBean = sys.createAdresse(new AdresseBean(null, null, null))
    sys.deleteAdresse(adr0)
    sys.getAdresse(adr0.getId())
  }

  @Test(expected = classOf[Exception])
  def batimentDaoTest0(): Unit = {
    sys.getBatiment(-1)
  }

  @Test
  def batimentDaoTest1(): Unit = {
    val bat0: BatimentBean = sys.createBatiment(new BatimentBean(0, null))
    val bat1: BatimentBean = sys.getBatiment(bat0.getId())
    assert(bat0.getId() == bat1.getId())
    assert(bat0.getId_adresse == bat1.getId_adresse)
    assert(bat0.getNom == bat1.getNom)
  }

  @Test(expected = classOf[Exception])
  def batimentDaoTest2(): Unit = {
    val bat0: BatimentBean = sys.createBatiment(new BatimentBean(0, null))
    sys.deleteBatiment(bat0)
    sys.getBatiment(bat0.getId())
  }

  @Test(expected = classOf[Exception])
  def batimentDaoTest3(): Unit = {
    val bat0: BatimentBean = new BatimentBean(0, null)
    sys.createBatiment(bat0)
    sys.createBatiment(bat0)
  }

  @Test(expected = classOf[Exception])
  def batimentDaoTest4(): Unit = {
    val bat0: BatimentBean = new BatimentBean(0, null)
    sys.createBatiment(bat0)
    sys.updateBatiment(bat0)
  }

  @Test(expected = classOf[Exception])
  def demandeurDaoTest0(): Unit = {
    sys.getDemandeur(-1)
  }

  @Test
  def demandeurDaoTest1(): Unit = {
    val dem0: DemandeurBean = sys.createDemandeur(new DemandeurBean(0, 0, 0, null))
    val dem1: DemandeurBean = sys.getDemandeur(dem0.getId())
    assert(dem0.getId() == dem1.getId())
    assert(dem0.getId_adresse == dem1.getId_adresse)
    assert(dem0.getId_typeOrigine == dem1.getId_typeOrigine)
    assert(dem0.getId_typeTitre == dem1.getId_typeTitre)
    assert(dem0.getNom == dem1.getNom)
  }

  @Test(expected = classOf[Exception])
  def demandeurDaoTest2(): Unit = {
    val dem0: DemandeurBean = sys.createDemandeur(new DemandeurBean(0, 0, 0, null))
    sys.deleteDemandeur(dem0)
    sys.getDemandeur(dem0.getId())
  }

  @Test(expected = classOf[Exception])
  def demandeurDaoTest3(): Unit = {
    val dem0: DemandeurBean = new DemandeurBean(0, 0, 0, null)
    sys.createDemandeur(dem0)
    sys.createDemandeur(dem0)
  }

  @Test(expected = classOf[Exception])
  def demandeurDaoTest4(): Unit = {
    val dem0: DemandeurBean = new DemandeurBean(0, 0, 0, null)
    sys.createDemandeur(dem0)
    sys.updateDemandeur(dem0)
  }

  @Test(expected = classOf[Exception])
  def materielDaoTest0(): Unit = {
    sys.getMateriel(-1)
  }

  @Test
  def materielDaoTest1(): Unit = {
    val mat0: MaterielBean = sys.createMateriel(new MaterielBean(0, -1, 0))
    val mat1: MaterielBean = sys.getMateriel(mat0.getId())
    assert(mat0.getId() == mat1.getId())
    assert(mat0.getId_reservation == mat1.getId_reservation)
    assert(mat0.getId_salle == mat1.getId_salle)
    assert(mat0.getId_typeMateriel == mat1.getId_typeMateriel)
  }

  @Test(expected = classOf[Exception])
  def materielDaoTest2(): Unit = {
    val mat0: MaterielBean = sys.createMateriel(new MaterielBean(0, 0, -1))
    sys.deleteMateriel(mat0)
    sys.getMateriel(mat0.getId())
  }

  @Test(expected = classOf[Exception])
  def reservationDaoTest0(): Unit = {
    sys.getReservation(-1)
  }

  @Test
  def reservationDaoTest1(): Unit = {
    val res0: ReservationBean = sys.createReservation(new ReservationBean(0, 0, 0, 0, null))
    val res1: ReservationBean = sys.getReservation(res0.getId())
    assert(res0.getId() == res1.getId())
    assert(res0.getDateResa == res1.getDateResa)
    assert(res0.getId_demandeur == res1.getId_demandeur)
    assert(res0.getId_salle == res1.getId_salle)
    assert(res0.getId_typeDuree == res1.getId_typeDuree)
    assert(res0.getId_typeManifestation == res1.getId_typeManifestation)

  }

  @Test(expected = classOf[Exception])
  def reservationDaoTest2(): Unit = {
    val res0: ReservationBean = sys.createReservation(new ReservationBean(0, 0, 0, 0, null))
    sys.deleteReservation(res0)
    sys.getReservation(res0.getId())
  }

  @Test(expected = classOf[Exception])
  def reservationDaoTest3(): Unit = {
    val res0: ReservationBean = new ReservationBean(0, 0, 0, 0, null)
    sys.createReservation(res0)
    sys.createReservation(res0)
  }

  @Test(expected = classOf[Exception])
  def reservationDaoTest4(): Unit = {
    val res0: ReservationBean = new ReservationBean(0, 0, 0, 0, null)
    sys.createReservation(res0)
    sys.deleteReservation(res0)
    sys.createReservation(res0)
  }

  @Test(expected = classOf[Exception])
  def salleDaoTest0(): Unit = {
    sys.getSalle(-1)
  }

  @Test
  def salleDaoTest1(): Unit = {
    val sal0: SalleBean = sys.createSalle(new SalleBean(0, 0, 0, 0))
    val sal1: SalleBean = sys.getSalle(sal0.getId())
    assert(sal0.getId() == sal1.getId())
    assert(sal0.getEtage == sal1.getEtage)
    assert(sal0.getSuperficie == sal1.getSuperficie)
    assert(sal0.getId_batiment == sal1.getId_batiment)
    assert(sal0.getId_typeSalle == sal1.getId_typeSalle)
  }

  @Test(expected = classOf[Exception])
  def salleDaoTest2(): Unit = {
    val sal0: SalleBean = sys.createSalle(new SalleBean(0, 0, 0, 0))
    sys.deleteSalle(sal0)
    sys.getSalle(sal0.getId())
  }

  @Test(expected = classOf[Exception])
  def typeDureeDaoTest0(): Unit = {
    sys.getTypeDuree(-1)
  }

  @Test
  def typeDureeDaoTest1(): Unit = {
    val tar0: TarifBean = sys.createTypeDuree(new TarifBean(null, 0.0, 0.0))
    val tar1: TarifBean = sys.getTypeDuree(tar0.getId())
    assert(tar0.getId() == tar1.getId())
    assert(tar0.getLibelle == tar1.getLibelle)
    assert(tar0.getTarifBase == tar1.getTarifBase)
    assert(tar0.getTarifCoef == tar1.getTarifCoef)
  }

  @Test(expected = classOf[Exception])
  def typeDureeDaoTest2(): Unit = {
    val tar0: TarifBean = sys.createTypeDuree(new TarifBean(null, 0.0, 0.0))
    sys.deleteTypeDuree(tar0)
    sys.getTypeDuree(tar0.getId())
  }

  @Test(expected = classOf[Exception])
  def typeDureeDaoTest3(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeDuree(tar0)
    sys.createTypeDuree(tar0)
  }

  @Test(expected = classOf[Exception])
  def typeDureeDaoTest4(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeDuree(tar0)
    sys.updateTypeDuree(tar0)
  }


  @Test(expected = classOf[Exception])
  def typeManifestationDaoTest0(): Unit = {
    sys.getTypeManifestation(-1)
  }

  @Test
  def typeManifestationDaoTest1(): Unit = {
    val tar0: TarifBean = sys.createTypeManifestation(new TarifBean(null, 0.0, 0.0))
    val tar1: TarifBean = sys.getTypeManifestation(tar0.getId())
    assert(tar0.getId() == tar1.getId())
    assert(tar0.getLibelle == tar1.getLibelle)
    assert(tar0.getTarifBase == tar1.getTarifBase)
    assert(tar0.getTarifCoef == tar1.getTarifCoef)
  }

  @Test(expected = classOf[Exception])
  def typeManifestationDaoTest2(): Unit = {
    val tar0: TarifBean = sys.createTypeManifestation(new TarifBean(null, 0.0, 0.0))
    sys.deleteTypeManifestation(tar0)
    sys.getTypeManifestation(tar0.getId())
  }

  @Test(expected = classOf[Exception])
  def typeManifestationDaoTest3(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeManifestation(tar0)
    sys.createTypeManifestation(tar0)
  }

  @Test(expected = classOf[Exception])
  def typeManifestationDaoTest4(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeManifestation(tar0)
    sys.updateTypeManifestation(tar0)
  }


  @Test(expected = classOf[Exception])
  def typeMaterielDaoTest0(): Unit = {
    sys.getTypeMateriel(-1)
  }

  @Test
  def typeMaterielDaoTest1(): Unit = {
    val tar0: TarifBean = sys.createTypeMateriel(new TarifBean(null, 0.0, 0.0))
    val tar1: TarifBean = sys.getTypeMateriel(tar0.getId())
    assert(tar0.getId() == tar1.getId())
    assert(tar0.getLibelle == tar1.getLibelle)
    assert(tar0.getTarifBase == tar1.getTarifBase)
    assert(tar0.getTarifCoef == tar1.getTarifCoef)
  }

  @Test(expected = classOf[Exception])
  def typeMaterielDaoTest2(): Unit = {
    val tar0: TarifBean = sys.createTypeMateriel(new TarifBean(null, 0.0, 0.0))
    sys.deleteTypeMateriel(tar0)
    sys.getTypeMateriel(tar0.getId())
  }

  @Test(expected = classOf[Exception])
  def typeMaterielDaoTest3(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeMateriel(tar0)
    sys.createTypeMateriel(tar0)
  }

  @Test(expected = classOf[Exception])
  def typeMaterielDaoTest4(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeMateriel(tar0)
    sys.updateTypeMateriel(tar0)
  }


  @Test(expected = classOf[Exception])
  def typeOrigineDaoTest0(): Unit = {
    sys.getTypeOrigine(-1)
  }

  @Test
  def typeOrigineDaoTest1(): Unit = {
    val tar0: TarifBean = sys.createTypeOrigine(new TarifBean(null, 0.0, 0.0))
    val tar1: TarifBean = sys.getTypeOrigine(tar0.getId())
    assert(tar0.getId() == tar1.getId())
    assert(tar0.getLibelle == tar1.getLibelle)
    assert(tar0.getTarifBase == tar1.getTarifBase)
    assert(tar0.getTarifCoef == tar1.getTarifCoef)
  }

  @Test(expected = classOf[Exception])
  def typeOrigineDaoTest2(): Unit = {
    val tar0: TarifBean = sys.createTypeOrigine(new TarifBean(null, 0.0, 0.0))
    sys.deleteTypeOrigine(tar0)
    sys.getTypeOrigine(tar0.getId())
  }

  @Test(expected = classOf[Exception])
  def typeOrigineDaoTest3(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeOrigine(tar0)
    sys.createTypeOrigine(tar0)
  }

  @Test(expected = classOf[Exception])
  def typeOrigineDaoTest4(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeOrigine(tar0)
    sys.updateTypeOrigine(tar0)
  }


  @Test(expected = classOf[Exception])
  def typeSalleDaoTest0(): Unit = {
    sys.getTypeSalle(-1)
  }

  @Test
  def typeSalleDaoTest1(): Unit = {
    val tar0: TarifBean = sys.createTypeSalle(new TarifBean(null, 0.0, 0.0))
    val tar1: TarifBean = sys.getTypeSalle(tar0.getId())
    assert(tar0.getId() == tar1.getId())
    assert(tar0.getLibelle == tar1.getLibelle)
    assert(tar0.getTarifBase == tar1.getTarifBase)
    assert(tar0.getTarifCoef == tar1.getTarifCoef)
  }

  @Test(expected = classOf[Exception])
  def typeSalleDaoTest2(): Unit = {
    val tar0: TarifBean = sys.createTypeSalle(new TarifBean(null, 0.0, 0.0))
    sys.deleteTypeSalle(tar0)
    sys.getTypeSalle(tar0.getId())
  }

  @Test(expected = classOf[Exception])
  def typeSalleDaoTest3(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeSalle(tar0)
    sys.createTypeSalle(tar0)
  }

  @Test(expected = classOf[Exception])
  def typeSalleDaoTest4(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeSalle(tar0)
    sys.updateTypeSalle(tar0)
  }


  @Test(expected = classOf[Exception])
  def typeTitreDaoTest0(): Unit = {
    sys.getTypeTitre(-1)
  }

  @Test
  def typeTitreDaoTest1(): Unit = {
    val tar0: TarifBean = sys.createTypeTitre(new TarifBean(null, 0.0, 0.0))
    val tar1: TarifBean = sys.getTypeTitre(tar0.getId())
    assert(tar0.getId() == tar1.getId())
    assert(tar0.getLibelle == tar1.getLibelle)
    assert(tar0.getTarifBase == tar1.getTarifBase)
    assert(tar0.getTarifCoef == tar1.getTarifCoef)
  }

  @Test(expected = classOf[Exception])
  def typeTitreDaoTest2(): Unit = {
    val tar0: TarifBean = sys.createTypeTitre(new TarifBean(null, 0.0, 0.0))
    sys.deleteTypeTitre(tar0)
    sys.getTypeTitre(tar0.getId())
  }

  @Test(expected = classOf[Exception])
  def typeTitreDaoTest3(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeTitre(tar0)
    sys.createTypeTitre(tar0)
  }

  @Test(expected = classOf[Exception])
  def typeTitreDaoTest4(): Unit = {
    val tar0: TarifBean = new TarifBean(null, 0.0, 0.0)
    sys.createTypeTitre(tar0)
    sys.updateTypeTitre(tar0)
  }
}
