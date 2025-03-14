plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    //  implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("io.ktor:ktor-server-config-yaml:2.3.8")
    implementation(libs.exposed.dao)
    implementation(libs.mariadb)  // Usando la versión desde el archivo libs.versions.toml
    implementation(libs.exposedjdbc)  // Usando la versión desde el archivo libs.versions.toml
    // implementation("org.mariadb.jdbc:mariadb-java-client:2.7.3")
    implementation("com.auth0:java-jwt:4.4.0") // Asegúrate de usar la última versión disponible
    implementation("io.ktor:ktor-server-auth:2.3.7")  // Para autenticación en general
    implementation("io.ktor:ktor-server-auth-jwt:2.3.7") // Para JWT específicamente
    implementation("com.auth0:java-jwt:4.4.0") // Para generar/verificar tokens


    // Tests
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}

