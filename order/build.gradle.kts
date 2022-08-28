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
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")
	implementation("org.projectlombok:lombok")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	// MapStruct
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
	implementation("org.projectlombok:lombok-mapstruct-binding:0.1.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
	annotationProcessor(
		"org.projectlombok:lombok",
		"org.projectlombok:lombok-mapstruct-binding:0.1.0"
	)

	//redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// kafka
	implementation("org.springframework.kafka:spring-kafka")
	testImplementation("org.springframework.kafka:spring-kafka-test")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")

	// jwt
	implementation("io.jsonwebtoken:jjwt-api:0.10.7")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.7")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.7")

	// Armeria
	implementation("com.linecorp.armeria:armeria-kotlin")
	implementation("com.linecorp.armeria:armeria:1.9.2")
	implementation("com.linecorp.armeria:armeria-grpc:1.9.2")
}

dependencyManagement {
	imports {
		mavenBom("com.linecorp.armeria:armeria-bom:0.99.5")
	}
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

sourceSets {
	main {
		java {
			srcDirs(
				"order-and-gift-idl/build/generated/source/proto/main/grpc",
				"order-and-gift-idl/build/generated/source/proto/main/java",
				"order-and-gift-idl/build/generated/source/proto/main/grpckt"
			)
		}
	}
}