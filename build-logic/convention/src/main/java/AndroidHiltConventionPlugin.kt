import com.appleroid.mkung.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies{
                "implementation"(libs.findBundle("hilt").get())
                "ksp"(libs.findBundle("hilt.compiler").get())
            }
        }
    }
}