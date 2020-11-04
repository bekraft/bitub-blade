package bitub.dto.spatial

import bitub.dto.spatial.BoundingBox.OBoxOrABox

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

	def union(b1: OBox, b2: OBox): ABox = ???

	def union(b1: BoundingBox, b2: BoundingBox): BoundingBox = ???

	def union(r1: Region, r2: Region, f: (String, String) => String = (s1, s2) => s1.concat(s2)): Region = {
		val boundingBox = r1.boundingBox.zip(r2.boundingBox).map {
			case (b1:BoundingBox, b2:BoundingBox) => union(b1, b2)
		}.getOrElse(BoundingBox(OBoxOrABox.ABox(infiniteABox)))
		Region(r1.population + r2.population, f(r1.label, r2.label), Some(boundingBox))
	}
}
