// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "8.0.1" apply false

}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        // 添加 Safe Args 插件的 classpath
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
        classpath("com.google.gms:google-services:4.4.0")
        //classpath "com.android.tools.build:gradle:4.0.1"
        //classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72"
    }
}
