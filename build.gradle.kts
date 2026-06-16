plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.korsh"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
      name = "sonatype-oss-snapshots1"
      mavenContent { snapshotsOnly() }
    }
    
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    implementation("com.mysql:mysql-connector-j:9.7.0")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "21"
    targetCompatibility = "21"
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.build{
    doLast{
        copy{
            from(tasks.jar.get().archiveFile)
            into(file("${rootProject.projectDir}/server/plugins"))
        }
    }
}