plugins {
	id 'fabric-loom' version '1.9-SNAPSHOT'
	id 'maven-publish'
	id "com.modrinth.minotaur" version "2.+"
	id 'org.jetbrains.kotlin.jvm'
}

version = project.mod_version
group = project.maven_group

base {
	archivesName = project.archives_base_name
}

repositories {
	maven { url 'https://api.modrinth.com/maven' }
	maven { url "https://maven.shedaniel.me/" }
	maven { url "https://maven.terraformersmc.com/releases/" }
	maven {
		name "KosmX's maven"
		url 'https://maven.kosmx.dev/'
	}
	maven {
        name = "Jitpack"
        url = "https://jitpack.io"
    }
	maven {
        name = "ladysnakeUnreleases"
        url = 'https://maven.ladysnake.org/releases'
    }
	maven{
		name = "ladysnakeReleases"
		url = "https://maven.ladysnake.org/#/releases/"
	}
	maven { url = 'https://maven.nucleoid.xyz/' }
	mavenCentral()
}
	// Add repositories to retrieve artifacts from in here.
	// You should only use this when depending on other mods because
	// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
	// See https://docs.gradle.org/current/userguide/declaring_repositories.html
	// for more information about repositories.

loom {
	splitEnvironmentSourceSets()

	mods {
		"fractured-mod" {
			sourceSet sourceSets.main
			sourceSet sourceSets.client
		}
	}

}

fabricApi {
	configureDataGeneration()
}

dependencies {
	// To change the versions see the gradle.properties file
	minecraft "com.mojang:minecraft:${project.minecraft_version}"
	mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"
	modImplementation "net.fabricmc:fabric-loader:${project.loader_version}"
	modApi("me.shedaniel.cloth:cloth-config-fabric:11.1.136") {
		exclude(group: "net.fabricmc.fabric-api")
	}
	// Fabric API. This is technically optional, but you probably want it anyway.
	modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
	modImplementation 'xyz.nucleoid:fantasy:0.4.11+1.20-rc1'
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-base:${project.cca_version}"
    modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-api:${project.cca_version}"
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-block:${project.cca_version}"
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-chunk:${project.cca_version}"
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-entity:${project.cca_version}"
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-item:${project.cca_version}"
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-level:${project.cca_version}"
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-scoreboard:${project.cca_version}"
	modImplementation "dev.onyxstudios.cardinal-components-api:cardinal-components-world:${project.cca_version}"
    // Copy the following only if you want to bundle Cardinal Components API as a Jar-in-Jar dependency
    // (otherwise, you should configure the dependency on Modrinth/Curseforge)
    include "dev.onyxstudios.cardinal-components-api:cardinal-components-base:${project.cca_version}"
    include "dev.onyxstudios.cardinal-components-api:cardinal-components-api:${project.cca_version}"
	include "dev.onyxstudios.cardinal-components-api:cardinal-components-block:${project.cca_version}"
	include "dev.onyxstudios.cardinal-components-api:cardinal-components-chunk:${project.cca_version}"
	include "dev.onyxstudios.cardinal-components-api:cardinal-components-entity:${project.cca_version}"
	include "dev.onyxstudios.cardinal-components-api:cardinal-components-item:${project.cca_version}"
	include "dev.onyxstudios.cardinal-components-api:cardinal-components-level:${project.cca_version}"
	include "dev.onyxstudios.cardinal-components-api:cardinal-components-scoreboard:${project.cca_version}"
	include "dev.onyxstudios.cardinal-components-api:cardinal-components-world:${project.cca_version}"
	implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk17"
}

processResources {
	inputs.property "version", project.version

	filesMatching("fabric.mod.json") {
		expand "version": project.version
	}
}

tasks.withType(JavaCompile).configureEach {
	it.options.release = 17
}

java {
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present.
	// If you remove this line, sources will not be generated.
	withSourcesJar()

}

jar {
	from("LICENSE") {
		rename { "${it}_${project.base.archivesName.get()}"}
	}
}

// configure the maven publication
publishing {
	publications {
		create("mavenJava", MavenPublication) {
			artifactId = project.archives_base_name
			from components.java
		}
	}

	// See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
	repositories {
		// Add repositories to publish to here.
		// Notice: This block does NOT have the same function as the block in the top level.
		// The repositories here will be used for publishing your artifact, not for
		// retrieving dependencies.
	}
	// build.gradle
modrinth {
    token = System.getenv("MODRINTH_TOKEN") // Remember to have the MODRINTH_TOKEN environment variable set or else this will fail - just make sure it stays private!
    projectId = "fractured-mod" // This can be the project ID or the slug. Either will work!
    versionNumber = "1.2.0" // You don't need to set this manually. Will fail if Modrinth has this version already
    versionType = "beta" // This is the default -- can also be `beta` or `alpha`
    uploadFile = jar // With Loom, this MUST be set to `remapJar` instead of `jar`!
    gameVersions = ["1.20.1"] // Must be an array, even with only one version
    loaders = ["fabric"] // Must also be an array - no need to specify this if you're using Loom or ForgeGradle
    dependencies { // A special DSL for creating dependencies
        // scope.type
        // The scope can be `required`, `optional`, `incompatible`, or `embedded`
        // The type can either be `project` or `version`
        required.project "fabric-api"
		embedded.project "cloth-config" // Creates a new required dependency on Fabric API
    }
}
}
kotlin {
	jvmToolchain(17)
}