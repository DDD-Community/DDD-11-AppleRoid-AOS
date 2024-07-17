package com.appleroid.mkung

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import com.appleroid.mkung.ExtensionType
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.util.Properties

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*,*,*,*,*>,
    extensionType: ExtensionType
){
    commonExtension.run {
        buildFeatures{
            buildConfig = true
        }
        val projectVersionName = libs.findVersion("projectVersionName").get().toString()
        when(extensionType){

            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(projectVersionName)
                            isDebuggable = true
                        }
                        release {
                            configureReleaseBuildType(commonExtension, projectVersionName)
                            isDebuggable = false
                        }
                    }
                }
            }

            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(projectVersionName)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, projectVersionName)
                        }
                    }
                }
            }
        }
    }
}


private fun Project.getPropertyFromFiles(propertyName: String): String {
    val propertiesFiles = listOf("local.defaults.properties", "secrets.properties", "local.properties")
    val properties = Properties()

    propertiesFiles.forEach { fileName ->
        val file = File(rootDir, fileName)
        if (file.exists()) {
            file.inputStream().use { properties.load(it) }
        }
    }

    return properties.getProperty(propertyName) ?: throw IllegalStateException("Property $propertyName not found in any properties file.")
}

private fun BuildType.configureDebugBuildType(
    projectVersionName: String
){
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *>,
    projectVersionName: String
){

    isMinifyEnabled = false // 코드 난독화
    isShrinkResources = false // 리소스 축소
    proguardFiles( // ProGuard 설정: 코드 최적화
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}