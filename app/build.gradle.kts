plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "pt.project.rebanpet"
    compileSdk = 35

    defaultConfig {
        applicationId = "pt.project.rebanpet"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
        compose = true
    }
}

dependencies {

    implementation (libs.androidx.ui)
    implementation("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.material:material:1.7.7")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.7.7")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation ("androidx.compose.compiler:compiler:1.5.15")
    implementation ("androidx.activity:activity-compose:1.10.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.7")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.navigation:navigation-compose:2.7.8")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("com.google.maps.android:maps-compose:2.11.5")
    implementation("com.google.maps.android:android-maps-utils:3.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.curvedbottomnavigation)
    implementation (libs.circleimageview)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.messaging)
    implementation("com.google.android.gms:play-services-auth:21.3.0")
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation ("com.google.android.gms:play-services-maps:19.0.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")
    implementation ("com.google.android.libraries.places:places:4.1.0")
    implementation ("com.google.firebase:firebase-firestore-ktx:24.8.1")
    implementation (libs.firebase.storage.ktx)
    implementation (libs.glide)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.firebase.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}