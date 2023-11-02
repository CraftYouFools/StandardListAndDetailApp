plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
}


android {
    namespace = "com.example.standardlistanddetailapplicationcontent"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.standardlistanddetailapplicationcontent"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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

    buildFeatures{

        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")


    implementation("androidx.room:room-common:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    // retrofit for networking
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    val moshi_version by extra { "1.15.0" }
    implementation ("com.squareup.moshi:moshi:$moshi_version")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshi_version")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.github.bumptech.glide:glide:4.11.0")
    kapt ("com.github.bumptech.glide:compiler:4.7.1")

    // DI
    // Adds libraries defining annotations to only the compile classpath.
   // compileOnly("com.google.dagger:dagger:2.28.3")
    // Adds the annotation processor dependency to the annotation processor classpath.
   // annotationProcessor("com.google.dagger:dagger-compiler:2.28.1")


    //dagger2
    api ("com.google.dagger:dagger:2.24")
    api ("com.google.dagger:dagger-android:2.24")
    //api ("com.google.dagger:dagger-android-support:2.24")

    annotationProcessor ("com.google.dagger:dagger-compiler:2.24")
    kapt ("com.google.dagger:dagger-compiler:2.24")

    annotationProcessor ("com.google.dagger:dagger-android-processor:2.24")
    kapt ("com.google.dagger:dagger-android-processor:2.24")

    compileOnly ("javax.annotation:jsr250-api:1.0")
    implementation ("javax.inject:javax.inject:1")





}