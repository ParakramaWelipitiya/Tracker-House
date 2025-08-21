plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.welipitiya.trackerhouse"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.welipitiya.trackerhouse"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.biometric)
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.google.android.gms:play-services-maps:18.1.0") //Maps SDK
    implementation("com.google.android.gms:play-services-location:21.0.1") //Location Services
    implementation ("com.squareup.okhttp3:okhttp:4.10.0") //For HTTP requests
}