import com.appleroid.mkung.addUiLayerDependencies
import com.appleroid.mkung.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("mkung.android.library.compose")
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies{
                addUiLayerDependencies(target)
                "implementation"(libs.findBundle("hilt").get())
                "ksp"(libs.findBundle("hilt.compiler").get())
            }
        }
    }
}