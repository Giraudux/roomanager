package fr.univ.nantes.roomanager.bean

class BaseBean {

  def getId(): Int = -1

  def canEqual(other: Any): Boolean = other.isInstanceOf[BaseBean]

  override def equals(other: Any): Boolean = other match {
    case that: BaseBean =>
      (that canEqual this) &&
        getId() == that.getId()
    case _ => false
  }
}
