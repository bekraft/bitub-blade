# bitub-dto

JVM Scala and Java protobuf bindings to Bitub.Dto.

## Build using sbt 

`sbt clean publishLocal publishM2`

## Use

Scala & sbt
```
dependencies += "bitub" %% "bitub-dto" % "1.0-SNAPSHOT"
```
Gradle
```
compile group:'bitub', name: 'bitub-dto_2.13', version: '1.0-SNAPSHOT'
```

Maven
```
<dependency>
    <groupId>bitub</groupId>
    <artifactId>bitub-dto</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## License

     Copyright 2020 Bernold Kraft

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
