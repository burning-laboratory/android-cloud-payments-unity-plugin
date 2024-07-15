plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
    gradlePluginPortal()
}

android {
    namespace = "com.burninglab.cpunityplugin"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
    publishing {
        multipleVariants {
            allVariants()
            withJavadocJar()
            withSourcesJar()
        }
    }
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                /** Configure path of your package repository on Github
                 *  Replace GITHUB_USERID with your/organisation Github userID and REPOSITORY with the repository name on GitHub
                 */
                url = uri("https://maven.pkg.github.com/burning-laboratory/android-cloud-payments-unity-plugin") // Github Package
                credentials {
                    //Fetch these details from the properties file or from Environment variables
                    username = System.getenv("GITHUB_USERNAME")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
        publications {
            create<MavenPublication>("mavenRelease") {
                groupId = "com.burning-lab"
                artifactId = "cpunityplugin"
                version = "1.1.0"

                from(components["release"])
            }
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
    implementation("ru.cloudpayments.gitpub.integrations.sdk:cloudpayments-android:1.5.2")

    compileOnly(fileTree("libs"))

}