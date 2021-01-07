package bitub.dto.spatial

import bitub.dto.spatial.XYZs._

import scala.util.{Failure, Success, Try}

object Regions {

	val infiniteABox: ABox = ABox(Some(XYZs.positiveInfinite), Some(XYZs.negativeInfinite))

	def union(b1: ABox, b2: ABox): ABox = {
		val max = b1.max
		  	.zip(b2.max).map {
				case (p1:XYZ, p2:XYZ) => XYZ(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y), Math.max(p1.z, p2.z))
			}
		  .getOrElse(XYZ(Float.PositiveInfinity, Float.PositiveInfinity, Float.PositiveInfinity))
		val min = b1.min
		  .zip(b2.min).map {
			case (p1:XYZ, p2:XYZ) => XYZ(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.min(p1.z, p2.z))
		}
		.getOrElse(XYZ(Float.NegativeInfinity, Float.NegativeInfinity, Float.NegativeInfinity))
		ABox(Some(min), Some(max))
	}

	def union(b1: OBox, b2: OBox): OBox = ???

	def union(b1: BoundingBox, b2: BoundingBox): Try[BoundingBox] = {
		if (b1.oBoxOrABox != b2.oBoxOrABox)
			Failure(new IllegalArgumentException("Mixed bounding box types not allowed"))
		else
			b1.oBoxOrABox match {
				case BoundingBox.OBoxOrABox.ABox(abox1) =>
					Success(BoundingBox(BoundingBox.OBoxOrABox.ABox(union(abox1, b2.oBoxOrABox.aBox.get))))
				case BoundingBox.OBoxOrABox.OBox(obox1) =>
					Success(BoundingBox(BoundingBox.OBoxOrABox.OBox(union(obox1, b2.oBoxOrABox.oBox.get))))
				case _ =>
					Failure(new NotImplementedError())
			}
	}

	def union(r1: Region, r2: Region, f: (String, String) => String = (s1, s2) => s1.concat(s2)): Try[Region] = {
		r1.boundingBox.zip(r2.boundingBox).map {
			case (b1:BoundingBox, b2:BoundingBox) => union(b1, b2)
		}.getOrElse(Success(BoundingBox(BoundingBox.OBoxOrABox.ABox(infiniteABox)))) match {
			case Success(bbox) =>
				Success(Region(r1.population + r2.population, f(r1.label, r2.label), Some(bbox)))
			case Failure(f) =>
				Failure(f)
		}
	}

	def intersects(b1: ABox, b2: ABox): Boolean = {
		val min1 = b1.min.getOrElse(XYZs.positiveInfinite)
		val min2 = b2.min.getOrElse(XYZs.positiveInfinite)
		val max1 = b1.max.getOrElse(XYZs.negativeInfinite)
		val max2 = b2.max.getOrElse(XYZs.negativeInfinite)

		for (i <- 0 to 2) {
			if (max1(i) > min2(i) && min1(i) < max2(i))
				return true
		}
		false
	}

	def intersects(b1: OBox, b2: OBox): Boolean = ???


}
