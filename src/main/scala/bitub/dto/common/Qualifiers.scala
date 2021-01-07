package bitub.dto.common

import java.util.UUID

object Qualifiers {
	def newAnonymous(uuid: UUID): Qualifier = Qualifier().withAnonymous(GlobalUniqueId().withGuid(Guids.fromUUID(uuid)))
	def newAnonymousBase64(base64: String): Qualifier = Qualifier().withAnonymous(GlobalUniqueId().withBase64(base64))
	def newAnonymous(): Qualifier = Qualifier().withAnonymous(GlobalUniqueId().withGuid(Guids.newRandomGuid()))
	def newNamed(fragments: String*): Qualifier = Qualifier().withNamed(Name().withFrags(fragments))
	def toClassifier(qualifiers: Qualifier*): Classifier = Classifier(qualifiers)

	object implicits {
		import scala.language.implicitConversions
		implicit def newAnonymous(uuid: UUID): Qualifier = Qualifiers.newAnonymous(uuid)
		implicit def newAnonymousBase64(base64: String): Qualifier = Qualifiers.newAnonymousBase64(base64)
	}
}
