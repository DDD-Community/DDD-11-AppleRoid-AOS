import com.appleroid.mkung.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies{
                "implementation"(libs.findBundle("retrofit").get())
            }
        }
    }
}