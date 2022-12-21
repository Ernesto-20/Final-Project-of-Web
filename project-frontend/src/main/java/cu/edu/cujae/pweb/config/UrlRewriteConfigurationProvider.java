package cu.edu.cujae.pweb.config;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

@RewriteConfiguration
public class UrlRewriteConfigurationProvider extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()

                .addRule(Join.path("/security-users").to("/pages/security/users/user-list.jsf"))
                .addRule(Join.path("/welcome").to("/pages/welcome/welcome.jsf"))
                .addRule(Join.path("/init-course/selection-subject")
                        .to("/pages/init-course/selection-subject.jsf"))
                .addRule(Join.path("/init-course/selection-student")
                        .to("/pages/init-course/selection-student.jsf"))
                .addRule(Join.path("/students").to("/pages/student/student-list.jsf"))
                .addRule(Join.path("/assign-grades").to("/pages/studentGrade/studentGrade.jsf"))
                .addRule(Join.path("/ranking").to("/pages/ranking/ranking.jsf"));

        // ej using params
        // .addRule(Join.path("/shop/{category}").to("/faces/viewCategory.jsf"));
    }

    @Override
    public int priority() {
        return 0;
    }

}
