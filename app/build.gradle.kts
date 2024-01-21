plugins {

    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinxSerialization)

}
//sourceSets{
//    dependencies {
//
//
//        implementation(libs.ktor.client.core)
//        implementation(libs.ktor.client.content.negotiation)
//        implementation(libs.ktor.serialization.kotlinx.json)
//
//        implementation(libs.kamel)
//    }
//}

android {
    namespace = "com.m.ammar.composeTemplate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.m.ammar.composeTemplate"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += setOf("/META-INF/{AL2.0,LGPL2.1}")
            excludes += setOf("/META-INF/gradle/incremental.annotation.processors/**")
        }
    }
}
dependencies {
    implementation(libs.core.ktx)
    implementation(libs.activity.compose)
    implementation(libs.bundles.lifecycle)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.ui.impl)
    implementation(libs.material3)
    implementation(libs.navigation.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.security.crypto)
    ksp(libs.hilt.android.compiler)


    implementation(libs.okhttp.interceptor.logging)

    implementation(libs.moshi)
    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.coil) {
        because("An image loading library for Android backed by Kotlin Coroutines")
    }




    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.kamel)


    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.bundles.compose.ui.debug)



}
