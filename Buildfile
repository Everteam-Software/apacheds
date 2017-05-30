require "buildr"
require "install.rb"

# Keep this structure to allow the build system to update version numbers.
VERSION_NUMBER = "8.1.1-SNAPSHOT"

desc "Embedded Apache Directory Service"
define "apacheds-webapp" do
  project.version = VERSION_NUMBER
  project.group = "org.intalio.tempo"
  
  compile.options.source = "1.5"
  compile.options.target = "1.5"
  
  libs = [APACHE_DS_DEPS, APACHE_COMMONS[:lang], APACHE_COMMONS[:collections], JSON_NAGGIT, SLF4J.values]
  compile.with(libs, SERVLET_API)
  test.with(libs, LOG4J)
  package(:war).with :libs => libs
end

