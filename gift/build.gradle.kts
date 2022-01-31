import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("kapt") version "1.3.72"
}

group = "msa"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.hibernate.validator:hibernate-validator:6.1.2.Final")
	implementation("org.apache.commons:commons-lang3:3.9")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
	implementation("org.projectlombok:lombok")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	// retrofit
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation("com.squareup.okhttp3:logging-interceptor:3.9.0")

	// MapStruct
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")

	// kafka
	implementation("org.springframework.kafka:spring-kafka")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	implementation("org.projectlombok:lombok-mapstruct-binding:0.1.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
	annotationProcessor(
		"org.projectlombok:lombok",
		"org.projectlombok:lombok-mapstruct-binding:0.1.0"
	)

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
