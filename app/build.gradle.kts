plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.openmovie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.openmovie"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(libs.play.services.maps)
    val nav_version = "2.7.7"
    val room_version = "2.6.1"
    val hilt_version = "2.48"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$2.7.0")
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    implementation("com.google.dagger:hilt-android:$hilt_version")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    implementation("com.google.android.gms:play-services-location:20.0.0")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-firestore")

    val nav_version_ktx = "2.3.0-alpha01"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version_ktx")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version_ktx")

    val material_version = "1.1.0"
    implementation ("com.google.android.material:material:$material_version")

    val fragment_version = "1.2.2"
    implementation ("androidx.fragment:fragment-ktx:$fragment_version")
    implementation("androidx.work:work-runtime:2.7.1")

    implementation("com.google.firebase:firebase-storage")
}