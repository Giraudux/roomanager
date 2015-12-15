package fr.univ.nantes.roomanager.dao

/**
 * @author Pierre Gaultier & Alexis Giraudet
 */
trait Dao[T] {
  def get(id: Int): T
  def create(t: T): T
  def update(t: T)
  def delete(t: T)
  def find(p: (T) => Boolean): Traversable[T]
}
