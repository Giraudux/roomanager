package fr.univ.nantes.roomanager.dao

/**
 * @author Pierre Gaultier
 * @author Alexis Giraudet
 */
trait Dao[T] {
  def get(id: Int): T

  def getAll(): Traversable[T]

  def create(t: T): T

  def update(t: T): Unit

  def delete(t: T): Unit

  def find(p: (T) => Boolean): Traversable[T]
}
