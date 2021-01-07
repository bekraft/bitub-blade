package bitub.dto.spatial

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._


class RegionsSpec extends AnyFlatSpec with should.Matchers {

	val abox000222 = ABox().withMin(XYZ(0, 0, 0)).withMax(XYZ(2,2,2))
	val abox000111 = ABox().withMin(XYZ(0, 0, 0)).withMax(XYZ(1,1,1))
	val abox222444 = ABox().withMin(XYZ(2, 2, 2)).withMax(XYZ(4,4,4))

	"abox000222 and abox000111" should "intersect" in {
		Regions.intersects(abox000111, abox000222) should be (true)
	}

	"abox000222 and abox222444" should "not intersect" in {
		Regions.intersects(abox000222, abox222444) should be (false)
	}
}
