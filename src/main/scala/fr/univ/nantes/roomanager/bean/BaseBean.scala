package fr.univ.nantes.roomanager.bean

class BaseBean {
  def getId(): Int = -1

  override def equals(other: Any): Boolean = other match {
    case that: BaseBean =>
      other.isInstanceOf[BaseBean] &&
        getId == that.getId
    case _ => false
  }
}
