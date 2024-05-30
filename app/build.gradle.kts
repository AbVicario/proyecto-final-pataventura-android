plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("com.google.relay") version "0.3.00"
    id("com.google.gms.google-services")
}

android {
    /*signingConfigs {
        getByName("debug") {
            storeFile = file("C:\\Users\\Abel\\Desktop\\firmasApp")
            storePassword = "secret02082010"
            keyAlias = "firmas"
            keyPassword = "secret02082010"
        }
    }*/
    namespace = "com.example.pataventura"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pataventura"
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
            isMinifyEnabled = false
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("io.coil-kt:coil-compose:2.0.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")                      //":1.1.2"

    // :3
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.dagger:hilt-android:2.48.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.databinding:databinding-common:8.3.1")
    implementation("androidx.databinding:databinding-runtime:8.3.1")
    implementation("com.google.firebase:protolite-well-known-types:18.0.0")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    testImplementation("org.testng:testng:6.9.6")
    testImplementation("org.testng:testng:6.9.6")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("io.coil-kt:coil:2.5.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    implementation("androidx.compose.material:material-icons-extended:1.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //maps
    implementation ("com.google.maps.android:maps-compose:2.9.0")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.2.0")

    //permission
    implementation ("com.google.accompanist:accompanist-permissions:0.31.3-beta")


}

kapt {
    correctErrorTypes = true
}