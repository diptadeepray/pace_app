plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //id("com.android.application")
    id("com.google.gms.google-services")
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.paceapp"
    compileSdk = 35

    buildFeatures {
        viewBinding= true
    }


    defaultConfig {
        applicationId = "com.example.paceapp"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.firebase.auth.ktx)
    val fragment_version = "1.8.5"
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation (libs.firebase.firestore)




    /*val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")*/

    // optional - Kotlin Extensions and Coroutines support for Room
    //implementation("androidx.room:room-ktx:$room_version")

    // optional - Test helpers
    //testImplementation("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    //mplementation("androidx.room:room-paging:$room_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}