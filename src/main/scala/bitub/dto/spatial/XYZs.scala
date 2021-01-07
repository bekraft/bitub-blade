package bitub.dto.spatial

object XYZs {
	val positiveInfinite: XYZ = XYZ(Float.PositiveInfinity, Float.PositiveInfinity, Float.PositiveInfinity)
	val negativeInfinite: XYZ = XYZ(Float.NegativeInfinity, Float.NegativeInfinity, Float.NegativeInfinity)

	def diff(p1: XYZ, p2:XYZ): XYZ = XYZ(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z)
	def add(p1: XYZ, p2:XYZ): XYZ = XYZ(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z)
	def scale(p: XYZ, scale: Float): XYZ = XYZ(p.x * scale, p.y * scale, p.z * scale)
	@annotation.tailrec def ordinate(p: XYZ, index: Int): Float = index match {
			case 0 => p.x
			case 1 => p.y
			case 2 => p.z
			case _ => ordinate(p, Math.abs(index) % 3)
	}

	def toNorm2(p: XYZ): Double = Math.sqrt(p.x*p.x + p.y*p.y + p.z*p.z)

	implicit class xyz(val p1: XYZ) extends AnyVal {
		def diff(p2: XYZ): XYZ = XYZs.diff(p1, p2)
		def -(p2: XYZ): XYZ = XYZs.diff(p1, p2)
		def add(p2: XYZ): XYZ = XYZs.add(p1, p2)
		def +(p2: XYZ): XYZ = XYZs.add(p1, p2)
		def scale(scale: Float): XYZ = XYZs.scale(p1, scale)
		def apply(index: Int): Float = XYZs.ordinate(p1, index)
		def toNorm2: Double = XYZs.toNorm2(p1)
	}
}
