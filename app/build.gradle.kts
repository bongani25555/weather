// build.gradle.kts (app module)

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    testImplementation ("org.mockito:mockito-core:5.7.0")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.robolectric:robolectric:4.5.1")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.test:runner:1.4.0")
    androidTestImplementation ("androidx.test:rules:1.4.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
}
