package fr.univ.nantes.roomanager

import fr.univ.nantes.roomanager.bean._
import org.junit.{Before, Test}

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
@Test
class SystemTest {
  var sys : Systeme = _

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
    val dem0: DemandeurBean = sys.createDemandeur(new DemandeurBean(0,0,0, null))
    val  dem1: DemandeurBean = sys.getDemandeur(dem0.getId())
    assert(dem0.getId() == dem1.getId())
    assert(dem0.getId_adresse == dem1.getId_adresse)
    assert(dem0.getId_typeOrigine == dem1.getId_typeOrigine)
    assert(dem0.getId_typeTitre == dem1.getId_typeTitre)
    assert(dem0.getNom == dem1.getNom)
  }

  @Test(expected = classOf[Exception])
  def demandeurDaoTest2(): Unit = {
    val dem0: DemandeurBean = sys.createDemandeur(new DemandeurBean(0,0,0, null))
    sys.deleteDemandeur(dem0)
    sys.getDemandeur(dem0.getId())
  }

  @Test(expected = classOf[Exception])
  def demandeurDaoTest3(): Unit = {
    val dem0: DemandeurBean = new DemandeurBean(0,0,0, null)
    sys.createDemandeur(dem0)
    sys.createDemandeur(dem0)
  }

  @Test(expected = classOf[Exception])
  def demandeurDaoTest4(): Unit = {
    val dem0: DemandeurBean = new DemandeurBean(0,0,0, null)
    sys.createDemandeur(dem0)
    sys.updateDemandeur(dem0)
  }

  @Test(expected = classOf[Exception])
  def materielDaoTest0(): Unit = {
    sys.getMateriel(-1)
  }

  @Test
  def materielDaoTest1(): Unit = {
    val mat0: MaterielBean = sys.createMateriel(new MaterielBean(0,-1,0))
    val mat1: MaterielBean = sys.getMateriel(mat0.getId())
    assert(mat0.getId() == mat1.getId())
    assert(mat0.getId_reservation == mat1.getId_reservation)
    assert(mat0.getId_salle == mat1.getId_salle)
    assert(mat0.getId_typeMateriel == mat1.getId_typeMateriel)
  }

  @Test(expected = classOf[Exception])
  def materielDaoTest2(): Unit = {
    val mat0: MaterielBean = sys.createMateriel(new MaterielBean(0,0,-1))
    sys.deleteMateriel(mat0)
    sys.getMateriel(mat0.getId())
  }

  @Test(expected = classOf[Exception])
  def reservationDaoTest0(): Unit = {
    sys.getReservation(-1)
  }

  @Test
  def reservationDaoTest1(): Unit = {
    val res0: ReservationBean = sys.createReservation(new ReservationBean(0,0,0,0,null))
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
    val res0: ReservationBean = sys.createReservation(new ReservationBean(0,0,0,0,null))
    sys.deleteReservation(res0)
    sys.getReservation(res0.getId())
  }

  @Test(expected = classOf[Exception])
  def reservationDaoTest3(): Unit = {
    val res0: ReservationBean = new ReservationBean(0,0,0,0,null)
    sys.createReservation(res0)
    sys.createReservation(res0)
  }

  @Test(expected = classOf[Exception])
  def reservationDaoTest4(): Unit = {
    val res0: ReservationBean = new ReservationBean(0,0,0,0,null)
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
    val sal0: SalleBean = sys.createSalle(new SalleBean(0,0,0,0))
    val sal1: SalleBean = sys.getSalle(sal0.getId())
    assert(sal0.getId() == sal1.getId())
    assert(sal0.getEtage == sal1.getEtage)
    assert(sal0.getSuperficie == sal1.getSuperficie)
    assert(sal0.getId_batiment == sal1.getId_batiment)
    assert(sal0.getId_typeSalle == sal1.getId_typeSalle)
  }

  @Test(expected = classOf[Exception])
  def salleDaoTest2(): Unit = {
    val sal0: SalleBean = sys.createSalle(new SalleBean(0,0,0,0))
    sys.deleteSalle(sal0)
    sys.getSalle(sal0.getId())
  }

  /*def example(): Unit = {
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
  }*/
}
