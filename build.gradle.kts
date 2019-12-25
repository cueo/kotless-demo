import io.kotless.DSLType
import io.kotless.plugin.gradle.dsl.Webapp.Route53
import io.kotless.plugin.gradle.dsl.kotless

plugins {
    kotlin("jvm") version "1.3.50" apply true
    id("io.kotless") version "0.1.2" apply true
}

group = "com.intuit"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.kotless", "lang", "0.1.2")
    implementation("org.jetbrains.kotlinx", "kotlinx-html-jvm", "0.6.11")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

kotless {
    config {
        bucket = "kotless.s3.example.com"

        dsl {
            type = DSLType.Kotless
            //or for Ktor
            //type = DSLType.Ktor
        }

        terraform {
            profile = "example"
            region = "us-east-1"
        }
    }

    webapp {
        route53 = Route53("kotless", "example.com")

        //configuration of lambda created
        lambda {
            //needed only for Kotless DSL
            kotless {
                //Define packages in which scan for routes should be performed
                packages = setOf("io.kotless.examples")
            }
        }
    }
}
