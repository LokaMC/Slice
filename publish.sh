./gradlew applyAllPatches
./gradlew publishDevBundlePublicationToMavenLocal -PpublishDevBundle
./gradlew :fork-api:publishToMavenLocal
./gradlew createBundlerJar
