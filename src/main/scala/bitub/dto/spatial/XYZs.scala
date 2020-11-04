package bitub.dto.spatial

object XYZs {
	val positiveInfinite: XYZ = XYZ(Float.PositiveInfinity, Float.PositiveInfinity, Float.PositiveInfinity)
	val negativeInfinite: XYZ = XYZ(Float.NegativeInfinity, Float.NegativeInfinity, Float.NegativeInfinity)

	def diff(p1: XYZ, p2:XYZ): XYZ = XYZ(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z)
	def add(p1: XYZ, p2:XYZ): XYZ = XYZ(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z)
	def scale(p: XYZ, scale: Float): XYZ = XYZ(p.x * scale, p.y * scale, p.z * scale)

	implicit class XYZOps(val p1: XYZ) {
		def diff(p2: XYZ): XYZ = XYZs.diff(p1, p2)
	}
}
